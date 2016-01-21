package br.com.servicospublicos.repository;

import static br.com.servicospublicos.domain.Estabelecimento.Status.VISIBLE;
import static java.lang.Boolean.TRUE;
import static org.springframework.data.geo.Metrics.KILOMETERS;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import br.com.servicospublicos.domain.Categoria;
import br.com.servicospublicos.domain.Coordenadas;
import br.com.servicospublicos.domain.Estabelecimento;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor(onConstructor=@__(@Autowired))
public class EstabelecimentoRepository {

	private final MongoTemplate mongoTemplate;

	public Estabelecimento buscar(String id) {
		return mongoTemplate.findById(id, Estabelecimento.class);
	}

	public List<Estabelecimento> buscar(Coordenadas coordenadas, double raio, List<Categoria> categorias, int limite) {
		categorias = categorias.isEmpty() ? Arrays.asList(Categoria.values()) : categorias;

		Query query = query(where("categoria").in(categorias)
							.and("localizacao.coordenadas").nearSphere(new Point(coordenadas.getLongitude(), coordenadas.getLatitude()))
														   .maxDistance(new Distance(raio, KILOMETERS).getNormalizedValue())
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
