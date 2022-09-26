package model.dao;

import java.util.List;

import model.entities.Item;

public interface ItemDao {
	
	void adicionarItem(Item obj);
	void atualizar(Item obj);
	void deletarPeloId(Integer id);
	Item acharPeloId(Integer id);
	List<Item> acharTodos();
}