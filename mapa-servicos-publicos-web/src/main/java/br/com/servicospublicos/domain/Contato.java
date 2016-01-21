package br.com.servicospublicos.domain;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class Contato {
	private @Getter @Setter String email;
	private @Getter @Setter List<String> telefones;
	private @Getter @Setter List<String> fax;
}
