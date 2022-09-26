package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.PedidoDao;
import model.entities.Cliente;
import model.entities.Pedido;

public class PedidoDaoImpl implements PedidoDao {
	
	private Connection conn;

	public PedidoDaoImpl(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void adicionarPedido(Pedido ped) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO pedido " 
					+ "(Data, IdCliente) " 
					+ "VALUES " 
					+ "(?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setDate(1, new java.sql.Date(ped.getData().getTime()));
			st.setInt(2, ped.getCliente().getId());

			int linhasAfetadas = st.executeUpdate();

			if (linhasAfetadas > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					ped.setId(id);
					DB.closeResultSet(rs);
				}
			} else {
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
	public void atualizar(Pedido obj) {
	}

	@Override
	public void deletarPeloId(Integer id) {
	}

	@Override
	public Pedido acharPeloId(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT pedido.*,cliente.Nome as ClNome "
					+ "FROM pedido INNER JOIN cliente "
					+ "ON pedido.IdCliente = cliente.Id "
					+ "WHERE pedido.Id = ? ");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Cliente cl = instanciarCliente(rs);
				Pedido ped = instanciarPedido(rs, cl);
				return ped;
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

	@Override
	public Pedido acharPelaData(Date data) {
		return null;
	}
	
	private Pedido instanciarPedido(ResultSet rs, Cliente cl) throws SQLException {
		Pedido ped = new Pedido();
		ped.setId(rs.getInt("Id"));
		ped.setData(rs.getDate("Data"));
		ped.setCliente(cl);
		return ped;
	}
	
	private Cliente instanciarCliente(ResultSet rs) throws SQLException {
		Cliente cl = new Cliente();
		cl.setId(rs.getInt("Id"));
		cl.setNome(rs.getString("ClNome"));
		return cl;
	}

	@Override
	public List<Pedido> acharTodos() {
		return null;
	}
}