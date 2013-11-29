package br.com.servicospublicos.business;

import java.util.List;

import br.com.servicospublicos.model.Categoria;
import br.com.servicospublicos.model.Coordenadas;
import br.com.servicospublicos.model.Estabelecimento;
import br.com.servicospublicos.model.Review;

public interface EstabelecimentoBusiness {

	Estabelecimento buscar(String id);
	
	List<Estabelecimento> buscar(List<Categoria> categorias, Coordenadas coordenadas);
	
	List<Estabelecimento> buscar(Categoria categoria, Coordenadas coordenadas, Double maxDistance, Integer maxResults);
	
	Estabelecimento adicionarReview(String idEstabelecimento, Review review);
}
