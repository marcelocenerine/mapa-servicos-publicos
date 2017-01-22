package br.com.servicospublicos.domain;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public @Getter @Setter class Contato {
	private String email;
	private List<String> telefones;
	private List<String> fax;
}
