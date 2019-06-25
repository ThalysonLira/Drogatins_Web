package br.unitins.drogatins.model;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

public class Cliente extends Usuario {
	
	@CPF(message="Formato do cpf incorreto.")
	private String cpf;
	private LocalDate dataNascimento;
	private Sexo sexo;
	
	public Cliente() {
		// TODO Auto-generated constructor stub
		super();
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

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
}