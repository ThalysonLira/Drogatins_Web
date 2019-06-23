package br.unitins.drogatins.model;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CPF;

public class Funcionario extends Usuario {
	
	@NotBlank(message="O nome deve ser informado.")
	private String nome;
	
	@CPF(message="Formato do cpf incorreto.")
	private String cpf;
	private LocalDate dataNascimento;
	private Endereco endereco;
	private Sexo sexo;
	private Situacao situacao;
	
	public Funcionario() {
		// TODO Auto-generated constructor stub
		super();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
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