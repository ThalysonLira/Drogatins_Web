package br.unitins.drogatins.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unitins.drogatins.application.Util;
import br.unitins.drogatins.model.ItemEstoque;

public class EstoqueDAO extends DAO<ItemEstoque> {

	@Override
	public boolean create(ItemEstoque obj) {
		boolean resultado = false;

		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return false;
		}

		PreparedStatement stat = null;
		try {
			stat = getConnection().prepareStatement(
					"INSERT INTO estoque ( " + " produto, " + " quantidade, " + "VALUES ( " + " ?, " + " ? ) ");

			stat.setInt(1, obj.getProduto().getId());
			stat.setInt(2, obj.getQuant());

			stat.execute();
			Util.addMessageError("Cadastro realizado com sucesso!");
			resultado = true;
		} catch (SQLException e) {
			Util.addMessageError("Falha ao incluir.");
			e.printStackTrace();
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return resultado;
	}

	@Override
	public boolean update(ItemEstoque obj) {
		boolean resultado = false;

		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return false;
		}

		PreparedStatement stat = null;
		try {
			stat = getConnection().prepareStatement(
					"UPDATE estoque SET " + "  produto = ?, " + "  quantidade = ? " + "WHERE id = ? ");
			stat.setInt(1, obj.getProduto().getId());
			stat.setInt(2, obj.getQuant());
			stat.setInt(3, obj.getId());

			stat.execute();
			Util.addMessageError("Alteração realizada com sucesso!");
			resultado = true;
		} catch (SQLException e) {
			Util.addMessageError("Falha ao Alterar.");
			e.printStackTrace();
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return resultado;
	}

	@Override
	public boolean delete(int id) {
		boolean resultado = false;

		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return false;
		}

		PreparedStatement stat = null;
		try {
			stat = getConnection().prepareStatement("DELETE FROM estoque WHERE id = ? ");
			stat.setInt(1, id);

			stat.execute();
			Util.addMessageError("Exclusão realizada com sucesso!");
			resultado = true;
		} catch (SQLException e) {
			Util.addMessageError("Falha ao Excluir.");
			e.printStackTrace();
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return resultado;
	}

	@Override
	public ItemEstoque findById(int id) {
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return null;
		}
		ItemEstoque item = null;

		PreparedStatement stat = null;
		ProdutoDAO produto = new ProdutoDAO();

		try {
			stat = getConnection().prepareStatement("SELECT * FROM Estoque WHERE id = ?");
			stat.setInt(1, id);

			ResultSet rs = stat.executeQuery();
			if (rs.next()) {
				item = new ItemEstoque();
				item.setId(rs.getInt("id"));
				item.setProduto(produto.findById(rs.getInt("produto")));
				item.setQuant(rs.getInt("quantidade"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Util.addMessageError("Falha ao consultar o Banco de Dados.");
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return item;
	}
	
	public List<ItemEstoque> findByNome(String nome) {
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return null;
		}
		
		List<ItemEstoque> estoque = new ArrayList<ItemEstoque>();
		PreparedStatement stat = null;
		ProdutoDAO produto = new ProdutoDAO();

		try {
			stat = getConnection().prepareStatement("SELECT * FROM Estoque WHERE produto ILIKE ?");
			stat.setString(1, (nome == null ? "%" : "%" + nome + "%"));
			ResultSet rs = stat.executeQuery();

			while (rs.next()) {
				ItemEstoque item = new ItemEstoque();
				item.setId(rs.getInt("id"));
				item.setProduto(produto.findById(rs.getInt("produto")));
				item.setQuant(rs.getInt("quantidade"));

				produto.closeConnection();
				estoque.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Util.addMessageError("Falha ao consultar o Banco de Dados.");
			estoque = null;
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return estoque;
	}

	public ItemEstoque findProduto(int produto) {
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return null;
		}
		ItemEstoque item = null;
		PreparedStatement stat = null;
		ProdutoDAO dao = new ProdutoDAO();

		try {
			stat = getConnection().prepareStatement("SELECT * FROM estoque WHERE produto = ?");
			stat.setInt(1, produto);

			ResultSet rs = stat.executeQuery();
			if (rs.next()) {
				item = new ItemEstoque();
				item.setId(rs.getInt("id"));
				item.setProduto(dao.findById(rs.getInt("produto")));
				item.setQuant(rs.getInt("quantidade"));
				
				dao.closeConnection();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Util.addMessageError("Falha ao consultar o Banco de Dados.");
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return item;
	}

	@Override
	public List<ItemEstoque> findAll() {
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return null;
		}

		List<ItemEstoque> estoque = new ArrayList<ItemEstoque>();

		PreparedStatement stat = null;
		ProdutoDAO produto = new ProdutoDAO();

		try {
			stat = getConnection().prepareStatement("SELECT * FROM Estoque");
			ResultSet rs = stat.executeQuery();
			while (rs.next()) {
				ItemEstoque item = new ItemEstoque();
				item.setId(rs.getInt("id"));
				item.setProduto(produto.findById(rs.getInt("produto")));
				item.setQuant(rs.getInt("quantidade"));

				estoque.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Util.addMessageError("Falha ao consultar o Banco de Dados.");
			estoque = null;
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return estoque;
	}
}