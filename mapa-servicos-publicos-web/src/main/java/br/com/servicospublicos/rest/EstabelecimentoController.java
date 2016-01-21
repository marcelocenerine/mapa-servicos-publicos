package br.com.servicospublicos.rest;

import static java.util.Objects.nonNull;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.http.ResponseEntity.ok;
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
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/estabelecimentos")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EstabelecimentoController {

	private final EstabelecimentoRepository repository;

	@RequestMapping(value = "/{id}", method = GET, produces = APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Estabelecimento> buscar(@PathVariable String id) {
		Estabelecimento estabelecimento = repository.buscar(id);

		return nonNull(estabelecimento) ? ok(estabelecimento) : new ResponseEntity<>(NOT_FOUND);
	}

	@RequestMapping(method = GET, produces = APPLICATION_JSON_UTF8_VALUE)
	public List<Estabelecimento> buscar(
			@RequestParam(required = true) double lng,
			@RequestParam(required = true) double lat,
			@RequestParam(defaultValue = "30") double raio,
			@RequestParam(defaultValue = "") List<Categoria> categorias,
			@RequestParam(defaultValue = "500") int limite) {
		return repository.buscar(Coordenadas.from(lng, lat), Math.min(raio, 30.0), categorias, Math.min(limite, 500)); // TODO proper validation
	}
}
