package br.com.servicospublicos.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.servicospublicos.model.Categoria;

@Controller
public class IndexController {

	@RequestMapping(value="/", method=GET)
	public String root() {
		return "redirect:/home";
	}

	@RequestMapping(value="/home", method=GET)
	public ModelAndView home() {
		return new ModelAndView("index", "categorias", Categoria.values());
	}
	
	@RequestMapping(value="/homeB", method=GET)
	public ModelAndView homeB() {
		return new ModelAndView("indexB", "categorias", Categoria.values());
	}
}
