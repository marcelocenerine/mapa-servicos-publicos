package br.com.servicospublicos.model;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import br.com.servicospublicos.rest.serialization.CategoriaSerializer;

@JsonSerialize(using=CategoriaSerializer.class)
public enum Categoria {
	
	ASS_SOCIAL("Assistência Social"), 
	CARTORIO("Cartório"), 
	COM_TERAP("Comunidade Terapêutica"), 
	CORREIOS("Correios"),
	ENS_BASICO("Escola Ensino Básico"), 
	ENS_SUPERIOR("Escola Ensino Superior"), 
	INSS("INSS"), 
	RFB("Receita Federal"), 
	SINE("Sistema Nacional de Emprego"), 
	UBS("Unidade Básica de Saúde"),
	LOTERICA("Lotérica");
	
	private String descricao;
	
	private Categoria(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
