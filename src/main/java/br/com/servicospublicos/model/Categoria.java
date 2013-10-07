package br.com.servicospublicos.model;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import br.com.servicospublicos.rest.serialization.CategoriaSerializer;

@JsonSerialize(using=CategoriaSerializer.class)
public enum Categoria {
	
	UBS("Unidade B�sica de Sa�de"), 
	CARTORIO("Cart�rio"), 
	INSS("INSS"), 
	RFB("Receita Federal"), 
	ASS_SOCIAL("Assist�ncia Social"), 
	COM_TERAP("Comunidade Terap�utica"), 
	SINE("Sistema Nacional de Emprego - SINE"), 
	ENS_BASICO("Escola Ensino B�sico"), 
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
