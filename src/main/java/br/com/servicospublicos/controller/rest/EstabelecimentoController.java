package br.com.servicospublicos.controller.rest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.servicospublicos.business.EstabelecimentoBusiness;
import br.com.servicospublicos.model.Coordenadas;
import br.com.servicospublicos.model.Estabelecimento;
import br.com.servicospublicos.model.Estabelecimento.Categoria;

@Controller
@RequestMapping("rest/api")
public class EstabelecimentoController {
	
	@Inject
	private EstabelecimentoBusiness business;
	
	@RequestMapping(value="/servicos/lng/{longitude}/lat/{latitute}/categorias/{categorias}", 
					method=GET, 
					produces="application/json;charset=UTF-8")
	public @ResponseBody List<Estabelecimento> buscar(@PathVariable Double longitude, 
													  @PathVariable Double latitute, 
													  @PathVariable List<Categoria> categorias) {
		
		return business.buscar(categorias, new Coordenadas(longitude, latitute));
	}
	
}
