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
import model.dao.PedidoDao;
import model.entities.Cliente;
import model.entities.Item;
import model.entities.ItemPedido;
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
					"SELECT pedido.*,cliente.Nome as ClNome,item.Descricao as ItDescricao,item.Preco as itPreco,itempedido.Qtde as ItPeQtde,itempedido.PrecoVenda as ItPePrecoVenda "
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
					"SELECT pedido.Data,cliente.Id as Id,cliente.Nome as ClNome,item.Descricao as ItDescricao, item.Preco as itPreco,itempedido.Qtde as ItPeQtde,itempedido.PrecoVenda as ItPePrecoVenda "
							+ "FROM pedido INNER JOIN cliente INNER JOIN item INNER JOIN itempedido "
							+ "ON pedido.IdCliente = cliente.Id "
							+ "WHERE pedido.Data between ? and ? ORDER BY Data ");
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
		ped.setId(rs.getInt("Id"));
		ped.setData(rs.getDate("Data"));
		ped.setCliente(cl);
		ped.addItem(itPe);
		return ped;
	}
	
	private Cliente instanciarCliente(ResultSet rs) throws SQLException {
		Cliente cl = new Cliente();
		cl.setId(rs.getInt("Id"));
		cl.setNome(rs.getString("ClNome"));
		return cl;
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
		itPe.setQtde(rs.getInt("ItPeQtde"));
		itPe.setPrecoVenda(rs.getDouble("ItPePrecoVenda"));
		itPe.setItem(it);
		return itPe;
	}

	@Override
	public List<Pedido> acharTodos() {
		return null;
	}

	@Override
	public void countByDate() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT count(Data) from pedido where Data between '2022-09-01' and '2022-09-30' group by Data Order by Data ");
			
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
	public void groupByDate() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT Data from pedido where Data between '2022-09-01' and '2022-09-30' group by Data Order by Data ");
			
			rs = st.executeQuery();
			
			while (rs.next()) {
				System.out.println(rs.getDate("Data"));
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