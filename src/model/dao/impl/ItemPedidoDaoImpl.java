package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.ItemPedidoDao;
import model.entities.Cliente;
import model.entities.ItemPedido;
import model.entities.Pedido;

public class ItemPedidoDaoImpl implements ItemPedidoDao {
	
	private Connection conn;

	public ItemPedidoDaoImpl(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void adicionar(ItemPedido itPe) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO itempedido " 
					+ "(Qtde, PrecoVenda, IdPedido, IdItem) " 
					+ "VALUES " 
					+ "(?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, itPe.getQtde());
			st.setDouble(2, itPe.getPrecoVenda());
			st.setInt(3, itPe.getPedido().getId());
			st.setInt(4, itPe.getItem().getId());

			int linhasAfetadas = st.executeUpdate();

			if (linhasAfetadas > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					itPe.setId(id);
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
	public void atualizar(ItemPedido obj) {
	}

	@Override
	public void deletarPeloId(Integer id) {
	}

	@Override
	public ItemPedido acharPeloId(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT * FROM itempedido WHERE Id = ? ");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Cliente cl = instanciarCliente(rs);
				Pedido pe = instanciarPedido(rs, cl);
				ItemPedido itPe = instanciarItemPedido(rs, pe);
				return itPe;
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
	public ItemPedido total(Double total) {
		return null;
	}
	
	private ItemPedido instanciarItemPedido(ResultSet rs, Pedido ped) throws SQLException {
		ItemPedido itPe = new ItemPedido();
		itPe.setId(rs.getInt("Id"));
		itPe.setQtde(rs.getInt("Qtde"));
		itPe.setPrecoVenda(rs.getDouble("PrecoVenda"));
		itPe.setPedido(ped);
		return itPe;
	}
	
	private Pedido instanciarPedido(ResultSet rs, Cliente cl) throws SQLException {
		Pedido ped = new Pedido();
		ped.setId(rs.getInt("Id"));
		ped.setCliente(cl);
		ped.setData(rs.getDate("Data"));
		return ped;
	}
	
	private Cliente instanciarCliente(ResultSet rs) throws SQLException {
		Cliente cl = new Cliente();
		cl.setId(rs.getInt("Id"));
		cl.setNome(rs.getString("Nome"));
		return cl;
	}

	@Override
	public List<ItemPedido> acharTodos() {
		return null;
	}

	@Override
	public void somaDoPedidoPeloId(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"select SUM(PrecoVenda),round(SUM(PrecoVenda), 2) from itempedido where idPedido = ? ");
			st.setInt(1, id);
			rs = st.executeQuery();
			
			while (rs.next()) {
				System.out.println(rs.getDouble("round(SUM(PrecoVenda), 2)"));
			}
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
	public void somarDoisPedidos() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT sum(PrecoVenda),round(SUM(PrecoVenda), 2) "
					+ "from itempedido "
					+ "where IdPedido = '4' "
					+ "or IdPedido = '6' ");
			rs = st.executeQuery();
			
			while (rs.next()) {
				System.out.println(rs.getDouble("round(SUM(PrecoVenda), 2)"));
			}
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
	public void somarQuatroPedidos() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT sum(PrecoVenda),round(SUM(PrecoVenda), 2) "
					+ "from itempedido "
					+ "where IdPedido = '3' "
					+ "or IdPedido = '4' "
					+ "or IdPedido = '5'"
					+ "or IdPedido = '6' ");
			rs = st.executeQuery();
			
			while (rs.next()) {
				System.out.println(rs.getDouble("round(SUM(PrecoVenda), 2)"));
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
}