package br.unitins.drogatins.controller;

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
import br.unitins.drogatins.model.Perfil;
import br.unitins.drogatins.model.Sexo;
import br.unitins.drogatins.model.Situacao;

@Named
@ViewScoped
public class CadastroFuncionarioController implements Serializable {

	private static final long serialVersionUID = 2769788590189511945L;

	private Funcionario funcionario;

	private List<Funcionario> listaFuncionario = null;

	public CadastroFuncionarioController() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		funcionario = (Funcionario) flash.get("funcionarioFlash");
	}

	public void editar(int id) {
		FuncionarioDAO dao = new FuncionarioDAO();
		setFuncionario(dao.findById(id));
	}

	public void salvar() {
		// encriptando a senha do fornecedor
		getFuncionario().setSenha(Util.encrypt(getFuncionario().getSenha()));

		FuncionarioDAO dao = new FuncionarioDAO();
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();

		if (getFuncionario().getId() == null) {
			flash.put("usuarioFlash", getFuncionario());
			Util.redirect("cadastroendereco.xhtml");
		} else {
			if (dao.update(getFuncionario())) {
				flash.put("enderecoFlash", getFuncionario().getEndereco());
				Util.redirect("cadastroendereco.xhtml");
			}
		}

		dao.closeConnection();
	}

	public void voltar() {
		Util.redirect("consulta.xhtml");
	}

	public void limpar() {
		funcionario = null;
	}

	public List<Funcionario> getListaFuncionario() {
		if (listaFuncionario == null) {
			FuncionarioDAO dao = new FuncionarioDAO();
			listaFuncionario = dao.findAll();
			if (listaFuncionario == null)
				listaFuncionario = new ArrayList<Funcionario>();
			dao.closeConnection();
		}
		return listaFuncionario;
	}

	public Sexo[] getListaSexo() {
		return Sexo.values();
	}

	public Perfil[] getListaPerfil() {
		return Perfil.values();
	}

	public Situacao[] getListaSituacao() {
		return Situacao.values();
	}

	public Funcionario getFuncionario() {
		if(funcionario == null)
			setFuncionario(new Funcionario());
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
}