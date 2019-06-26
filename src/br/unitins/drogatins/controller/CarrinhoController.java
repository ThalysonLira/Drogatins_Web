package br.unitins.drogatins.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
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
@RequestScoped
public class CarrinhoController implements Serializable {

	private static final long serialVersionUID = 1878498306666000924L;

	private Venda venda;

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

		// informando item a ser vendido
		item.setItem(itemEstoque);
		item.setValor(itemEstoque.getProduto().getValor());

		// adiciona o item no objeto de referencia do carrinho
		carrinho.add(item);

		// atualiza o carrinho
		Session.getInstance().setAttribute("carrinho", carrinho);

		Util.addMessageSucess("Adicionado com Sucesso!");
	}

	public void remover(ItemVenda item) {
		// verifica se existe o carrinho na sessao
		if (Session.getInstance().getAttribute("carrinho") == null) {
			// adiciona o carrinho na sessao
			Session.getInstance().setAttribute("carrinho", new ArrayList<ItemVenda>());
		}
		// busca o carrinho da sessao
		List<ItemVenda> carrinho = 
				(List<ItemVenda>) Session.getInstance().getAttribute("carrinho");

		// adiciona o item no objeto de referencia do carrinho
		carrinho.remove(item);
		
		// realocando item no estoque
		item.getItem().setQuant(item.getItem().getQuant() + 1);

		// atualiza o carrinho
		Session.getInstance().setAttribute("carrinho", carrinho);

		Util.addMessageSucess("Removido com Sucesso!");
	}

	public void finalizar() {
		getVenda().setCliente((Usuario) Session.getInstance().getAttribute("usuarioLogado"));
		VendaDAO dao = new VendaDAO();
		dao.create(getVenda());
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