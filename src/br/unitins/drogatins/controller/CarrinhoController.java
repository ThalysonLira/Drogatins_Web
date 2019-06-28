package br.unitins.drogatins.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.drogatins.application.Session;
import br.unitins.drogatins.application.Util;
import br.unitins.drogatins.dao.EstoqueDAO;
import br.unitins.drogatins.dao.VendaDAO;
import br.unitins.drogatins.model.ItemEstoque;
import br.unitins.drogatins.model.ItemVenda;
import br.unitins.drogatins.model.Usuario;
import br.unitins.drogatins.model.Venda;

@Named
@ViewScoped
public class CarrinhoController implements Serializable {

	private static final long serialVersionUID = 1878498306666000924L;

	private Venda venda;
	private Double valorTotal;

	public void adicionar(int id) {
		// pesquisa o item selecionado
		EstoqueDAO dao = new EstoqueDAO();
		ItemEstoque itemEstoque = dao.findById(id);

		// verifica se existe o carrinho na sessao
		if (Session.getInstance().getAttribute("carrinho") == null) {
			// adiciona o carrinho na sessao
			Session.getInstance().setAttribute("carrinho", new ArrayList<ItemVenda>());
		}
		// busca o carrinho da sessao
		List<ItemVenda> carrinho = (List<ItemVenda>) Session.getInstance().getAttribute("carrinho");

		// cria um item de venda
		ItemVenda item = new ItemVenda();

		// reservando item do estoque para venda
		itemEstoque.setQuant(itemEstoque.getQuant() - 1);
		if (dao.update(itemEstoque)) {
			// informando item a ser vendido
			item.setItem(itemEstoque);
			item.setValor(itemEstoque.getProduto().getValor());

			// adiciona o item no objeto de referencia do carrinho
			carrinho.add(item);
		} else {
			Util.addMessageError("Erro ao atualizar!");
		}

		dao.closeConnection();

		// atualiza o carrinho
		Session.getInstance().setAttribute("carrinho", carrinho);

		Util.addMessageSucess("Adicionado com Sucesso!");
	}

	public void remover(ItemVenda item) {
		EstoqueDAO dao = new EstoqueDAO();

		// verifica se existe o carrinho na sessao
		if (Session.getInstance().getAttribute("carrinho") == null) {
			// adiciona o carrinho na sessao
			Session.getInstance().setAttribute("carrinho", new ArrayList<ItemVenda>());
		}
		// busca o carrinho da sessao
		List<ItemVenda> carrinho = (List<ItemVenda>) Session.getInstance().getAttribute("carrinho");

		// realocando item no estoque
		item.getItem().setQuant(item.getItem().getQuant() + 1);
		if (dao.update(item.getItem())) {
			// adiciona o item no objeto de referencia do carrinho
			carrinho.remove(item);
		} else {
			Util.addMessageError("Erro ao atualizar!");
		}

		dao.closeConnection();

		// atualiza o carrinho
		Session.getInstance().setAttribute("carrinho", carrinho);

		Util.addMessageSucess("Removido com Sucesso!");
	}

	public void finalizar() {
		// busca o carrinho da sessao
		List<ItemVenda> carrinho = (List<ItemVenda>) Session.getInstance().getAttribute("carrinho");

		if (carrinho != null && carrinho.size() > 0) {
			getVenda().setCliente((Usuario) Session.getInstance().getAttribute("usuarioLogado"));
			getVenda().setTotal(getValorTotal());
			VendaDAO dao = new VendaDAO();
			dao.create(getVenda());

			carrinho = null;

		} else {
			Util.addMessageAlert("Ao menos um item deve ser adicionado no carrinho!");
		}

	}

	public Double getValorTotal() {
		valorTotal = 0.0;

		// verifica se existe o carrinho na sessao
		if (Session.getInstance().getAttribute("carrinho") == null) {
			// adiciona o carrinho na sessao
			Session.getInstance().setAttribute("carrinho", new ArrayList<ItemVenda>());
		}
		// busca o carrinho da sessao
		List<ItemVenda> carrinho = (List<ItemVenda>) Session.getInstance().getAttribute("carrinho");

		if (carrinho.size() > 0) {
			for (int i = 0; i < carrinho.size(); i++) {
				double desconto = carrinho.get(i).getValor() * carrinho.get(i).getItem().getProduto().getDesconto()
						/ 100;
				valorTotal += carrinho.get(i).getValor() - desconto;
			}
		}

		return valorTotal;
	}

	public boolean verificarDisponibilidade(ItemEstoque item) {
		if (item.getQuant() > 0)
			return false;
		else
			return true;
	}

	public Venda getVenda() {
		if (venda == null) {
			venda = new Venda();
		}
		// obtendo o carrinho da sessao
		List<ItemVenda> carrinho = (List<ItemVenda>) Session.getInstance().getAttribute("carrinho");

		if (carrinho != null)
			venda.setListaItemVenda(carrinho);
		else
			venda.setListaItemVenda(new ArrayList<ItemVenda>());

		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}
}