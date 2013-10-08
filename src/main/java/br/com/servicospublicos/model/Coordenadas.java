package br.com.servicospublicos.model;

import org.springframework.data.mongodb.core.mapping.Field;

public class Coordenadas {
	
	@Field("lng")
	private Double longitude;
	@Field("lat")
	private Double latitude;
	
	public Coordenadas() {}
	
	private Coordenadas(Double longitude, Double latitude) {
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public static Coordenadas from(Double longitude, Double latitude) {
		return new Coordenadas(longitude, latitude);
	}

	public Double getLongitude() {
		return longitude;
	}
	
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	public Double getLatitude() {
		return latitude;
	}
	
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

}
