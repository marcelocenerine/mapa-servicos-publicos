package br.com.servicospublicos.business;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.servicospublicos.model.Categoria;
import br.com.servicospublicos.model.Coordenadas;
import br.com.servicospublicos.model.Estabelecimento;
import br.com.servicospublicos.repository.EstabelecimentoRepository;

@Service
public class EstabelecimentoBusinessImpl implements EstabelecimentoBusiness {

	@Inject
	private EstabelecimentoRepository repository;
	
	private static final double DEFAULT_MAX_DISTANCE = 30;
	private static final int DEFAULT_MAX_RESULTS = 500;


	@Override
	public List<Estabelecimento> buscar(Categoria categoria, Coordenadas coordenadas) {
		return repository.findByCategoriaAndCoordenadas(categoria, coordenadas, DEFAULT_MAX_DISTANCE, DEFAULT_MAX_RESULTS);
	}

	@Override
	public List<Estabelecimento> buscar(Categoria categoria, Coordenadas coordenadas, Double maxDistance, Integer maxResults) {
		return repository.findByCategoriaAndCoordenadas(categoria, coordenadas, maxDistance, maxResults);
	}
	
	@Deprecated
	public List<Estabelecimento> buscar(List<Categoria> categorias,	Coordenadas coordenadas) {
		if (categorias.isEmpty()) return Collections.emptyList();
		
		return repository.findByCategoriasAndCoordenadas(categorias, coordenadas, DEFAULT_MAX_DISTANCE, DEFAULT_MAX_RESULTS);
	}
}
