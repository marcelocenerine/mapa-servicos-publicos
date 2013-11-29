package br.com.servicospublicos.business.exception;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = -4474276066950967509L;
	
	public BusinessException(String message) {
		super(message);
	}

}
