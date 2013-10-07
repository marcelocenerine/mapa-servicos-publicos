package br.com.servicospublicos.model;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import br.com.servicospublicos.rest.serialization.CategoriaSerializer;

@JsonSerialize(using=CategoriaSerializer.class)
public enum Categoria {
	
	UBS("Unidade Básica de Saúde"), 
	CARTORIO("Cartório"), 
	INSS("INSS"), 
	RFB("Receita Federal"), 
	ASS_SOCIAL("Assistência Social"), 
	COM_TERAP("Comunidade Terapêutica"), 
	SINE("Sistema Nacional de Emprego - SINE"), 
	ENS_BASICO("Escola Ensino Básico"), 
	ENS_SUPERIOR("Escola Ensino Superior"), 
	CORREIOS("Correios");
	
	private String descricao;
	
	private Categoria(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
