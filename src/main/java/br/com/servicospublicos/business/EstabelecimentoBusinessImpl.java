package br.com.servicospublicos.business;

import static br.com.servicospublicos.model.Estabelecimento.Status.VISIBLE;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.springframework.data.mongodb.core.geo.Distance;
import org.springframework.data.mongodb.core.geo.Metrics;
import org.springframework.data.mongodb.core.geo.Point;
import org.springframework.stereotype.Service;

import br.com.servicospublicos.model.Coordenadas;
import br.com.servicospublicos.model.Estabelecimento;
import br.com.servicospublicos.model.Estabelecimento.Categoria;
import br.com.servicospublicos.repository.EstabelecimentoRepository;

@Service
public class EstabelecimentoBusinessImpl implements EstabelecimentoBusiness {

	@Inject
	private EstabelecimentoRepository repository;
	
	private static final Distance MAX_DISTANCE = new Distance(50, Metrics.KILOMETERS);

	public List<Estabelecimento> buscar(List<Categoria> categorias,	Coordenadas coordenadas) {
		if (categorias.isEmpty()) return Collections.emptyList();
		
		return repository.findByCategoriaInAndLocalizacaoCoordenadasNearAndStatus(categorias, 
						new Point(coordenadas.getLongitude(), coordenadas.getLatitude()), MAX_DISTANCE,	VISIBLE);
	}
}
