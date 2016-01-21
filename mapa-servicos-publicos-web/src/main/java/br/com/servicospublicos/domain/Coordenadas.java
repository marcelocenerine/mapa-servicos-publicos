package br.com.servicospublicos.domain;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.Setter;

public class Coordenadas {

	@Field("lng")
	private @Getter @Setter double longitude;

	@Field("lat")
	private @Getter @Setter double latitude;

	public Coordenadas() {}

	private Coordenadas(double longitude, double latitude) {
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public static Coordenadas from(double longitude, double latitude) {
		return new Coordenadas(longitude, latitude);
	}
}
