package br.unitins.drogatins.listController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.drogatins.application.Util;
import br.unitins.drogatins.dao.FornecedorDAO;
import br.unitins.drogatins.model.Fornecedor;

@Named
@ViewScoped
public class ConsultaFornecedorController implements Serializable{

	private static final long serialVersionUID = 6917188508735657586L;
	
	private String busca;
	private Fornecedor fornecedor;

	private List<Fornecedor> listaFornecedor = null;
	
	public void novo() {
		Util.redirect("cadastrofornecedor.xhtml");
	}
	
	public void editar(Fornecedor fornecedor) {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("fornecedorFlash", fornecedor);
		
//		Session.getInstance().setAttribute("fornecedorSession", fornecedor);
		
		Util.redirect("cadastrofornecedor.xhtml");
	}
	
	public void excluir(Fornecedor fornecedor) {
		FornecedorDAO dao = new FornecedorDAO();
		if (dao.delete(fornecedor.getId())) {
			limpar();
			// para atualizar o data table
			listaFornecedor = null;
		}
		dao.closeConnection();
	}
	
	public void pesquisar() {
		listaFornecedor = null;
	}
	
	public void voltar() {
		Util.redirect("menu.xhtml");
	}

	public void limpar() {
		fornecedor = null;
	}
	
	public List<Fornecedor> getListaFornecedor() {
		if (listaFornecedor == null) {
			FornecedorDAO dao = new FornecedorDAO();			
			listaFornecedor = dao.findByNome(getBusca());
			
			if (listaFornecedor == null)
				listaFornecedor = new ArrayList<Fornecedor>();
			dao.closeConnection();
		}
		return listaFornecedor;
	}

	public String getBusca() {
		return busca;
	}

	public void setBusca(String busca) {
		this.busca = busca;
	}

	public Fornecedor getFornecedor() {
		if (fornecedor == null)
			fornecedor = new Fornecedor();
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
}