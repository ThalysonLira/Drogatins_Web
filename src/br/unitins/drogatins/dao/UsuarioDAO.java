package br.unitins.drogatins.dao;

import java.util.List;

import br.unitins.drogatins.application.Util;
import br.unitins.drogatins.model.Fornecedor;
import br.unitins.drogatins.model.Funcionario;
import br.unitins.drogatins.model.Situacao;
import br.unitins.drogatins.model.Usuario;

public class UsuarioDAO extends DAO<Usuario> {

	@Override
	public boolean create(Usuario obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Usuario obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Usuario findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Usuario findUsuario(String login, String senha) {
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return null;
		}

		Usuario usuario = null;
		ClienteDAO cliente = new ClienteDAO();
		FuncionarioDAO funcionario = new FuncionarioDAO();
		FornecedorDAO fornecedor = new FornecedorDAO();

		usuario = cliente.findCliente(login, senha);

		if (usuario == null) {
			usuario = funcionario.findFuncionario(login, senha);
			
			if(usuario != null) {
			Funcionario f = (Funcionario) usuario;
			if(f.getSituacao() == Situacao.INATIVO)
				return null;
			}
		}
		if (usuario == null) {
			usuario = fornecedor.findFornecedor(login, senha);
			
			if(usuario != null) {
			Fornecedor f = (Fornecedor) usuario;
			if(f.getSituacao() == Situacao.INATIVO)
				return null;
			}
		}

		return usuario;
	}

	@Override
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}