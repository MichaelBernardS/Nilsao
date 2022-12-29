package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.PedidoDao;
import model.entities.Cliente;
import model.entities.Item;
import model.entities.ItemPedido;
import model.entities.Pedido;

public class PedidoDaoImpl implements PedidoDao {
	
	private Connection conn;
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

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
	public Pedido acharPeloId(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT pedido.*,cliente.Nome as ClNome,item.Descricao as ItDescricao,item.Preco as ItPreco, itempedido.Qtde as ItPeQtde, itempedido.PrecoVenda as ItPePrecoVenda "
					+ "FROM pedido INNER JOIN cliente INNER JOIN item INNER JOIN itempedido "
					+ "ON pedido.IdCliente = cliente.Id "
					+ "WHERE pedido.Id = ? ");
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if (rs.next()) {
				Cliente cl = instanciarCliente(rs);
				Item it = instanciarItem(rs);
				ItemPedido itPe = instanciarItemPedido(rs, it);
				Pedido ped = instanciarPedido(rs, cl, itPe);
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
	public List<Pedido> acharPelaData(Date dataInicio, Date dataFinal) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT pedido.Data, itempedido.Id as IdItemPedido, cliente.Id as IdCliente, pedido.Id as IdPedido, cliente.Nome as ClNome, item.Descricao as ItDescricao,item.Preco as ItPreco, itempedido.Qtde as ItPeQtde, itempedido.PrecoVenda as ItPePrecoVenda, Item.Id as IdItem "
							+ "FROM itempedido "
							+ "INNER JOIN pedido ON pedido.Id = itempedido.IdPedido "
							+ "INNER JOIN item ON item.Id = itempedido.IdItem "
							+ "INNER JOIN cliente ON cliente.Id = pedido.IdCliente "
							+ "WHERE pedido.Data between ? and ? "
							+ "ORDER BY itempedido.Id, cliente.Id, pedido.Id ");
			st.setDate(1, new java.sql.Date(dataInicio.getTime()));
			st.setDate(2, new java.sql.Date(dataFinal.getTime()));
			
			rs = st.executeQuery();
			
			List<Pedido> list = new ArrayList<>();
			
			while (rs.next()) {
				Cliente cl = instanciarCliente(rs);
				Item it = instanciarItem(rs);
				ItemPedido itPe = instanciarItemPedido(rs, it);
				Pedido ped = instanciarPedido(rs, cl, itPe);
				list.add(ped);
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
	
	private Pedido instanciarPedido(ResultSet rs, Cliente cl, ItemPedido itPe) throws SQLException {
		Pedido ped = new Pedido();
		ped.setId(rs.getInt("IdPedido"));
		ped.setData(rs.getDate("Data"));
		ped.setCliente(cl);
		ped.addItem(itPe);
		return ped;
	}
	
	private Cliente instanciarCliente(ResultSet rs) throws SQLException {
		Cliente cl = new Cliente();
		cl.setId(rs.getInt("IdCliente"));
		cl.setNome(rs.getString("ClNome"));
		return cl;
	}
	
	private Item instanciarItem(ResultSet rs) throws SQLException {
		Item it = new Item();
		it.setId(rs.getInt("IdItem"));
		it.setDescricao(rs.getString("ItDescricao"));
		it.setPreco(rs.getDouble("ItPreco"));
		return it;
	}
	
	private ItemPedido instanciarItemPedido(ResultSet rs, Item it) throws SQLException {
		ItemPedido itPe = new ItemPedido();
		itPe.setId(rs.getInt("IdItemPedido"));
		itPe.setQtde(rs.getInt("ItPeQtde"));
		itPe.setPrecoVenda(rs.getDouble("ItPePrecoVenda"));
		itPe.setItem(it);
		return itPe;
	}

	@Override
	public void countByDate(Date dataInicio, Date dataFinal) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT count(Data) "
					+ "From pedido "
					+ "Where Data "
					+ "Between ? AND ? "
					+ "Group By Data Order by Data ");
			st.setDate(1, new java.sql.Date(dataInicio.getTime()));
			st.setDate(2, new java.sql.Date(dataFinal.getTime()));
			rs = st.executeQuery();
			
			while (rs.next()) {
				System.out.println(rs.getInt(1));
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
	public List<String> groupByDate(Date dataInicio, Date dataFinal) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT Data "
					+ "FROM pedido "
					+ "WHERE Data "
					+ "Between ? And ? "
					+ "Group By Data Order by Data ");
			st.setDate(1, new java.sql.Date(dataInicio.getTime()));
			st.setDate(2, new java.sql.Date(dataFinal.getTime()));
			rs = st.executeQuery();
			
			List<String> list = new ArrayList<>();
			
			while (rs.next()) {
				list.add(sdf.format(rs.getDate("Data")));
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