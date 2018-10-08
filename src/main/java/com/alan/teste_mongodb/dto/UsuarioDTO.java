package com.alan.teste_mongodb.dto;

import java.io.Serializable;

import com.alan.teste_mongodb.domain.Usuario;

/*
 * 
	Referências:
	https://pt.stackoverflow.com/questions/31362/o-que-é-um-dto
	DTO (Data Transfer Object): é um objeto que tem o papel de carregar dados das entidades de forma simples,
	podendo inclusive "projetar" apenas alguns dados da entidade original. Vantagens:
	- Otimizar o tráfego (trafegando menos dados)
	- Evitar que dados de interesse exclusivo do sistema fiquem sendo expostos (por exemplo: senhas, dados de
	auditoria como data de criação e data de atualização do objeto, etc.)
	- Customizar os objetos trafegados conforme a necessidade de cada requisição (por exemplo: para alterar
	um produto, você precisa dos dados A, B e C; já para listar os produtos, eu preciso dos dados A, B e a
	categoria de cada produto, etc.).
 * 
 */

public class UsuarioDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String nome;
	private String email;
	
	
	public UsuarioDTO() {		
	}	
	
	public UsuarioDTO(Usuario u) {
		this.id = u.getId();
		this.nome = u.getNome();
		this.email = u.getEmail();		
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
