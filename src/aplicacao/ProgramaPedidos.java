package aplicacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import model.dao.ClienteDao;
import model.dao.DaoFactory;
import model.dao.PedidoDao;
import model.entities.Pedido;

public class ProgramaPedidos {

	public static void main(String[] args) throws ParseException {
		
		PedidoDao pedidoDao = DaoFactory.createPedidoDao();
		ClienteDao clienteDao = DaoFactory.createClienteDao();

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
		
		// Localizar pedidos pelo ID no BD;
		System.out.println();
		System.out.println("Achar pedido pelo id: ");
		Pedido acharPedido = pedidoDao.acharPeloId(2);
		System.out.println(acharPedido);
	}
}