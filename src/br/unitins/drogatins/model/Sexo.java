package br.unitins.drogatins.model;

public enum Sexo {
	FEMININO(0, "Feminino"),
	MASCULINO(1, "Masculino");

	private int value;
	private String label;

	private Sexo(int value, String label) {
		this.value = value;
		this.label = label;
	}

	public int getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}

	// Retorna o sexo a partir de um valor inteiro
	public static Sexo valueOf(int value) {
		for (Sexo sexo : Sexo.values()) {
			if (sexo.getValue() == value) {
				return sexo;
			}
		}
		return null;
	}
}