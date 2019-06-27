package br.unitins.drogatins.listController;

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

@Named
@ViewScoped
public class ConsultaClienteController implements Serializable {

	private static final long serialVersionUID = 485053809157217597L;

	private String busca;
	private Cliente cliente;

	private List<Cliente> listaCliente = null;

	public void novo() {
		Util.redirect("cadastrocliente.xhtml");
	}

	public void editar(Cliente cliente) {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("clienteFlash", cliente);

		Util.redirect("cadastrocliente.xhtml");
	}

	public void excluir(Cliente cliente) {
		ClienteDAO dao = new ClienteDAO();
		if (dao.delete(cliente.getId())) {
			limpar();
			// para atualizar o data table
			listaCliente = null;
		}
		dao.closeConnection();
	}

	public void pesquisar() {
		listaCliente = null;
	}

	public void limpar() {
		cliente = null;
	}

	public List<Cliente> getListaCliente() {
		if (listaCliente == null) {
			ClienteDAO dao = new ClienteDAO();
			listaCliente = dao.findByNome(getBusca());

			if (listaCliente == null)
				listaCliente = new ArrayList<Cliente>();
			dao.closeConnection();
		}
		return listaCliente;
	}

	public String getBusca() {
		return busca;
	}

	public void setBusca(String busca) {
		this.busca = busca;
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