package br.com.servicospublicos.model;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include=Inclusion.NON_NULL)
public class Avaliacao {

	@JsonIgnore
	private Long count = 0L;
	@JsonIgnore
	private Long somaNotas = 0L;
	private List<Review> reviews;
	
	public List<Review> getReviews() {
		return reviews;
	}

	@JsonProperty
	public int getNotaMedia() {
		if (count == 0) return 0;
		return (int) Math.round(somaNotas / new Double(count));
	}
}
