package br.unitins.drogatins.model;

import br.unitins.drogatins.model.Usuario;

public class Endereco {
	
	private Integer id;
	
	private Usuario usuario;
	private String rua;
	private String numero;
	private String cep;
	private String bairro;
	private String cidade;
	private Uf unidadeFederal;
	private String complemento;
	
	public Endereco() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Uf getUnidadeFederal() {
		return unidadeFederal;
	}

	public void setUnidadeFederal(Uf unidadeFederal) {
		this.unidadeFederal = unidadeFederal;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
}