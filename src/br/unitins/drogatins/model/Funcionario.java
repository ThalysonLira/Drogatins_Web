package br.unitins.drogatins.model;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

public class Funcionario extends Usuario {
	
	@CPF(message="Formato do cpf incorreto.")
	private String cpf;
	private Usuario usuario;
	private LocalDate dataNascimento;
	private Sexo sexo;
	private Situacao situacao;
	
	public Funcionario() {
		// TODO Auto-generated constructor stub
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}
}