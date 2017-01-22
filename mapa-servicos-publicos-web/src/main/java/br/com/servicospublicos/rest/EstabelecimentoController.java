package br.com.servicospublicos.rest;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.servicospublicos.domain.Categoria;
import br.com.servicospublicos.domain.Coordenadas;
import br.com.servicospublicos.domain.Estabelecimento;
import br.com.servicospublicos.repository.EstabelecimentoRepository;

@RestController
@RequestMapping(value = "api/v1/estabelecimentos", produces = APPLICATION_JSON_UTF8_VALUE)
public class EstabelecimentoController {

	private static final int MAX_LIMITE = 500;
	private static final double MAX_RAIO = 30.0;

	private final EstabelecimentoRepository repository;

	@Autowired
	public EstabelecimentoController(EstabelecimentoRepository repository) {
		this.repository = repository;
	}

	@RequestMapping(value = "/{id}", method = GET)
	public ResponseEntity<Estabelecimento> buscar(@PathVariable String id) {
		return repository.buscar(id)
				.map(ResponseEntity::ok)
				.orElse(new ResponseEntity<>(NOT_FOUND));
	}

	@RequestMapping(method = GET)
	public List<Estabelecimento> buscar(
			@RequestParam(required = true) double lng,
			@RequestParam(required = true) double lat,
			@RequestParam(defaultValue = "30") double raio,
			@RequestParam(defaultValue = "") List<Categoria> categorias,
			@RequestParam(defaultValue = "500") int limite) {
		Coordenadas coordenadas = Coordenadas.from(lng, lat);
		return repository.buscar(coordenadas, Math.min(raio, MAX_RAIO), categorias, Math.min(limite, MAX_LIMITE)); // TODO proper validation
	}
}
