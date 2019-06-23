package br.unitins.drogatins.model;

public enum Situacao {
	ATIVO(0, "Ativo"),
	INATIVO(1, "Inativo");

	private int value;
	private String label;

	private Situacao(int value, String label) {
		this.value = value;
		this.label = label;
	}

	public int getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}

	// Retorna uma situacao a partir de um valor inteiro
	public static Situacao valueOf(int value) {
		for (Situacao situacao : Situacao.values()) {
			if (situacao.getValue() == value) {
				return situacao;
			}
		}
		return null;
	}
}