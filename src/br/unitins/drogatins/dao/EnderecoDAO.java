package br.unitins.drogatins.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.unitins.drogatins.application.Util;
import br.unitins.drogatins.model.Cliente;
import br.unitins.drogatins.model.Endereco;
import br.unitins.drogatins.model.Fornecedor;
import br.unitins.drogatins.model.Funcionario;
import br.unitins.drogatins.model.Uf;

public class EnderecoDAO extends DAO<Endereco> {

	public boolean createCliente(Cliente cliente) {
		boolean resultado = false;

		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return false;
		}

		PreparedStatement stat = null;

		try {
			// salvando endereco
			stat = getConnection().prepareStatement("INSERT INTO endereco ( " + " rua, " + " numero, " + " cep, "
					+ " bairro," + " cidade," + " uf," + " complemento ) " + "VALUES ( " + " ?, " + " ?, " + " ?, "
					+ " ?," + " ?, " + " ?, " + " ? ) ", Statement.RETURN_GENERATED_KEYS);

			stat.setString(1, cliente.getEndereco().getRua());
			stat.setString(2, cliente.getEndereco().getNumero());
			stat.setObject(3, cliente.getEndereco().getCep());
			stat.setObject(4, cliente.getEndereco().getBairro());
			stat.setString(5, cliente.getEndereco().getCidade());
			stat.setInt(6, cliente.getEndereco().getUf().getValue());
			stat.setString(7, cliente.getEndereco().getComplemento());

			stat.executeUpdate();
			final ResultSet rs = stat.getGeneratedKeys();
			rs.next();
			final int lastId = rs.getInt("id");
			stat.close();

			// salvando cliente
			stat = getConnection().prepareStatement("INSERT INTO cliente ( " + " nome, " + " cpf, " + " endereco, "
					+ " datanascimento, " + " login," + " senha," + " perfil," + " sexo ) " + "VALUES ( " + " ?, "
					+ " ?, " + " ?, " + " ?," + " ?, " + " ?, " + " ?, " + " ?, " + " ? ) ");

			stat.setString(1, cliente.getNome());
			stat.setString(2, cliente.getCpf());
			stat.setInt(3, lastId);
			stat.setDate(4, (cliente.getDataNascimento() == null ?
					null : java.sql.Date.valueOf(cliente.getDataNascimento())));
			stat.setInt(5, lastId);
			stat.setString(6, cliente.getLogin());
			stat.setString(7, cliente.getSenha());
			stat.setInt(8, cliente.getPerfil().getValue());
			stat.setInt(9, cliente.getSexo().getValue());

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

	public boolean createFuncionario(Funcionario funcionario) {
		boolean resultado = false;

		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return false;
		}

		PreparedStatement stat = null;

		try {
			// salvando endereco
			stat = getConnection().prepareStatement("INSERT INTO endereco ( " + " rua, " + " numero, " + " cep, "
					+ " bairro," + " cidade," + " uf," + " complemento ) " + "VALUES ( " + " ?, " + " ?, " + " ?, "
					+ " ?," + " ?, " + " ?, " + " ? ) ", Statement.RETURN_GENERATED_KEYS);

			stat.setString(1, funcionario.getEndereco().getRua());
			stat.setString(2, funcionario.getEndereco().getNumero());
			stat.setObject(3, funcionario.getEndereco().getCep());
			stat.setObject(4, funcionario.getEndereco().getBairro());
			stat.setString(5, funcionario.getEndereco().getCidade());
			stat.setInt(6, funcionario.getEndereco().getUf().getValue());
			stat.setString(7, funcionario.getEndereco().getComplemento());

			stat.executeUpdate();
			final ResultSet rs = stat.getGeneratedKeys();
			rs.next();
			final int lastId = rs.getInt("id");
			stat.close();

			// salvando funcionario
			stat = getConnection().prepareStatement("INSERT INTO funcionario ( " + " nome, " + " cpf, " + " endereco, "
					+ " datanascimento, " + " login," + " senha," + " perfil," + " sexo," + " situacao ) " + "VALUES ( "
					+ " ?, " + " ?, " + " ?, " + " ?," + " ?, " + " ?, " + " ?, " + " ?, " + " ? ) ");
			stat.setString(1, funcionario.getNome());
			stat.setString(2, funcionario.getCpf());
			stat.setInt(3, lastId);
			stat.setDate(4, (funcionario.getDataNascimento() == null ?
					null: java.sql.Date.valueOf(funcionario.getDataNascimento())));
			stat.setString(5, funcionario.getLogin());
			stat.setString(6, funcionario.getSenha());
			stat.setInt(7, funcionario.getPerfil().getValue());
			stat.setInt(8, funcionario.getSexo().getValue());
			stat.setInt(9, funcionario.getSituacao().getValue());

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

	public boolean createFornecedor(Fornecedor fornecedor) {
		boolean resultado = false;

		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return false;
		}

		PreparedStatement stat = null;

		try {
			// salvando endereco
			stat = getConnection().prepareStatement("INSERT INTO endereco ( " + " rua, " + " numero, " + " cep, "
					+ " bairro," + " cidade," + " uf," + " complemento ) " + "VALUES ( " + " ?, " + " ?, " + " ?, "
					+ " ?," + " ?, " + " ?, " + " ? ) ", Statement.RETURN_GENERATED_KEYS);

			stat.setString(1, fornecedor.getEndereco().getRua());
			stat.setString(2, fornecedor.getEndereco().getNumero());
			stat.setObject(3, fornecedor.getEndereco().getCep());
			stat.setObject(4, fornecedor.getEndereco().getBairro());
			stat.setString(5, fornecedor.getEndereco().getCidade());
			stat.setInt(6, fornecedor.getEndereco().getUf().getValue());
			stat.setString(7, fornecedor.getEndereco().getComplemento());

			stat.executeUpdate();
			final ResultSet rs = stat.getGeneratedKeys();
			rs.next();
			final int lastId = rs.getInt("id");
			stat.close();

			// salvando fornecedor
			stat = getConnection().prepareStatement("INSERT INTO fornecedor ( " + " nome, " + " cnpj, " + " endereco, "
					+ " login," + " senha," + " perfil," + " situacao ) " + "VALUES ( " + " ?, " + " ?, " + " ?, "
					+ " ?," + " ?, " + " ?, " + " ? ) ");

			stat.setString(1, fornecedor.getNome());
			stat.setString(2, fornecedor.getCnpj());
			stat.setInt(3, lastId);
			stat.setString(4, fornecedor.getLogin());
			stat.setString(5, fornecedor.getSenha());
			stat.setInt(6, fornecedor.getPerfil().getValue());
			stat.setInt(7, fornecedor.getSituacao().getValue());

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
	public boolean create(Endereco obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Endereco obj) {
		boolean resultado = false;

		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return false;
		}

		PreparedStatement stat = null;
		try {
			stat = getConnection().prepareStatement(
					"UPDATE endereco SET " + "  rua = ?, " + "  numero = ? " + "  cep = ? " + "  bairro = ?, "
							+ "  cidade = ?, " + "  uf = ?, " + "  complemento = ?, " + "WHERE id = ? ");

			stat.setString(1, obj.getRua());
			stat.setString(2, obj.getNumero());
			stat.setObject(3, obj.getCep());
			stat.setObject(4, obj.getBairro());
			stat.setString(5, obj.getCidade());
			stat.setInt(6, obj.getUf().getValue());
			stat.setString(7, obj.getComplemento());
			stat.setInt(8, obj.getId());

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
			stat = getConnection().prepareStatement("DELETE FROM endereco WHERE id = ? ");
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
	public Endereco findById(int id) {
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return null;
		}
		Endereco endereco = null;

		PreparedStatement stat = null;

		try {
			stat = getConnection().prepareStatement("SELECT * FROM Endereco WHERE id = ?");
			stat.setInt(1, id);

			ResultSet rs = stat.executeQuery();
			if (rs.next()) {
				endereco = new Endereco();
				endereco.setId(rs.getInt("id"));
				endereco.setRua(rs.getString("rua"));
				endereco.setNumero(rs.getString("numero"));
				endereco.setCep(rs.getString("cep"));
				endereco.setBairro(rs.getString("bairro"));
				endereco.setCidade(rs.getString("cidade"));
				endereco.setUf(Uf.valueOf(rs.getInt("uf")));
				endereco.setComplemento(rs.getString("complemento"));
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

		return endereco;
	}

	@Override
	public List<Endereco> findAll() {
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return null;
		}

		List<Endereco> listaEndereco = new ArrayList<Endereco>();

		PreparedStatement stat = null;

		try {
			stat = getConnection().prepareStatement("SELECT * FROM Endereco");
			ResultSet rs = stat.executeQuery();
			while (rs.next()) {
				Endereco endereco = new Endereco();
				endereco.setId(rs.getInt("id"));
				endereco.setRua(rs.getString("rua"));
				endereco.setNumero(rs.getString("numero"));
				endereco.setCep(rs.getString("cep"));
				endereco.setBairro(rs.getString("bairro"));
				endereco.setCidade(rs.getString("cidade"));
				endereco.setUf(Uf.valueOf(rs.getInt("uf")));
				endereco.setComplemento(rs.getString("complemento"));

				listaEndereco.add(endereco);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Util.addMessageError("Falha ao consultar o Banco de Dados.");
			listaEndereco = null;
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return listaEndereco;
	}
}