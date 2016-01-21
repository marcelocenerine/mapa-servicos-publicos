package br.com.servicospublicos.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "estabelecimentos")
public class Estabelecimento {

	public enum Status { HIDDEN, VISIBLE }

	private @Getter @Setter String id;
	private @Getter @Setter Categoria categoria;
	private @Getter @Setter String nome;
	private @Getter @Setter Localizacao localizacao;
	private @Getter @Setter Contato contato;
	private @Getter @Setter String site;
	private @Getter @Setter String atendimento;
	private @Getter @Setter boolean publico;
	private @Getter @Setter Status status;
}
