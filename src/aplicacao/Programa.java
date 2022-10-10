package aplicacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import model.dao.ClienteDao;
import model.dao.DaoFactory;
import model.dao.ItemDao;
import model.dao.ItemPedidoDao;
import model.dao.PedidoDao;
import model.entities.Cliente;
import model.entities.Item;
import model.entities.ItemPedido;
import model.entities.Pedido;

public class Programa {

	public static void main(String[] args) throws ParseException {
		
		ClienteDao clienteDao = DaoFactory.createClienteDao();
		ItemDao itemDao = DaoFactory.createItemDao();
		PedidoDao pedidoDao = DaoFactory.createPedidoDao();
		ItemPedidoDao itemPedidoDao = DaoFactory.createItemPedidoDao();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Date data1 = sdf.parse("10/08/2022");
		Date data2 = sdf.parse("04/08/2022");
		Date data3 = sdf.parse("13/09/2022");
		Date data4 = sdf.parse("14/09/2022");
		Date data5 = sdf.parse("16/09/2022");
		
		System.out.println("Clientes: ");
		Cliente cl1 = new Cliente(1, "Nilson");
		System.out.println(cl1);
		Cliente cl2 = new Cliente(2, "José");
		System.out.println(cl2);
		Cliente cl3 = new Cliente(3, "Marcelo");
		System.out.println(cl3);
		
		System.out.println();
		System.out.println("Itens: ");
		Item i1 = new Item(1, "Bolacha", 7.50);
		System.out.println(i1);
		Item i2 = new Item(2, "Macarrão", 13.15);
		System.out.println(i2);
		Item i3 = new Item(3, "Leite", 4.99);
		System.out.println(i3);
		
		System.out.println();
		System.out.println("Pedidos: ");
		Pedido pedido1 = new Pedido(1, data1, cl1);
		System.out.println(pedido1);
		Pedido pedido2 = new Pedido(2, data2, cl2);
		System.out.println(pedido2);
		Pedido pedido3 = new Pedido(3, data4, cl1);
		System.out.println(pedido3);
		Pedido pedido4 = new Pedido(4, data5, cl1);
		System.out.println(pedido4);
		Pedido pedido5 = new Pedido(5, data3, cl2);
		System.out.println(pedido5);
		Pedido pedido6 = new Pedido(6, data5, cl2);
		System.out.println(pedido6);
		
		System.out.println();
		System.out.println("Itens dos Pedidos: ");
		ItemPedido itemPedido1 = new ItemPedido(1, 6, 39.98, pedido1, i1);
		itemPedido1.addItem(i1); itemPedido1.addItem(i1); itemPedido1.addItem(i1); itemPedido1.addItem(i1);
		itemPedido1.addItem(i3);  itemPedido1.addItem(i3);
		System.out.println(itemPedido1);
		
		ItemPedido itemPedido2 = new ItemPedido(2, 4, 44.44, pedido2, i3);
		itemPedido2.addItem(i3);
		itemPedido2.addItem(i2); itemPedido2.addItem(i2); itemPedido2.addItem(i2);
		System.out.println(itemPedido2);
		
		ItemPedido itemPedido3 = new ItemPedido(3, 4, 36.28, pedido3, i2);
		itemPedido3.addItem(i2); itemPedido3.addItem(i2);
		itemPedido3.addItem(i3); itemPedido3.addItem(i3);
		System.out.println(itemPedido3);
		
		ItemPedido itemPedido4 = new ItemPedido(4, 6, 39.98, pedido4, i1);
		itemPedido4.addItem(i1); itemPedido4.addItem(i1); itemPedido4.addItem(i1); itemPedido4.addItem(i1);
		itemPedido4.addItem(i3); itemPedido4.addItem(i3);
		System.out.println(itemPedido4);
		
		ItemPedido itemPedido5 = new ItemPedido(5, 4, 44.44, pedido5, i3);
		itemPedido5.addItem(i3);
		itemPedido5.addItem(i2); itemPedido5.addItem(i2); itemPedido5.addItem(i2);
		System.out.println(itemPedido5);
		
		ItemPedido itemPedido6 = new ItemPedido(6, 2, 12.49, pedido6, i1);
		itemPedido6.addItem(i1);
		itemPedido6.addItem(i3);
		System.out.println(itemPedido6);
		
		// Adição de 3 clientes no BD: Nilson, José e Marcelo;
		System.out.println();
		System.out.println("Inserção de clientes: ");
		//Cliente novoCliente = new Cliente(null, "Marcelo");
		//clienteDao.adicionarCliente(novoCliente);
		//System.out.println("Adicionado! Novo id: " + novoCliente.getId());
		
		// Localizar id de clientes do BD;
		System.out.println();
		System.out.println("Achar cliente pelo id: ");
		Cliente acharCliente = clienteDao.acharPeloId(3);
		System.out.println(acharCliente);
		
		// Adição de 3 itens no BD: Bolacha, Macarrão e Leite;
		System.out.println();
		System.out.println("Inserção de itens: ");
		//Item novoItem = new Item(null, "Leite", 4.99);
		//itemDao.adicionarItem(novoItem);
		//System.out.println("Adicionado! Novo id: " + novoItem.getId());
		
		// Localizar id de itens do BD;
		System.out.println();
		System.out.println("Achar item pelo id: ");
		Item acharItem = itemDao.acharPeloId(1);
		System.out.println(acharItem);
		
		// Adição de 6 pedidos no BD;
		System.out.println();
		System.out.println("Inserção de pedidos: ");
		//Pedido novoPedido = new Pedido(null, data5, cl2);
		//pedidoDao.adicionarPedido(novoPedido);
		//System.out.println("Adicionado! Novo id: " + novoPedido.getId());
		
		// Localizar id de pedidos do BD;
		System.out.println();
		System.out.println("Achar pedido pelo id: ");
		Pedido acharPedido = pedidoDao.acharPeloId(4);
		System.out.println(acharPedido);
		
		// Adição de itens de pedidos;
		System.out.println();
		System.out.println("Inserção de itens de pedidos: ");
		//ItemPedido novoItemPedido = new ItemPedido(null, 1, 4.99, pedido6, i3);
		//itemPedidoDao.adicionar(novoItemPedido);
		//System.out.println("Adicionado! Novo id: " + novoItemPedido.getId());
		
		// Localizar pedidos por data no BD;
		System.out.println();
		System.out.println("Achar pedidos dentro da data de 01/09/2022 a 30/09/2022 no BD: ");
		List<Pedido> list = pedidoDao.acharPelaData();
		list.forEach(System.out::println);
		
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