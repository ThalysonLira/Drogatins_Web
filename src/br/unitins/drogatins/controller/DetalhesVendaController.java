package br.unitins.drogatins.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.drogatins.dao.VendaDAO;
import br.unitins.drogatins.model.ItemVenda;
import br.unitins.drogatins.model.Venda;

@Named
@ViewScoped
public class DetalhesVendaController  implements Serializable {

	private static final long serialVersionUID = 7273382270747267265L;
	
	private Venda venda;
	
	public DetalhesVendaController() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		venda = (Venda) flash.get("vendaFlash");
	}
	
	public Venda getVenda() {
		if (venda == null) {
			venda = new Venda();
			venda.setListaItemVenda(new ArrayList<ItemVenda>());
		}
		
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}
}