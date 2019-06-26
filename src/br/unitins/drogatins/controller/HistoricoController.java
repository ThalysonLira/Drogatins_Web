package br.unitins.drogatins.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import br.unitins.drogatins.application.Session;
import br.unitins.drogatins.application.Util;
import br.unitins.drogatins.dao.VendaDAO;
import br.unitins.drogatins.model.Usuario;
import br.unitins.drogatins.model.Venda;

public class HistoricoController implements Serializable {

	private static final long serialVersionUID = -4957743572910802170L;

	private List<Venda> listaVenda;

	// retorna todas a vendas do usuario logado
	public List<Venda> getListaVenda() {
		if (listaVenda == null) {
			Usuario usuario = (Usuario) Session.getInstance().getAttribute("usuarioLogado");
			if (usuario == null) {
				listaVenda = new ArrayList<Venda>();
				return listaVenda;
			}
			VendaDAO dao = new VendaDAO();
			// buscando todas as vendas do usuario logado
			listaVenda = dao.findByCliente(usuario);
			// se o retorno da consulta for nula ... inicializar a lista para evitar erro de
			// nullpointer
			if (listaVenda == null)
				listaVenda = new ArrayList<Venda>();
		}

		return listaVenda;
	}

	public void detalhes(Venda venda) {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("vendaFlash", venda);
		Util.redirect("/Drogatins_Web/faces/pages/detalhesvenda.xhtml");
	}
}