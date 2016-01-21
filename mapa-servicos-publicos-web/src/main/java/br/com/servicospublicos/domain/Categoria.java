package br.com.servicospublicos.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.servicospublicos.rest.json.CategoriaSerializer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@JsonSerialize(using=CategoriaSerializer.class)
@RequiredArgsConstructor
public enum Categoria {

	ASS_SOCIAL("Assistência Social"),
	CARTORIO("Cartório"),
	COM_TERAP("Comunidade Terapêutica"),
	CORREIOS("Correios"),
	ENS_BASICO("Escola Ensino Básico"),
	ENS_SUPERIOR("Escola Ensino Superior"),
	LOTERICA("Lotérica"),
	INSS("Previdência Social"),
	RFB("Receita Federal"),
	SINE("Sistema Nacional de Emprego"),
	UBS("Unidade Básica de Saúde");

	private final @Getter String descricao;
}
