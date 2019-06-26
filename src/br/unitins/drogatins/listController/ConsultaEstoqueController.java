package br.unitins.drogatins.listController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.drogatins.application.Util;
import br.unitins.drogatins.dao.EstoqueDAO;
import br.unitins.drogatins.model.ItemEstoque;

@Named
@ViewScoped
public class ConsultaEstoqueController implements Serializable {

	private static final long serialVersionUID = 3236696511479018640L;

	private String busca;
	private ItemEstoque item;

	private List<ItemEstoque> estoque = null;

	public void novo() {
		Util.redirect("cadastroestoque.xhtml");
	}

	public void editar(ItemEstoque item) {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("itemFlash", item);

		Util.redirect("cadastroestoque.xhtml");
	}

	public void excluir(ItemEstoque item) {
		EstoqueDAO dao = new EstoqueDAO();
		if (dao.delete(item.getId())) {
			limpar();
			// para atualizar o data table
			estoque = null;
		}
		dao.closeConnection();
	}

	public void pesquisar() {
		estoque = null;
	}

	public void voltar() {
		Util.redirect("menu.xhtml");
	}

	public void limpar() {
		item = null;
	}

	public List<ItemEstoque> getEstoque() {
		if (estoque == null) {
			EstoqueDAO dao = new EstoqueDAO();
			estoque = dao.findByNome(getBusca());

			if (estoque == null)
				estoque = new ArrayList<ItemEstoque>();
			dao.closeConnection();
		}
		
		return estoque;
	}

	public String getBusca() {
		return busca;
	}

	public void setBusca(String busca) {
		this.busca = busca;
	}

	public ItemEstoque getItemEstoque() {
		if (item == null)
			item = new ItemEstoque();
		return item;
	}

	public void setItemEstoque(ItemEstoque item) {
		this.item = item;
	}
}