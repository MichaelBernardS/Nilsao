package model.dao;

import java.util.List;

import model.entities.ItemPedido;

public interface ItemPedidoDao {
	
	void adicionar(ItemPedido obj);
	void atualizar(ItemPedido obj);
	void deletarPeloId(Integer id);
	ItemPedido acharPeloId(Integer id);
	ItemPedido total(Double total);
	List<ItemPedido> acharTodos();
}