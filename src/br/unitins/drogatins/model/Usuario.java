package br.unitins.drogatins.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Usuario {
	
	private Integer id;
	
	@NotBlank(message="O nome deve ser informado.")
	private String nome;
	private Endereco endereco;
	
	@Email(message="Email inválido.")
	private String login;
	
	@Size(min=8, message="Tamanho incomátível, mínimo de 8 caracteres")
	@Size(max=20, message="Tamanho incomátível, máximo de 25 caracteres")
	private String senha;
	private Perfil perfil;

	public Usuario() {

	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
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