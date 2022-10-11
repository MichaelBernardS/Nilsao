package aplicacao;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.ItemDao;
import model.dao.ItemPedidoDao;
import model.dao.PedidoDao;
import model.entities.ItemPedido;
import model.entities.Pedido;

public class ProgramaItensPedidos {

	public static void main(String[] args) {
		
		ItemPedidoDao itemPedidoDao = DaoFactory.createItemPedidoDao();
		PedidoDao pedidoDao = DaoFactory.createPedidoDao();
		ItemDao itemDao = DaoFactory.createItemDao();
		
		// Adição de itens de pedidos;
		System.out.println();
		System.out.println("Inserção de itens de pedidos:");
		ItemPedido itemPedido1 = new ItemPedido(null, 4, itemDao.acharPeloId(1).getPreco(), pedidoDao.acharPeloId(1), itemDao.acharPeloId(1));
		itemPedidoDao.adicionar(itemPedido1);
		System.out.println("Adicionado! Novo id: " + itemPedido1.getId());
		ItemPedido itemPedido2 = new ItemPedido(null, 2, itemDao.acharPeloId(3).getPreco(), pedidoDao.acharPeloId(1), itemDao.acharPeloId(3));
		itemPedidoDao.adicionar(itemPedido2);
		System.out.println("Adicionado! Novo id: " + itemPedido2.getId());
		ItemPedido itemPedido3 = new ItemPedido(null, 1, itemDao.acharPeloId(3).getPreco(), pedidoDao.acharPeloId(2), itemDao.acharPeloId(3));
		itemPedidoDao.adicionar(itemPedido3);
		System.out.println("Adicionado! Novo id: " + itemPedido3.getId());
		ItemPedido itemPedido4 = new ItemPedido(null, 3, itemDao.acharPeloId(2).getPreco(), pedidoDao.acharPeloId(2), itemDao.acharPeloId(2));
		itemPedidoDao.adicionar(itemPedido4);
		System.out.println("Adicionado! Novo id: " + itemPedido4.getId());
		ItemPedido itemPedido5 = new ItemPedido(null, 2, itemDao.acharPeloId(2).getPreco(), pedidoDao.acharPeloId(3), itemDao.acharPeloId(2));
		itemPedidoDao.adicionar(itemPedido5);
		System.out.println("Adicionado! Novo id: " + itemPedido5.getId());
		ItemPedido itemPedido6 = new ItemPedido(null, 2, itemDao.acharPeloId(3).getPreco(), pedidoDao.acharPeloId(3), itemDao.acharPeloId(3));
		itemPedidoDao.adicionar(itemPedido6);
		System.out.println("Adicionado! Novo id: " + itemPedido6.getId());
		ItemPedido itemPedido7 = new ItemPedido(null, 4, itemDao.acharPeloId(1).getPreco(), pedidoDao.acharPeloId(4), itemDao.acharPeloId(1));
		itemPedidoDao.adicionar(itemPedido7);
		System.out.println("Adicionado! Novo id: " + itemPedido7.getId());
		ItemPedido itemPedido8 = new ItemPedido(null, 2, itemDao.acharPeloId(3).getPreco(), pedidoDao.acharPeloId(4), itemDao.acharPeloId(3));
		itemPedidoDao.adicionar(itemPedido8);
		System.out.println("Adicionado! Novo id: " + itemPedido8.getId());
		ItemPedido itemPedido9 = new ItemPedido(null, 1, itemDao.acharPeloId(3).getPreco(), pedidoDao.acharPeloId(5), itemDao.acharPeloId(3));
		itemPedidoDao.adicionar(itemPedido9);
		System.out.println("Adicionado! Novo id: " + itemPedido9.getId());
		ItemPedido itemPedido10 = new ItemPedido(null, 3, itemDao.acharPeloId(2).getPreco(), pedidoDao.acharPeloId(5), itemDao.acharPeloId(2));
		itemPedidoDao.adicionar(itemPedido10);
		System.out.println("Adicionado! Novo id: " + itemPedido10.getId());
		ItemPedido itemPedido11 = new ItemPedido(null, 1, itemDao.acharPeloId(1).getPreco(), pedidoDao.acharPeloId(6), itemDao.acharPeloId(1));
		itemPedidoDao.adicionar(itemPedido11);
		System.out.println("Adicionado! Novo id: " + itemPedido11.getId());
		ItemPedido itemPedido12 = new ItemPedido(null, 1, itemDao.acharPeloId(3).getPreco(), pedidoDao.acharPeloId(6), itemDao.acharPeloId(3));
		itemPedidoDao.adicionar(itemPedido12);
		System.out.println("Adicionado! Novo id: " + itemPedido12.getId());
		
		// Solicitações do Nilson:
		// Utilizando o group by, mostrou as datas sem repetições;
		System.out.println();
		System.out.println("   Data");
		System.out.println("----------");
		pedidoDao.groupByDate(); 
		
		// Utilizando o count, mostrou quantos pedidos terão em uma determinada data:
		System.out.println();
		System.out.println("Pedidos");
		System.out.println("-------");
		pedidoDao.countByDate(); 
		
		// Localizar pedidos por data no BD;
		System.out.println();
		System.out.println("Achar pedidos dentro da data de 01/09/2022 a 30/09/2022 no BD: ");
		List<Pedido> list = pedidoDao.acharPelaData();
		list.forEach(System.out::println);
		
		// Utilizando o sum e round, mostrou a soma do total do pedido;
		System.out.println();
		System.out.println("Total");
		System.out.println("-----");
		itemPedidoDao.somaDoPedidoPeloId(5);
		itemPedidoDao.somaDoPedidoPeloId(3);
		itemPedidoDao.somarDoisPedidos();
		
		System.out.println();
		System.out.println("Total geral");
		System.out.println("------");
		itemPedidoDao.somarQuatroPedidos();
	}
}