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
import model.entities.Item;
import model.entities.ItemPedido;
import model.entities.Pedido;

public class ItemPedidoDaoImpl implements ItemPedidoDao {
	
	private Connection conn;

	public ItemPedidoDaoImpl(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void adicionar(ItemPedido itPe, Pedido pe) {
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
			st.setInt(3, pe.getId());
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
					"SELECT itempedido.*,item.Descricao as ItDescricao,item.Preco as ItPreco "
					+ "FROM itempedido INNER JOIN item "
					+ "ON itempedido.IdItem = item.Id "
					+ "WHERE itempedido.Id = ? ");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Item it = instanciarItem(rs);
				ItemPedido itPe = instanciarItemPedido(rs, it);
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
	
	private Item instanciarItem(ResultSet rs) throws SQLException {
		Item it = new Item();
		it.setId(rs.getInt("Id"));
		it.setDescricao(rs.getString("ItDescricao"));
		it.setPreco(rs.getDouble("ItPreco"));
		return it;
	}
	
	private ItemPedido instanciarItemPedido(ResultSet rs, Item it) throws SQLException {
		ItemPedido itPe = new ItemPedido();
		itPe.setId(rs.getInt("Id"));
		itPe.setQtde(rs.getInt("Qtde"));
		itPe.setPrecoVenda(rs.getDouble("PrecoVenda"));
		itPe.setItem(it);
		return itPe;
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
					"select SUM(Qtde * PrecoVenda),round(SUM(Qtde * PrecoVenda), 2) from itempedido where idPedido = ? ");
			st.setInt(1, id);
			rs = st.executeQuery();
			
			while (rs.next()) {
				System.out.println(rs.getDouble("round(SUM(Qtde * PrecoVenda), 2)"));
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
					"SELECT sum(Qtde * PrecoVenda),round(SUM(Qtde * PrecoVenda), 2) "
					+ "from itempedido "
					+ "where IdPedido = '4' "
					+ "or IdPedido = '6' ");
			rs = st.executeQuery();
			
			while (rs.next()) {
				System.out.println(rs.getDouble("round(SUM(Qtde * PrecoVenda), 2)"));
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
					"SELECT sum(Qtde * PrecoVenda),round(SUM(Qtde * PrecoVenda), 2) "
					+ "from itempedido "
					+ "where IdPedido = '3' "
					+ "or IdPedido = '4' "
					+ "or IdPedido = '5' "
					+ "or IdPedido = '6' ");
			rs = st.executeQuery();
			
			while (rs.next()) {
				System.out.println(rs.getDouble("round(SUM(Qtde * PrecoVenda), 2)"));
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