package br.com.servicospublicos.repository;

import static br.com.servicospublicos.model.Estabelecimento.Status.VISIBLE;
import static java.lang.Boolean.TRUE;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.List;

import javax.inject.Inject;

import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.Distance;
import org.springframework.data.mongodb.core.geo.Metrics;
import org.springframework.data.mongodb.core.geo.Point;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import br.com.servicospublicos.model.Categoria;
import br.com.servicospublicos.model.Coordenadas;
import br.com.servicospublicos.model.Estabelecimento;
import br.com.servicospublicos.model.Review;

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
		Query query = query(where("categoria").in(categorias)
							.and("localizacao.coordenadas").nearSphere(new Point(coordenadas.getLongitude(), coordenadas.getLatitude()))
														   .maxDistance(new Distance(distancia, Metrics.KILOMETERS).getNormalizedValue())
							.and("status").is(VISIBLE)
							.and("publico").is(TRUE))
							.limit(maxResults);
		return mongoTemplate.find(lightProjection(query), Estabelecimento.class);
	}

	@Override
	public List<Estabelecimento> findByCategoriaAndCoordenadas(Categoria categoria, Coordenadas coordenadas, Double distancia, Integer maxResults) {
		Query query = query(where("categoria").is(categoria)
							.and("localizacao.coordenadas").nearSphere(new Point(coordenadas.getLongitude(), coordenadas.getLatitude()))
														   .maxDistance(new Distance(distancia, Metrics.KILOMETERS).getNormalizedValue())
							.and("status").is(VISIBLE)
							.and("publico").is(TRUE))
							.limit(maxResults);
		return mongoTemplate.find(lightProjection(query), Estabelecimento.class);
	}
	
	private Query lightProjection(Query query) {
		query.fields().include("id")
					  .include("categoria")
					  .include("nome")
					  .include("localizacao")
					  .include("contato")
					  .include("avaliacao.count")
					  .include("avaliacao.somaNotas");
		return query;
	}

	@Override
	public Estabelecimento addReview(String idEstabelecimento, Review review) {
		Query findQuery = query(where("id").is(idEstabelecimento));
		Update update = new Update().inc("avaliacao.count", 1)
									.inc("avaliacao.somaNotas", review.getNota())
									.push("avaliacao.reviews", review);
		return mongoTemplate.findAndModify(findQuery, update, new FindAndModifyOptions().returnNew(true), Estabelecimento.class);
	}
}
