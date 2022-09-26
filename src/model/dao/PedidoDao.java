package model.dao;

import java.util.Date;
import java.util.List;

import model.entities.Pedido;

public interface PedidoDao {
	
	void adicionar(Pedido obj);
	void atualizar(Pedido obj);
	void deletarPeloId(Integer id);
	Pedido acharPeloId(Integer id);
	Pedido acharPelaData(Date data);
	List<Pedido> acharTodos();
}