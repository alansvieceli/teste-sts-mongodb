package com.alan.teste_mongodb.dto;

import java.io.Serializable;

import com.alan.teste_mongodb.domain.Usuario;

public class AutorDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String nome;
	
	public AutorDTO() {
		
	}
	
	public AutorDTO(Usuario u) {
		this.id = u.getId();
		this.nome = u.getNome();		
	}

	public String getId() {
		return id;
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
	
	

}
