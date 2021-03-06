package br.unitins.drogatins.controller;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.drogatins.application.Util;
import br.unitins.drogatins.dao.EnderecoDAO;
import br.unitins.drogatins.model.Cliente;
import br.unitins.drogatins.model.Endereco;
import br.unitins.drogatins.model.Fornecedor;
import br.unitins.drogatins.model.Funcionario;
import br.unitins.drogatins.model.Uf;
import br.unitins.drogatins.model.Usuario;

@Named
@ViewScoped
public class CadastroEnderecoController implements Serializable {

	private static final long serialVersionUID = 3172197352750959460L;

	private Usuario usuario;
	private Endereco endereco;

	public CadastroEnderecoController() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		usuario = (Usuario) flash.get("usuarioFlash");
		endereco = (Endereco) flash.get("enderecoFlash");
	}
	
	public void editar(int id) {
		EnderecoDAO dao = new EnderecoDAO();
		setEndereco(dao.findById(id));
	}
	
	public void salvar() {
		EnderecoDAO dao = new EnderecoDAO();
		if (getEndereco().getId() == null) {
			getUsuario().setEndereco(getEndereco());
			
			if(Cliente.class == usuario.getClass()) {
				dao.createCliente((Cliente) getUsuario());
			}else if(Funcionario.class == usuario.getClass()) {
				dao.createFuncionario((Funcionario) getUsuario());
			}else if(Fornecedor.class == usuario.getClass()) {
				dao.createFornecedor((Fornecedor) getUsuario());
			}
			limpar();
		}
		else {
			if (dao.update(getEndereco())) {
				limpar();
				
				
			}
		}
		
		dao.closeConnection();
	}
	
	public void voltar() {
		Util.redirect("consultaendereco.xhtml");
	}

	public void limpar() {
		endereco = null;
	}
	
	public Uf[] getListaUf() {
		return Uf.values();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Endereco getEndereco() {
		if(endereco == null)
			setEndereco(new Endereco());
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
}