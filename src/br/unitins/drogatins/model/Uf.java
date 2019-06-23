package br.unitins.drogatins.model;

public enum Uf {
	AC(0, "AC"), AL(1, "AL"), AP(2, "AP"), AM(3, "AM"), BA(4, "BA"), CE(5, "CE"), DF(6, "DF"), ES(7, "ES"), GO(8, "GO"),
	MA(9, "MA"), MT(10, "MT"), MS(11, "MS"), MG(12, "MG"), PA(13, "PA"), PB(14, "PB"), PR(15, "PR"), PE(16, "PE"),
	PI(17, "PI"), RJ(18, "RJ"), RN(19, "RN"), RS(20, "RS"), RO(21, "RO"), RR(22, "RR"), SC(23, "SC"), SP(24, "SP"),
	SE(25, "SE"), TO(26, "TO"), EX(27, "EX");

	private int value;
	private String label;

	private Uf(int value, String label) {
		this.value = value;
		this.label = label;
	}

	public int getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}
	
	// Retorna um uf a partir de um valor inteiro
	public static Uf valueOf(int value) {
		for (Uf uf : Uf.values()) {
			if (uf.getValue() == value) {
				return uf;
			}
		}
		return null;
	}
}