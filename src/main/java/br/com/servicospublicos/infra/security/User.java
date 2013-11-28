package br.com.servicospublicos.infra.security;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 8371914386250066291L;
	
	private String id;
	private String name;
	private String pictureUrl;

	public User(String id, String name, String pictureUrl) {
		this.id = id;
		this.name = name;
		this.pictureUrl = pictureUrl;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}
	
}
