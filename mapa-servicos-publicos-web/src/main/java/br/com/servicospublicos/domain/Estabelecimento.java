package br.com.servicospublicos.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "estabelecimentos")
public @Getter @Setter class Estabelecimento {

	public enum Status { HIDDEN, VISIBLE }

	private String id;
	private Categoria categoria;
	private String nome;
	private Localizacao localizacao;
	private Contato contato;
	private String site;
	private String atendimento;
	private boolean publico;
	private Status status;
}
