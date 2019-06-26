package br.unitins.drogatins.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unitins.drogatins.application.Util;
import br.unitins.drogatins.model.Funcionario;
import br.unitins.drogatins.model.Perfil;
import br.unitins.drogatins.model.Sexo;
import br.unitins.drogatins.model.Situacao;

public class FuncionarioDAO extends DAO<Funcionario> {

	@Override
	public boolean create(Funcionario obj) {
		boolean resultado = false;

		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return false;
		}

		PreparedStatement stat = null;
		try {
			stat = getConnection().prepareStatement(
					"INSERT INTO funcionario ( " + " nome, " + " cpf, " + " datanascimento, " + " login,"
							+ " senha," + " perfil," + " sexo," + " situacao ) " + "VALUES ( " + " ?, " + " ?, "
							+ " ?, " + " ?," + " ?, " + " ?, " + " ?, " + " ?, " + " ? ) ");
			stat.setString(1, obj.getNome());
			stat.setString(2, obj.getCpf());
			stat.setDate(3, (obj.getDataNascimento() == null ?
					null : java.sql.Date.valueOf(obj.getDataNascimento())));
			stat.setString(4, obj.getLogin());
			stat.setString(5, obj.getSenha());
			stat.setInt(6, obj.getPerfil().getValue());
			stat.setInt(7, obj.getSexo().getValue());
			stat.setInt(8, obj.getSituacao().getValue());

			stat.execute();
			Util.addMessageSucess("Cadastro realizado com sucesso!");
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
	public boolean update(Funcionario obj) {
		boolean resultado = false;

		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return false;
		}

		PreparedStatement stat = null;
		try {
			stat = getConnection().prepareStatement("UPDATE funcionario SET " + "  nome = ?, " + "  cpf = ? "
					+ "  datanascimento = ? " + "  login = ?, " + "  senha = ?, " + "  perfil = ?, "
					+ "  sexo = ?, " + "  situacao = ?, " + "WHERE id = ? ");
			stat.setString(1, obj.getNome());
			stat.setString(2, obj.getCpf());
			stat.setDate(3, (obj.getDataNascimento() == null ?
					null : java.sql.Date.valueOf(obj.getDataNascimento())));
			stat.setString(4, obj.getLogin());
			stat.setString(5, obj.getSenha());
			stat.setInt(6, obj.getPerfil().getValue());
			stat.setInt(7, obj.getSexo().getValue());
			stat.setInt(8, obj.getSituacao().getValue());
			stat.setInt(9, obj.getId());

			stat.execute();
			Util.addMessageSucess("Alteração realizada com sucesso!");
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
			stat = getConnection().prepareStatement("DELETE FROM funcionario WHERE id = ? ");
			stat.setInt(1, id);

			stat.execute();
			Util.addMessageSucess("Exclusão realizada com sucesso!");
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
	public Funcionario findById(int id) {
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return null;
		}
		Funcionario funcionario = null;

		PreparedStatement stat = null;

		try {
			stat = getConnection().prepareStatement("SELECT * FROM Funcionario WHERE id = ?");
			stat.setInt(1, id);

			ResultSet rs = stat.executeQuery();
			if (rs.next()) {
				funcionario = new Funcionario();
				funcionario.setId(rs.getInt("id"));
				funcionario.setNome(rs.getString("nome"));
				funcionario.setCpf(rs.getString("cpf"));
				funcionario.setDataNascimento(
						rs.getDate("datanascimento") == null ?
								null : (rs.getDate("datanascimento").toLocalDate()));
				funcionario.setLogin(rs.getString("login"));
				funcionario.setSenha(rs.getString("senha"));
				funcionario.setSexo(Sexo.valueOf(rs.getInt("sexo")));
				funcionario.setPerfil(Perfil.valueOf(rs.getInt("perfil")));
				funcionario.setSituacao(Situacao.valueOf(rs.getInt("situacao")));
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
		return funcionario;
	}
	
	public Funcionario findFuncionario(String login, String senha) {
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return null;
		}
		Funcionario funcionario = null;
		PreparedStatement stat = null;

		try {
			stat = getConnection().prepareStatement("SELECT * FROM funcionario WHERE login = ? AND senha = ? ");
			stat.setString(1, login);
			stat.setString(2, senha);

			ResultSet rs = stat.executeQuery();
			if (rs.next()) {
				funcionario = new Funcionario();
				funcionario.setId(rs.getInt("id"));
				funcionario.setNome(rs.getString("nome"));
				funcionario.setCpf(rs.getString("cpf"));
				funcionario.setDataNascimento(
						rs.getDate("datanascimento") == null ?
								null : (rs.getDate("datanascimento").toLocalDate()));
				funcionario.setLogin(rs.getString("login"));
				funcionario.setSenha(rs.getString("senha"));
				funcionario.setSexo(Sexo.valueOf(rs.getInt("sexo")));
				funcionario.setPerfil(Perfil.valueOf(rs.getInt("perfil")));
				funcionario.setSituacao(Situacao.valueOf(rs.getInt("situacao")));
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
		return funcionario;
	}

	@Override
	public List<Funcionario> findAll() {
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return null;
		}

		List<Funcionario> listaFuncionario = new ArrayList<Funcionario>();

		PreparedStatement stat = null;

		try {
			stat = getConnection().prepareStatement("SELECT * FROM Funcionario");
			ResultSet rs = stat.executeQuery();
			while (rs.next()) {
				Funcionario funcionario = new Funcionario();
				funcionario.setId(rs.getInt("id"));
				funcionario.setNome(rs.getString("nome"));
				funcionario.setCpf(rs.getString("cpf"));
				funcionario.setDataNascimento(
						rs.getDate("datanascimento") == null ?
								null : (rs.getDate("datanascimento").toLocalDate()));
				funcionario.setLogin(rs.getString("login"));
				funcionario.setSenha(rs.getString("senha"));
				funcionario.setSexo(Sexo.valueOf(rs.getInt("sexo")));
				funcionario.setPerfil(Perfil.valueOf(rs.getInt("perfil")));
				funcionario.setSituacao(Situacao.valueOf(rs.getInt("situacao")));

				listaFuncionario.add(funcionario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Util.addMessageError("Falha ao consultar o Banco de Dados.");
			listaFuncionario = null;
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listaFuncionario;

	}

	public List<Funcionario> findByNome(String nome) {
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return null;
		}

		List<Funcionario> listaFuncionario = new ArrayList<Funcionario>();

		PreparedStatement stat = null;

		try {
			stat = getConnection().prepareStatement("SELECT * FROM Funcionario WHERE nome ILIKE ?");
			stat.setString(1, (nome == null ? "%" : "%" + nome + "%"));
			ResultSet rs = stat.executeQuery();

			while (rs.next()) {
				Funcionario funcionario = new Funcionario();
				funcionario.setId(rs.getInt("id"));
				funcionario.setNome(rs.getString("nome"));
				funcionario.setCpf(rs.getString("cpf"));
				funcionario.setDataNascimento(
						rs.getDate("datanascimento") == null ?
								null : (rs.getDate("datanascimento").toLocalDate()));
				funcionario.setLogin(rs.getString("login"));
				funcionario.setSenha(rs.getString("senha"));
				funcionario.setSexo(Sexo.valueOf(rs.getInt("sexo")));
				funcionario.setPerfil(Perfil.valueOf(rs.getInt("perfil")));
				funcionario.setSituacao(Situacao.valueOf(rs.getInt("situacao")));

				listaFuncionario.add(funcionario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Util.addMessageError("Falha ao consultar o Banco de Dados.");
			listaFuncionario = null;
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listaFuncionario;
	}
}