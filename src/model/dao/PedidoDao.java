package model.dao;

import java.util.List;

import model.entities.Pedido;

public interface PedidoDao {
	
	void adicionarPedido(Pedido obj);
	void atualizar(Pedido obj);
	void deletarPeloId(Integer id);
	Pedido acharPeloId(Integer id);
	List<Pedido> acharPelaData();
	List<Pedido> acharTodos();
	void countByDate();
	void groupByDate();
}