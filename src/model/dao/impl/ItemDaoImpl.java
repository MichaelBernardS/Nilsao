package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;
import model.dao.ItemDao;
import model.entities.Item;

public class ItemDaoImpl implements ItemDao {

	private Connection conn;

	public ItemDaoImpl(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void adicionarItem(Item it) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO item " 
					+ "(Descricao, Preco) " 
					+ "VALUES " 
					+ "(?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, it.getDescricao());
			st.setDouble(2, it.getPreco());

			int linhasAfetadas = st.executeUpdate();

			if (linhasAfetadas > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					it.setId(id);
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
	public Item acharPeloId(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT * FROM item WHERE Id = ? ");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Item it = instanciarItem(rs);
				return it;
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
		it.setDescricao(rs.getString("Descricao"));
		it.setPreco(rs.getDouble("Preco"));
		return it;
	}
}