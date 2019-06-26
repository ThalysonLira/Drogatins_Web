package br.unitins.drogatins.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.drogatins.application.Util;
import br.unitins.drogatins.dao.ClienteDAO;
import br.unitins.drogatins.model.Cliente;
import br.unitins.drogatins.model.Perfil;
import br.unitins.drogatins.model.Sexo;

@Named
@ViewScoped
public class CadastroClienteController implements Serializable {

	private static final long serialVersionUID = 8713033117401907672L;

	private Cliente cliente;

	private List<Cliente> listaCliente = null;

	public CadastroClienteController() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		cliente = (Cliente) flash.get("clienteFlash");
	}

	public void editar(int id) {
		ClienteDAO dao = new ClienteDAO();
		setCliente(dao.findById(id));
	}

	public void salvar() {
		// encriptando a senha do fornecedor
//		getFornecedor().setSenha(Util.encrypt(getFornecedor().getSenha()));

		ClienteDAO dao = new ClienteDAO();
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();

		if (getCliente() == null) {
			flash.put("usuarioFlash", getCliente());
			Util.redirect("cadastroendereco.xhtml");
		} else {
			if (dao.update(getCliente())){
				flash.put("enderecoFlash", getCliente().getEndereco());
				Util.redirect("cadastroendereco.xhtml");
			}
		}

		dao.closeConnection();
	}

	public void incluir() {
		// encriptando a senha do cliente
//		getCliente().setSenha(Util.encrypt(getCliente().getSenha()));

		ClienteDAO dao = new ClienteDAO();
		if (dao.create(getCliente())) {
			limpar();
		}
		dao.closeConnection();
	}

	public void alterar() {
		// encriptando a senha do cliente
//		getCliente().setSenha(Util.encrypt(getCliente().getSenha()));

		ClienteDAO dao = new ClienteDAO();
		if (dao.update(getCliente())) {
			limpar();
		}
		dao.closeConnection();
	}

	public void voltar() {
		Util.redirect("consulta.xhtml");
	}

	public void limpar() {
		cliente = null;
	}

	public List<Cliente> getListaCliente() {
		if (listaCliente == null) {
			ClienteDAO dao = new ClienteDAO();
			listaCliente = dao.findAll();
			if (listaCliente == null)
				listaCliente = new ArrayList<Cliente>();
			dao.closeConnection();
		}
		return listaCliente;
	}

	public Sexo[] getListaSexo() {
		return Sexo.values();
	}

	public Perfil[] getListaPerfil() {
		return Perfil.values();
	}

	public Cliente getCliente() {
		if (cliente == null)
			cliente = new Cliente();
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}