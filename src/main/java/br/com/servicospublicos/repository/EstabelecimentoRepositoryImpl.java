package br.com.servicospublicos.repository;

import static br.com.servicospublicos.model.Estabelecimento.Status.VISIBLE;
import static java.lang.Boolean.TRUE;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.List;

import javax.inject.Inject;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.Distance;
import org.springframework.data.mongodb.core.geo.Metrics;
import org.springframework.data.mongodb.core.geo.Point;
import org.springframework.stereotype.Repository;

import br.com.servicospublicos.model.Categoria;
import br.com.servicospublicos.model.Coordenadas;
import br.com.servicospublicos.model.Estabelecimento;

@Repository
public class EstabelecimentoRepositoryImpl implements EstabelecimentoRepository {
	
	@Inject
	private MongoTemplate mongoTemplate;

	@Override
	public Estabelecimento findById(String id) {
		return mongoTemplate.findById(id, Estabelecimento.class);
	}

	@Override
	public List<Estabelecimento> findByCategoriasAndCoordenadas(List<Categoria> categorias, Coordenadas coordenadas, Double distancia, Integer maxResults) {
		return mongoTemplate.find(query(where("categoria").in(categorias)
										.and("localizacao.coordenadas").nearSphere(new Point(coordenadas.getLongitude(), coordenadas.getLatitude()))
																	   .maxDistance(new Distance(distancia, Metrics.KILOMETERS).getNormalizedValue())
										.and("status").is(VISIBLE)
										.and("publico").is(TRUE))
								  .limit(maxResults), Estabelecimento.class);
	}

	@Override
	public List<Estabelecimento> findByCategoriaAndCoordenadas(Categoria categoria, Coordenadas coordenadas, Double distancia, Integer maxResults) {
		return mongoTemplate.find(query(where("categoria").is(categoria)
										.and("localizacao.coordenadas").nearSphere(new Point(coordenadas.getLongitude(), coordenadas.getLatitude()))
																	   .maxDistance(new Distance(distancia, Metrics.KILOMETERS).getNormalizedValue())
										.and("status").is(VISIBLE)
										.and("publico").is(TRUE))
								  .limit(maxResults), Estabelecimento.class);
	}

}
