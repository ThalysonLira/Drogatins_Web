package br.unitins.drogatins.model;

import java.time.LocalDate;
import java.util.List;

public class Venda {
	
	private Integer id;
	private LocalDate data;
	private Usuario cliente;
	private List<ItemVenda> listaItemVenda;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public LocalDate getData() {
		return data;
	}
	
	public void setData(LocalDate data) {
		this.data = data;
	}
	
	public Usuario getCliente() {
		return cliente;
	}
	
	public void setCliente(Usuario cliente) {
		this.cliente = cliente;
	}
	
	public List<ItemVenda> getListaItemVenda() {
		return listaItemVenda;
	}
	
	public void setListaItemVenda(List<ItemVenda> listaItemVenda) {
		this.listaItemVenda = listaItemVenda;
	}
}