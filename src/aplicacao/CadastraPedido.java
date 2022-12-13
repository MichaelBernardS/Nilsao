package aplicacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import model.dao.ClienteDao;
import model.dao.DaoFactory;
import model.dao.ItemDao;
import model.dao.ItemPedidoDao;
import model.dao.PedidoDao;
import model.entities.ItemPedido;
import model.entities.Pedido;

public class CadastraPedido {

	public static void main(String[] args) throws ParseException {
		
		ItemPedidoDao itemPedidoDao = DaoFactory.createItemPedidoDao();
		PedidoDao pedidoDao = DaoFactory.createPedidoDao();
		ClienteDao clienteDao = DaoFactory.createClienteDao();
		ItemDao itemDao = DaoFactory.createItemDao();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		// Adição de 6 pedidos no BD;
		System.out.println("Inserção de pedidos:");
		
		Pedido pedido1 = new Pedido(null, sdf.parse("10/08/2022"), clienteDao.acharPeloId(1));
		pedidoDao.adicionarPedido(pedido1);
		System.out.println("Adicionado! Novo id: " + pedido1.getId());
		
		Pedido pedido2 = new Pedido(null, sdf.parse("04/08/2022"), clienteDao.acharPeloId(2));
		pedidoDao.adicionarPedido(pedido2);
		System.out.println("Adicionado! Novo id: " + pedido2.getId());
		
		Pedido pedido3 = new Pedido(null, sdf.parse("14/09/2022"), clienteDao.acharPeloId(1));
		pedidoDao.adicionarPedido(pedido3);
		System.out.println("Adicionado! Novo id: " + pedido3.getId());
		
		Pedido pedido4 = new Pedido(null, sdf.parse("16/09/2022"), clienteDao.acharPeloId(1));
		pedidoDao.adicionarPedido(pedido4);
		System.out.println("Adicionado! Novo id: " + pedido4.getId());
		
		Pedido pedido5 = new Pedido(null, sdf.parse("13/09/2022"), clienteDao.acharPeloId(2));
		pedidoDao.adicionarPedido(pedido5);
		System.out.println("Adicionado! Novo id: " + pedido5.getId());
		
		Pedido pedido6 = new Pedido(null, sdf.parse("16/09/2022"), clienteDao.acharPeloId(2));
		pedidoDao.adicionarPedido(pedido6);
		System.out.println("Adicionado! Novo id: " + pedido6.getId());
		
		// Adição de itens de pedidos
		System.out.println();
		System.out.println("Inserção de itens de pedidos:");
		
		ItemPedido itemPedido1 = new ItemPedido(null, 4, itemDao.acharPeloId(1).getPreco(), itemDao.acharPeloId(1));
		itemPedidoDao.adicionar(itemPedido1, pedido1);
		System.out.println("Adicionado! Novo id: " + itemPedido1.getId());
		ItemPedido itemPedido2 = new ItemPedido(null, 2, itemDao.acharPeloId(3).getPreco(), itemDao.acharPeloId(3));
		itemPedidoDao.adicionar(itemPedido2, pedido1);
		System.out.println("Adicionado! Novo id: " + itemPedido2.getId());
		
		ItemPedido itemPedido3 = new ItemPedido(null, 1, itemDao.acharPeloId(3).getPreco(), itemDao.acharPeloId(3));
		itemPedidoDao.adicionar(itemPedido3, pedido2);
		System.out.println("Adicionado! Novo id: " + itemPedido3.getId());
		ItemPedido itemPedido4 = new ItemPedido(null, 3, itemDao.acharPeloId(2).getPreco(), itemDao.acharPeloId(2));
		itemPedidoDao.adicionar(itemPedido4, pedido2);
		System.out.println("Adicionado! Novo id: " + itemPedido4.getId());
		
		ItemPedido itemPedido5 = new ItemPedido(null, 2, itemDao.acharPeloId(2).getPreco(), itemDao.acharPeloId(2));
		itemPedidoDao.adicionar(itemPedido5, pedido3);
		System.out.println("Adicionado! Novo id: " + itemPedido5.getId());
		ItemPedido itemPedido6 = new ItemPedido(null, 2, itemDao.acharPeloId(3).getPreco(), itemDao.acharPeloId(3));
		itemPedidoDao.adicionar(itemPedido6, pedido3);
		System.out.println("Adicionado! Novo id: " + itemPedido6.getId());
		
		ItemPedido itemPedido7 = new ItemPedido(null, 4, itemDao.acharPeloId(1).getPreco(), itemDao.acharPeloId(1));
		itemPedidoDao.adicionar(itemPedido7, pedido4);
		System.out.println("Adicionado! Novo id: " + itemPedido7.getId());
		ItemPedido itemPedido8 = new ItemPedido(null, 2, itemDao.acharPeloId(3).getPreco(), itemDao.acharPeloId(3));
		itemPedidoDao.adicionar(itemPedido8, pedido4);
		System.out.println("Adicionado! Novo id: " + itemPedido8.getId());
		
		ItemPedido itemPedido9 = new ItemPedido(null, 1, itemDao.acharPeloId(3).getPreco(), itemDao.acharPeloId(3));
		itemPedidoDao.adicionar(itemPedido9, pedido5);
		System.out.println("Adicionado! Novo id: " + itemPedido9.getId());
		ItemPedido itemPedido10 = new ItemPedido(null, 3, itemDao.acharPeloId(2).getPreco(), itemDao.acharPeloId(2));
		itemPedidoDao.adicionar(itemPedido10, pedido5);
		System.out.println("Adicionado! Novo id: " + itemPedido10.getId());
		
		ItemPedido itemPedido11 = new ItemPedido(null, 1, itemDao.acharPeloId(1).getPreco(), itemDao.acharPeloId(1));
		itemPedidoDao.adicionar(itemPedido11, pedido6);
		System.out.println("Adicionado! Novo id: " + itemPedido11.getId());
		ItemPedido itemPedido12 = new ItemPedido(null, 1, itemDao.acharPeloId(3).getPreco(), itemDao.acharPeloId(3));
		itemPedidoDao.adicionar(itemPedido12, pedido6);
		System.out.println("Adicionado! Novo id: " + itemPedido12.getId());

		// Localizar pedidos pelo ID no BD;
		System.out.println();
		System.out.println("Achar pedido pelo id: ");
		Pedido acharPedido = pedidoDao.acharPeloId(1);
		System.out.println(acharPedido);
		
		// Localizar itempedido pelo ID no BD;
		System.out.println();
		System.out.println("Achar ItemPedido pelo id: ");
		ItemPedido acharItemPedido = itemPedidoDao.acharPeloId(2);
		System.out.println(acharItemPedido);
	}
}