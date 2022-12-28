package model.dao;

import model.entities.Item;

public interface ItemDao {
	
	void adicionarItem(Item obj);
	Item acharPeloId(Integer id);
}