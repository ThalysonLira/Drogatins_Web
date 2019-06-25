package br.unitins.drogatins.listController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.drogatins.application.Util;
import br.unitins.drogatins.dao.FuncionarioDAO;
import br.unitins.drogatins.model.Funcionario;

@Named
@ViewScoped
public class ConsultaFuncionarioController implements Serializable{
	
	private static final long serialVersionUID = 7043458353005295009L;
	
	private String busca;
	private Funcionario funcionario;

	private List<Funcionario> listaFuncionario = null;
	
	public void novo() {
		Util.redirect("cadastrofuncionario.xhtml");
	}
	
	public void editar(Funcionario funcionario) {		
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("funcionarioFlash", funcionario);
		
		Util.redirect("cadastrofuncionario.xhtml");
	}
	
	public void excluir(Funcionario funcionario) {
		FuncionarioDAO dao = new FuncionarioDAO();
		if (dao.delete(funcionario.getId())) {
			limpar();
			// para atualizar o data table
			listaFuncionario = null;
		}
		dao.closeConnection();
	}
	
	public void pesquisar() {
		listaFuncionario = null;
	}
	
	public void voltar() {
		Util.redirect("menu.xhtml");
	}

	public void limpar() {
		funcionario = null;
	}
	
	public List<Funcionario> getListaFuncionario() {
		if (listaFuncionario == null) {
			FuncionarioDAO dao = new FuncionarioDAO();			
			listaFuncionario = dao.findByNome(getBusca());
			
			if (listaFuncionario == null)
				listaFuncionario = new ArrayList<Funcionario>();
			dao.closeConnection();
		}
		return listaFuncionario;
	}

	public String getBusca() {
		return busca;
	}

	public void setBusca(String busca) {
		this.busca = busca;
	}

	public Funcionario getFuncionario() {
		if (funcionario == null)
			funcionario = new Funcionario();
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
}