package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.ClienteDao;
import model.entities.Cliente;

public class ClienteDaoImpl implements ClienteDao {

	private Connection conn;

	public ClienteDaoImpl(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void adicionarCliente(Cliente cl) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO cliente "
					+ "(Nome) "
					+ "VALUES "
					+ "(?)",
					Statement.RETURN_GENERATED_KEYS);
				st.setString(1, cl.getNome());
					
				int linhasAfetadas = st.executeUpdate();
				
				if (linhasAfetadas > 0) {
					ResultSet rs = st.getGeneratedKeys();
					if (rs.next()) {
						int id = rs.getInt(1);
						cl.setId(id);
						DB.closeResultSet(rs);
					}
				}
				else {
					throw new DbException("Erro inesperado! Sem linhas afetadas!");
				}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void atualizar(Cliente obj) {
	}

	@Override
	public void deletarPeloId(Integer id) {
	}

	@Override
	public Cliente acharPeloId(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT * FROM cliente WHERE Id = ? ");
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if (rs.next()) {
				Cliente cl = instanciarCliente(rs);
				return cl;
			}
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private Cliente instanciarCliente(ResultSet rs) throws SQLException {
		Cliente cl = new Cliente();
		cl.setId(rs.getInt("Id"));
		cl.setNome(rs.getString("Nome"));
		return cl;
	}

	@Override
	public List<Cliente> acharTodos() {
		return null;
	}
}