package br.com.servicospublicos.controller.rest.types;

import java.util.List;

import br.com.servicospublicos.model.Coordenadas;
import br.com.servicospublicos.model.Estabelecimento.Categoria;

public class Pesquisa {
	
	private List<Categoria> categorias;
	private Coordenadas local;
	
	public List<Categoria> getCategorias() {
		return categorias;
	}
	
	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	
	public Coordenadas getLocal() {
		return local;
	}
	
	public void setLocal(Coordenadas local) {
		this.local = local;
	}
}
