package br.com.servicospublicos.repository;

import java.util.List;

import org.springframework.data.mongodb.core.geo.Distance;
import org.springframework.data.mongodb.core.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.servicospublicos.model.Categoria;
import br.com.servicospublicos.model.Estabelecimento;
import br.com.servicospublicos.model.Estabelecimento.Status;

public interface EstabelecimentoRepository extends MongoRepository<Estabelecimento, String> {

	Estabelecimento findById(String id);
	
	List<Estabelecimento> findByCategoriaInAndLocalizacaoCoordenadasNearAndStatus(List<Categoria> categorias, Point location, Distance distance, Status status);
}
