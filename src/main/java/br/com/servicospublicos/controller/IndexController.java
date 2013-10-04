package br.com.servicospublicos.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping(value="/", method=GET)
	public String root() {
		return "redirect:/home";
	}
	
	@RequestMapping(value="/home", method=GET)
	public String home() {
		return "index";
	}
}
