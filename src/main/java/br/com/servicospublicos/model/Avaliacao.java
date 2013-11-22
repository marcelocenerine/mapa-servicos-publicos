package br.com.servicospublicos.model;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

public class Avaliacao {

	@JsonIgnore
	private Long quantidade;
	@JsonIgnore
	private Double somaNotas;
	private List<ItemAvaliacao> avaliacoes;
	
	public List<ItemAvaliacao> getAvaliacoes() {
		return avaliacoes;
	}

	@JsonProperty
	public Integer getAvaliacao() {
		if (quantidade == null || quantidade == 0) return null;
		return (int) Math.round(somaNotas / quantidade);
	}
}
