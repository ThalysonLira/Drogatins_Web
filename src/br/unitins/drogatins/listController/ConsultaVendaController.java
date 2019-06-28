package br.unitins.drogatins.listController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.drogatins.application.Util;
import br.unitins.drogatins.dao.VendaDAO;
import br.unitins.drogatins.model.Venda;

@Named
@ViewScoped
public class ConsultaVendaController implements Serializable{

	private static final long serialVersionUID = 1850977902015907244L;
	 
	private String busca;
	private Venda venda;

	private List<Venda> listaVenda = null;
	
	public void pesquisar() {
		listaVenda = null;
	}
	
	public void visualizarVenda(Venda venda) {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("vendaFlash", venda);
		
		Util.redirect("detalhesvenda.xhtml");
	}
	
	public String getBusca() {
		return busca;
	}

	public void setBusca(String busca) {
		this.busca = busca;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public List<Venda> getListaVenda() {
		if (listaVenda == null) {
			VendaDAO dao = new VendaDAO();
			
			setListaVenda(dao.findAll());
			dao.closeConnection();
			
			if (listaVenda == null)
				listaVenda = new ArrayList<Venda>();
		}
		
		return listaVenda;
	}

	public void setListaVenda(List<Venda> listaVenda) {
		this.listaVenda = listaVenda;
	}
}