package br.com.servicospublicos.domain;

import lombok.Getter;
import lombok.Setter;

public class Localizacao {
	private @Getter @Setter String endereco;
	private @Getter @Setter String complemento;
	private @Getter @Setter String bairro;
	private @Getter @Setter String cidade;
	private @Getter @Setter UF uf;
	private @Getter @Setter String cep;
	private @Getter @Setter Coordenadas coordenadas;
}
