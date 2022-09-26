package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.dao.ClienteDao;
import model.dao.DaoFactory;
import model.dao.ItemDao;
import model.entities.Cliente;
import model.entities.Item;
import model.entities.ItemPedido;
import model.entities.Pedido;

public class Program {

	public static void main(String[] args) throws ParseException {
		
		ClienteDao clienteDao = DaoFactory.createClienteDao();
		ItemDao itemDao = DaoFactory.createItemDao();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		Date data1 = sdf.parse("10/08/2022 14:20:20");
		Date data2 = sdf.parse("04/08/2022 11:16:33");
		Date data3 = sdf.parse("13/09/2022 09:32:11");
		Date data4 = sdf.parse("14/09/2022 15:12:59");
		Date data5 = sdf.parse("16/09/2022 22:11:22");
		
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
		System.out.println("Itens do Pedido: ");
		ItemPedido itemPedido1 = new ItemPedido(1, 2, 18.14, pedido1);
		itemPedido1.addItem(i2);
		itemPedido1.addItem(i3);
		System.out.println(itemPedido1);
		
		// Adição de 3 clientes no BD: Nilson, José e Marcelo;
		System.out.println();
		System.out.println("Inserção de clientes: ");
		Cliente novoCliente = new Cliente(null, "Marcelo");
		clienteDao.adicionarCliente(novoCliente);
		System.out.println("Adicionado! Novo id: " + novoCliente.getId());
		
		// Adição de 3 itens no BD: Bolacha, Macarrão e Leite;
		System.out.println();
		System.out.println("Inserção de itens: ");
		Item novoItem = new Item(null, "Leite", 4.99);
		itemDao.adicionarItem(novoItem);
		System.out.println("Adicionado! Novo id: " + novoItem.getId());
	}
}