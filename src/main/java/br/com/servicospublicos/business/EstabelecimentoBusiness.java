package br.com.servicospublicos.business;

import java.util.List;

import br.com.servicospublicos.model.Coordenadas;
import br.com.servicospublicos.model.Estabelecimento;
import br.com.servicospublicos.model.Estabelecimento.Categoria;

public interface EstabelecimentoBusiness {

	List<Estabelecimento> buscar(List<Categoria> categorias,	Coordenadas coordenadas);
}
