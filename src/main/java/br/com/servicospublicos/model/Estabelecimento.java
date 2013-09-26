package br.com.servicospublicos.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="estabelecimentos")
@JsonSerialize(include=Inclusion.NON_NULL)
@JsonIgnoreProperties(value={"status"})
public class Estabelecimento {
	
	public enum Status {HIDDEN, VISIBLE}
	
	public enum Categoria {UBS, CARTORIO, INSS, RFB, ASS_SOCIAL, COM_TERAP, SINE, ENS_BASICO}
	
	private String id;
	private Categoria categoria;
	private String nome;
	private Localizacao localizacao;
	private Contato contato;
	private String atendimento;
	private Boolean publico;
	private Status status;
	
	public String getId() {
		return id;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}
	
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Localizacao getLocalizacao() {
		return localizacao;
	}
	
	public void setLocalizacao(Localizacao localizacao) {
		this.localizacao = localizacao;
	}
	
	public Contato getContato() {
		return contato;
	}
	
	public void setContato(Contato contato) {
		this.contato = contato;
	}
	
	public String getAtendimento() {
		return atendimento;
	}
	
	public void setAtendimento(String atendimento) {
		this.atendimento = atendimento;
	}
	
	public Boolean getPublico() {
		return publico;
	}

	public void setPublico(Boolean publico) {
		this.publico = publico;
	}

	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}

}
