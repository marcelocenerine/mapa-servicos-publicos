package br.com.servicospublicos.model;

import java.util.Date;

public class ItemAvaliacao {

	private Integer nota;
	private String comentario;
	private String usuario;
	private Date data;
	
	public Integer getNota() {
		return nota;
	}
	
	public void setNota(Integer nota) {
		this.nota = nota;
	}
	
	public String getComentario() {
		return comentario;
	}
	
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}
}
