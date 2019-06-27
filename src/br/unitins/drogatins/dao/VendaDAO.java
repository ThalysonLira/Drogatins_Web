package br.unitins.drogatins.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.unitins.drogatins.application.Util;
import br.unitins.drogatins.model.ItemVenda;
import br.unitins.drogatins.model.Usuario;
import br.unitins.drogatins.model.Venda;

public class VendaDAO extends DAO<Venda> {

	@Override
	public boolean create(Venda obj) {
		boolean resultado = false;

		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return false;
		}

		PreparedStatement stat = null;
		try {
			stat = getConnection().prepareStatement(
					"INSERT INTO venda ( " + "  data, " + "  cliente, " + "  total  ) " + "VALUES ( " + " ?, " + " ?, " + " ? ) ",
					Statement.RETURN_GENERATED_KEYS);
			stat.setDate(1, Date.valueOf(LocalDate.now()));
			stat.setInt(2, obj.getCliente().getId());
			stat.setDouble(3, obj.getTotal());

			stat.executeUpdate();
			final ResultSet rs = stat.getGeneratedKeys();
			rs.next();
			final int lastId = rs.getInt("id");

			stat.close();

			for (ItemVenda itemVenda : obj.getListaItemVenda()) {
				stat = getConnection().prepareStatement("INSERT INTO itemvenda ( " + "  venda, " + "  valor,"
						+ "  item ) " + "VALUES ( " + " ?, " + " ?, " + " ? ) ");

				stat.setInt(1, lastId);
				stat.setDouble(2, itemVenda.getItem().getProduto().getValor());
				stat.setInt(3, itemVenda.getItem().getId());

				stat.execute();
				stat.close();
			}

			Util.addMessageSucess("Compra  encerrada!");
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
	public boolean update(Venda obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Venda findById(int id) {
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return null;
		}

		Venda venda = null;
		PreparedStatement stat = null;
		ClienteDAO cliente = new ClienteDAO();

		try {
			stat = getConnection().prepareStatement("SELECT * FROM Venda WHERE id = ?");
			stat.setInt(1, id);

			ResultSet rs = stat.executeQuery();
			if (rs.next()) {
				Venda item = new Venda();
				item.setId(rs.getInt("id"));
				item.setData(rs.getDate("data") == null ? null : (rs.getDate("data").toLocalDate()));
				item.setCliente(cliente.findById(rs.getInt("cliente")));
				item.setTotal(rs.getDouble("total"));

				cliente.closeConnection();
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

		return venda;
	}

	public List<ItemVenda> findByVenda(int id) {
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return null;
		}

		List<ItemVenda> listaItemVenda = new ArrayList<ItemVenda>();

		PreparedStatement stat = null;
		EstoqueDAO produto = new EstoqueDAO();
		VendaDAO venda = new VendaDAO();

		try {
			stat = getConnection().prepareStatement("SELECT * FROM ItemVenda WHERE venda = ?");
			stat.setInt(1, id);

			ResultSet rs = stat.executeQuery();
			while (rs.next()) {
				ItemVenda item = new ItemVenda();
				item.setId(rs.getInt("id"));
				item.setItem(produto.findById(rs.getInt("produto")));
				item.setValor(rs.getDouble("valor"));
				item.setVenda(venda.findById(rs.getInt("venda")));

				produto.closeConnection();
				venda.closeConnection();
				listaItemVenda.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Util.addMessageError("Falha ao consultar o Banco de Dados.");
			listaItemVenda = null;
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return listaItemVenda;
	}

	public List<Venda> findByCliente(Usuario cliente) {
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return null;
		}

		List<Venda> listaVenda = new ArrayList<Venda>();

		PreparedStatement stat = null;

		try {
			stat = getConnection().prepareStatement("SELECT * FROM Venda WHERE cliente = ?");
			stat.setInt(1, cliente.getId());

			ResultSet rs = stat.executeQuery();
			while (rs.next()) {
				Venda venda = new Venda();
				venda.setId(rs.getInt("id"));
				venda.setData(rs.getDate("data") == null ? null : (rs.getDate("data").toLocalDate()));
				venda.setTotal(rs.getDouble("total"));

				listaVenda.add(venda);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Util.addMessageError("Falha ao consultar o Banco de Dados.");
			listaVenda = null;
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return listaVenda;
	}

	@Override
	public List<Venda> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}