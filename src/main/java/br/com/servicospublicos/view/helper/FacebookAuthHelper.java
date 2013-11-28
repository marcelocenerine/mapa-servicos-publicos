package br.com.servicospublicos.view.helper;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("application")
public class FacebookAuthHelper {
	
	private static final String FACEBOOK_APP_ID = "670595456295135";
	
	@Autowired
	private ServletContext context;

	@PostConstruct
	public void startup() {
		context.setAttribute("facebookAppId", FACEBOOK_APP_ID);
	}
	
	public String getAppId() {
		return FACEBOOK_APP_ID;
	}

}
