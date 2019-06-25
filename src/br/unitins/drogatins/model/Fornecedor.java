package br.unitins.drogatins.model;

public class Fornecedor extends Usuario {

	private String cnpj;
	private Situacao situacao;
	
	public Fornecedor() {
		// TODO Auto-generated constructor stub
		super();
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}
}