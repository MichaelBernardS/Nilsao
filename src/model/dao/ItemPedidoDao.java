package model.dao;

import java.util.List;

import model.entities.ItemPedido;
import model.entities.Pedido;

public interface ItemPedidoDao {
	
	void adicionar(ItemPedido obj, Pedido pe);
	void atualizar(ItemPedido obj);
	void deletarPeloId(Integer id);
	ItemPedido acharPeloId(Integer id);
	ItemPedido total(Double total);
	List<ItemPedido> acharTodos();
	void somaDoPedidoPeloId(Integer id);
	void somarDoisPedidos();
	void somarQuatroPedidos();
}