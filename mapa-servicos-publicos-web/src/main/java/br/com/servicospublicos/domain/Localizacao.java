package br.com.servicospublicos.domain;

import lombok.Getter;
import lombok.Setter;

public @Getter @Setter class Localizacao {
	private String endereco;
	private String complemento;
	private String bairro;
	private String cidade;
	private UF uf;
	private String cep;
	private Coordenadas coordenadas;
}
