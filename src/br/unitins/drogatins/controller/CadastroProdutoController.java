package br.unitins.drogatins.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.drogatins.application.Util;
import br.unitins.drogatins.dao.ProdutoDAO;
import br.unitins.drogatins.model.Produto;

@Named
@ViewScoped
public class CadastroProdutoController implements Serializable {

	private static final long serialVersionUID = -1790392894372578715L;

	private Produto produto;

	private List<Produto> listaProduto = null;

	public CadastroProdutoController() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		produto = (Produto) flash.get("produtoFlash");
	}

	public void editar(int id) {
		ProdutoDAO dao = new ProdutoDAO();
		setProduto(dao.findById(id));
	}

	public void salvar() {
		ProdutoDAO dao = new ProdutoDAO();
		if (getProduto().getId() == null) {
			if (dao.create(produto)) {
				limpar();
			}
		} else {
			if (dao.update(produto)) {
				limpar();
			}
		}

		dao.closeConnection();
	}
	
	public void voltar() {
		Util.redirect("consultaproduto.xhtml");
	}

	public void limpar() {
		produto = null;
	}

	public List<Produto> getListaProduto() {
		if (listaProduto == null) {
			ProdutoDAO dao = new ProdutoDAO();
			listaProduto = dao.findAll();
			if (listaProduto == null)
				listaProduto = new ArrayList<Produto>();
			dao.closeConnection();
		}
		return listaProduto;
	}

	public Produto getProduto() {
		if(produto == null)
			setProduto(new Produto());
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
}