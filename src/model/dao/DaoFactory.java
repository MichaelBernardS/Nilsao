package model.dao;

import db.DB;
import model.dao.impl.ClienteDaoImpl;
import model.dao.impl.ItemDaoImpl;

public class DaoFactory {
	
	public static ClienteDao createClienteDao() {
		return new ClienteDaoImpl(DB.getConnection());
	}
	
	public static ItemDao createItemDao() {
		return new ItemDaoImpl(DB.getConnection());
	}
}