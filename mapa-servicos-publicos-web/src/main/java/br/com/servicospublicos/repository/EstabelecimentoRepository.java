package br.com.servicospublicos.repository;

import static br.com.servicospublicos.domain.Estabelecimento.Status.VISIBLE;
import static java.lang.Boolean.TRUE;
import static org.springframework.data.geo.Metrics.KILOMETERS;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import br.com.servicospublicos.domain.Categoria;
import br.com.servicospublicos.domain.Coordenadas;
import br.com.servicospublicos.domain.Estabelecimento;

@Repository
public class EstabelecimentoRepository {

	private final MongoTemplate mongoTemplate;

	@Autowired
	public EstabelecimentoRepository(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public Optional<Estabelecimento> buscar(String id) {
		return Optional.ofNullable(mongoTemplate.findById(id, Estabelecimento.class));
	}

	public List<Estabelecimento> buscar(Coordenadas coordenadas, double raio, List<Categoria> categorias, int limite) {
		categorias = categorias.isEmpty() ? Arrays.asList(Categoria.values()) : categorias;

		Point point = new Point(coordenadas.getLongitude(), coordenadas.getLatitude());
		double distance = new Distance(raio, KILOMETERS).getNormalizedValue();
		Query query = query(where("categoria").in(categorias)
							.and("localizacao.coordenadas").nearSphere(point).maxDistance(distance)
							.and("status").is(VISIBLE)
							.and("publico").is(TRUE))
							.limit(limite);
		query.fields().include("id")
					  .include("categoria")
					  .include("nome")
					  .include("localizacao")
					  .include("contato");
		return mongoTemplate.find(query, Estabelecimento.class);
	}
}
