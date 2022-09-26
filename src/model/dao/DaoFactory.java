package model.dao;

import db.DB;
import model.dao.impl.ClienteDaoImpl;
import model.dao.impl.ItemDaoImpl;
import model.dao.impl.ItemPedidoDaoImpl;
import model.dao.impl.PedidoDaoImpl;

public class DaoFactory {
	
	public static ClienteDao createClienteDao() {
		return new ClienteDaoImpl(DB.getConnection());
	}
	
	public static ItemDao createItemDao() {
		return new ItemDaoImpl(DB.getConnection());
	}
		
	public static PedidoDao createPedidoDao() {
		return new PedidoDaoImpl(DB.getConnection());
	}
	
	public static ItemPedidoDao createItemPedidoDao() {
		return new ItemPedidoDaoImpl(DB.getConnection());
	}
}