package br.com.servicospublicos.repository;

import java.util.List;

import br.com.servicospublicos.model.Categoria;
import br.com.servicospublicos.model.Coordenadas;
import br.com.servicospublicos.model.Estabelecimento;
import br.com.servicospublicos.model.Review;

public interface EstabelecimentoRepository {

	Estabelecimento findById(String id);
	
	List<Estabelecimento> findByCategoriaAndCoordenadas(Categoria categoria, Coordenadas coordenadas, Double distancia, Integer maxResults);
	
	List<Estabelecimento> findByCategoriasAndCoordenadas(List<Categoria> categorias, Coordenadas coordenadas, Double distancia, Integer maxResults);
	
	Estabelecimento addReview(String idEstabelecimento, Review review);
}
