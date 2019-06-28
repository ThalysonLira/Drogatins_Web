package br.unitins.drogatins.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.drogatins.application.Session;
import br.unitins.drogatins.application.Util;
import br.unitins.drogatins.model.ItemVenda;
import br.unitins.drogatins.model.Usuario;

@Named
@ViewScoped
public class TemplateController implements Serializable {
	
	private static final long serialVersionUID = -1457991834290264968L;
	
	Usuario usuarioLogado = null;
	
	int qtdItensCarrinho;
	
	public TemplateController() {
		usuarioLogado = (Usuario) Session.getInstance().getAttribute("usuarioLogado");
	}
	
	public void encerrarSessao() {
		Session.getInstance().invalidateSession();
		Util.redirect("/Drogatins_Web/faces/login.xhtml");
	}
	
	public void redirecionar(String pagina) {
		Util.redirect(pagina);
	}
	
	public int getQtdItensCarrinho() {
		qtdItensCarrinho = 0;
		List<ItemVenda> itens = (List<ItemVenda>) Session.getInstance().getAttribute("carrinho");
		if (itens != null)
			qtdItensCarrinho = itens.size();
		return qtdItensCarrinho;	
		
	}
	
	public boolean bloquearAcesso(int perfil) {	
		if(usuarioLogado.getPerfil().getValue() > perfil)
			return true;
		else
			return false;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
}