package br.unitins.drogatins.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class Usuario {
	
	private Integer id;
	
	@Email(message="Email inválido.")
	private String login;
	
	@Size(min=8, message="Tamanho incomátível, mínimo de 8 caracteres")
	@Size(max=20, message="Tamanho incomátível, máximo de 25 caracteres")
	private String senha;
	private Perfil perfil;

	public Usuario() {

	}

	public Usuario(Integer id, String login, String senha, Perfil perfil) {
		this.id = id;
		this.login = login;
		this.senha = senha;
		this.perfil = perfil;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
}