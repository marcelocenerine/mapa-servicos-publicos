package br.com.servicospublicos.view.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.servicospublicos.infra.security.User;
import br.com.servicospublicos.view.helper.FacebookAuthHelper;

@Controller
public class LoginController {
	
	@Autowired
	private FacebookAuthHelper authHelper;

	@RequestMapping(value="/user/id/{userId}/login", method=POST)
	public String login(@PathVariable String userId, 
						@RequestParam(value="user.name") String userName, 
						@RequestParam(value="user.pictureUrl") String pictureUrl, 
						HttpServletRequest request) {
		//TODO: validar cookie facebook
		request.getSession(true).setAttribute("user", new User(userId, userName, pictureUrl));
		return "redirect:/home";
	}
	
	@RequestMapping(value="/user/logout", method=GET)
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/home";
	}
}
