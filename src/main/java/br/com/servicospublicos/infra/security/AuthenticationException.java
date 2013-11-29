package br.com.servicospublicos.infra.security;

public class AuthenticationException extends RuntimeException {

	private static final long serialVersionUID = 1160220611873259694L;
	
	public AuthenticationException(String message) {
		super(message);
	}
}
