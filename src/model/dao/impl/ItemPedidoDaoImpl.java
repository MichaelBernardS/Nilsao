package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
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
	public List<ItemPedido> acharItemPedido(Pedido pedido) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT itempedido.*,item.Descricao as ItDescricao, item.Preco as itPreco "
					+ "FROM itempedido INNER JOIN pedido INNER JOIN item "
					+ "ON itempedido.IdItem = item.Id "
					+ "WHERE itempedido.IdPedido = ? "
					+ "ORDER BY Id ");
			st.setInt(1, pedido.getId());
			rs = st.executeQuery();
			
			List<ItemPedido> list = new ArrayList<>();
			
			while (rs.next()) {
				Item it = instanciarItem(rs);
				ItemPedido itPe = instanciarItemPedido(rs, it);
				list.add(itPe);
			}
			return list;
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
	public void somaDoPedidoPeloId(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT round(SUM(Qtde * PrecoVenda), 2) "
					+ "FROM itempedido "
					+ "WHERE idPedido = ? ");
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
	public List<Double> somaDosPedidosPorData(Date dataInicio, Date dataFinal) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT round(SUM(Qtde * PrecoVenda), 2) "
					+ "FROM itempedido "
					+ "INNER JOIN pedido ON pedido.Id = itempedido.IdPedido "
					+ "WHERE pedido.Data between ? AND ? "
					+ "Group By Data Order By Data ");
			st.setDate(1, new java.sql.Date(dataInicio.getTime()));
			st.setDate(2, new java.sql.Date(dataFinal.getTime()));
			rs = st.executeQuery();
			
			List<Double> list = new ArrayList<>();

			while (rs.next()) {
				list.add(rs.getDouble("round(SUM(Qtde * PrecoVenda), 2)"));
			}
			return list;
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