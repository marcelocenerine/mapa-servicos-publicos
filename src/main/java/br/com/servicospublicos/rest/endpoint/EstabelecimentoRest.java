package br.com.servicospublicos.rest.endpoint;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.servicospublicos.business.EstabelecimentoBusiness;
import br.com.servicospublicos.model.Categoria;
import br.com.servicospublicos.model.Coordenadas;
import br.com.servicospublicos.model.Estabelecimento;

@Controller
@RequestMapping("rest/api")
public class EstabelecimentoRest {
	
	@Inject
	private EstabelecimentoBusiness business;
	
	@RequestMapping(value="/servicos/estabelecimento/{id}", method=GET, produces="application/json;charset=UTF-8")
	public @ResponseBody Estabelecimento buscar(@PathVariable String id) {
		return business.buscar(id);
	}
	
	@RequestMapping(value="/servicos/lng/{longitude}/lat/{latitute}/categoria/{categoria}/distancia/{distancia}/limite/{limite}", method=GET, produces="application/json;charset=UTF-8")
	public @ResponseBody List<Estabelecimento> buscar(@PathVariable Double longitude, @PathVariable Double latitute, @PathVariable Categoria categoria, @PathVariable Double distancia, @PathVariable Integer limite) {
		return business.buscar(categoria, Coordenadas.from(longitude, latitute), distancia, limite);
	}
	
	@RequestMapping(value="/servicos/lng/{longitude}/lat/{latitute}/categorias/{categorias}", method=GET, produces="application/json;charset=UTF-8")
	public @ResponseBody List<Estabelecimento> buscar(@PathVariable Double longitude, @PathVariable Double latitute, @PathVariable List<Categoria> categorias) {
		return business.buscar(categorias, Coordenadas.from(longitude, latitute));
	}
}
