package aplicacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.ItemPedidoDao;
import model.dao.PedidoDao;
import model.entities.Pedido;

public class Listagem {

	public static void main(String[] args) throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		ItemPedidoDao itemPedidoDao = DaoFactory.createItemPedidoDao();
		PedidoDao pedidoDao = DaoFactory.createPedidoDao();

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
		System.out.println("-------------------------------------------------------------");
		List<Pedido> listAcharPelaData = pedidoDao.acharPelaData(sdf.parse("01/09/2022"), sdf.parse("30/09/2022"));
		listAcharPelaData.forEach(System.out::println);
		
		// Utilizando o sum e round, mostrou a soma do total dos pedidos agrupados por dia;
		System.out.println();
		System.out.println("Total");
		System.out.println("-----");
		itemPedidoDao.somaDosPedidosPorData(sdf.parse("01/09/2022"), sdf.parse("30/09/2022"));
		
		System.out.println();
		System.out.println("Total geral");
		System.out.println("------------");
		itemPedidoDao.somaTotal(sdf.parse("01/09/2022"), sdf.parse("30/09/2022"));
	}
}