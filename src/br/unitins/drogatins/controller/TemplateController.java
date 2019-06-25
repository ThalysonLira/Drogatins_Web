package br.unitins.drogatins.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.drogatins.application.Session;
import br.unitins.drogatins.application.Util;
import br.unitins.drogatins.model.Usuario;

@Named
@ViewScoped
public class TemplateController implements Serializable {
	
	private static final long serialVersionUID = -1457991834290264968L;
	
	Usuario usuarioLogado = null;
	
	public TemplateController() {
		usuarioLogado = (Usuario) Session.getInstance().getAttribute("usuarioLogado");
	}
	
	public void encerrarSessao() {
		Session.getInstance().invalidateSession();
		Util.redirect("login.xhtml");
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
}