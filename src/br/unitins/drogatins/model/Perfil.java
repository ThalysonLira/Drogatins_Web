package br.unitins.drogatins.model;

public enum Perfil {
	ADMINISTRADOR(1, "Administrador"),
	GERENTE(2, "Gerente"),
	FUNCIONARIO(3, "Funcionario"),
	FORNECEDOR(4, "Fornecedor"),
	CLIENTE(5, "Cliente");

	private int value;
	private String label;
	
	Perfil(int value, String label) {
		this.value = value;
		this.label = label;
	}

	public int getValue() {
		return value;
	}

	public String getLabel() {
		return label;
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