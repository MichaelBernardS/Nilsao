package model.dao;

import java.util.Date;
import java.util.List;

import model.entities.ItemPedido;
import model.entities.Pedido;

public interface ItemPedidoDao {
	
	void adicionar(ItemPedido obj, Pedido pe);
	ItemPedido acharPeloId(Integer id);
	List<ItemPedido> acharItemPedido(Pedido pedido);
	void somaDoPedidoPeloId(Integer id);
	void somaDosPedidosPorData(Date dataInicio, Date dataFinal);
	Double somaTotal(Date dataInicio, Date dataFinal);
}