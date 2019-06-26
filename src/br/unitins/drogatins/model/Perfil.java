package br.unitins.drogatins.model;

import java.util.Arrays;
import java.util.List;

public enum Perfil {
	ADMINISTRADOR(1, "Administrador", Arrays.asList("login.xhtml", "template.xhtml", "principal.xhtml", "carrinho.xhtml",
			"cadastroendereco.xhtml", "cadastrocliente.xhtml", "consultacliente.xhtml", "cadastrofuncionario.xhtml",
			"consultafuncionario.xhtml", "cadastroestoque.xhtml", "consultaestoque.xhtml", "cadastrofornecedor.xhtml",
			"consultafornecedor.xhtml", "cadastroproduto.xhtml", "consultaproduto.xhtml", "acessonegado.xhtml")),
	GERENTE(2, "Gerente", Arrays.asList("login.xhtml", "template.xhtml", "principal.xhtml", "carrinho.xhtml",
			"cadastroendereco.xhtml", "cadastrocliente.xhtml", "consultacliente.xhtml", "cadastrofuncionario.xhtml",
			"consultafuncionario.xhtml", "cadastroestoque.xhtml", "consultaestoque.xhtml", "cadastrofornecedor.xhtml",
			"consultafornecedor.xhtml", "cadastroproduto.xhtml", "consultaproduto.xhtml", "acessonegado.xhtml")),
	FUNCIONARIO(3, "Funcionario", Arrays.asList("login.xhtml", "template.xhtml", "principal.xhtml", "carrinho.xhtml",
			"cadastroendereco.xhtml", "cadastrocliente.xhtml", "consultacliente.xhtml", "cadastroestoque.xhtml",
			"consultaestoque.xhtml", "cadastrofornecedor.xhtml", "consultafornecedor.xhtml", "cadastroproduto.xhtml",
			"consultaproduto.xhtml", "acessonegado.xhtml")),
	FORNECEDOR(4, "Fornecedor", Arrays.asList("login.xhtml", "principal.xhtml", "template.xhtml", "cadastroendereco.xhtml",
			"cadastroestoque.xhtml", "consultaestoque.xhtml", "acessonegado.xhtml")),
	CLIENTE(5, "Cliente", Arrays.asList("login.xhtml", "template.xhtml", "principal.xhtml",
			"cadastroendereco.xhtml", "carrinho.xhtml", "acessonegado.xhtml"));

	private int value;
	private String label;
	private List<String> pages;
	
	Perfil(int value, String label, List<String> pages) {
		this.value = value;
		this.label = label;
		this.pages = pages;
	}

	public int getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}
	
	public List<String> getPages() {
		return pages;
	}
	
	// Retorna um perfil a partir de um valor inteiro
	public static Perfil valueOf(int value) {
		for (Perfil perfil : Perfil.values()) {
			if (perfil.getValue() == value) {
				return perfil;
			}
		}
		return null;
	}
}