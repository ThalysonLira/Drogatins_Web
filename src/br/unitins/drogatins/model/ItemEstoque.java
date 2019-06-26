package br.unitins.drogatins.model;

public class ItemEstoque {
	
	private Integer id;
	private Produto produto;
	private String nome;
	private Integer quant;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Produto getProduto() {
		if (produto == null)
			setProduto(new Produto());
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getQuant() {
		return quant;
	}
	
	public void setQuant(Integer quant) {
		this.quant = quant;
	}
}