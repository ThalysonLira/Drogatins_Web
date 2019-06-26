package br.unitins.drogatins.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.drogatins.application.Util;
import br.unitins.drogatins.dao.EstoqueDAO;
import br.unitins.drogatins.dao.ProdutoDAO;
import br.unitins.drogatins.model.ItemEstoque;
import br.unitins.drogatins.model.Produto;

@Named
@ViewScoped
public class CadastroEstoqueController implements Serializable {

	private static final long serialVersionUID = 3926415784563098038L;

	private ItemEstoque item;
	private List<ItemEstoque> estoque = null;

	private String busca;
	private List<Produto> produtos = null;

	public CadastroEstoqueController() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		item = (ItemEstoque) flash.get("itemFlash");
	}

	public void editar(int id) {
		EstoqueDAO dao = new EstoqueDAO();
		setItem(dao.findById(id));
	}

	public void salvar() {
		EstoqueDAO dao = new EstoqueDAO();

		if (getItem().getId() == null) {
			if (dao.create(getItem())) {
				limpar();
			}
		} else {
			if (dao.update(getItem())) {
				limpar();
			}
		}

		dao.closeConnection();
	}

	public void voltar() {
		Util.redirect("consultaestoque.xhtml");
	}

	public void limpar() {
		item = null;
	}

	public List<Produto> getProdutos() {
		if (produtos == null) {
			ProdutoDAO dao = new ProdutoDAO();
			produtos = dao.findByNome(getBusca());
			if (produtos == null)
				produtos = new ArrayList<Produto>();
			dao.closeConnection();
		}

		return produtos;
	}

	public String getBusca() {
		return busca;
	}

	public void setBusca(String busca) {
		this.busca = busca;
	}

	public List<ItemEstoque> getEstoque() {
		if (estoque == null) {
			EstoqueDAO dao = new EstoqueDAO();
			estoque = dao.findAll();
			if (estoque == null)
				estoque = new ArrayList<ItemEstoque>();
			dao.closeConnection();
		}

		return estoque;
	}

	public void setEstoque(List<ItemEstoque> estoque) {
		this.estoque = estoque;
	}

	public ItemEstoque getItem() {
		if (item == null)
			setItem(new ItemEstoque());
		return item;
	}

	public void setItem(ItemEstoque item) {
		this.item = item;
	}
}