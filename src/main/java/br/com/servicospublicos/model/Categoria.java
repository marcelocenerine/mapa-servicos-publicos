package br.com.servicospublicos.model;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import br.com.servicospublicos.rest.serialization.CategoriaSerializer;

@JsonSerialize(using=CategoriaSerializer.class)
public enum Categoria {
	
	ASS_SOCIAL("Assist�ncia Social"), 
	CARTORIO("Cart�rio"), 
	COM_TERAP("Comunidade Terap�utica"), 
	CORREIOS("Correios"),
	ENS_BASICO("Escola Ensino B�sico"), 
	ENS_SUPERIOR("Escola Ensino Superior"), 
	INSS("INSS"), 
	RFB("Receita Federal"), 
	SINE("Sistema Nacional de Emprego"), 
	UBS("Unidade B�sica de Sa�de"),
	LOTERICA("Lot�rica");
	
	private String descricao;
	
	private Categoria(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
