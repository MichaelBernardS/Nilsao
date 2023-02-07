package aplicacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.PedidoDao;
import model.entities.Pedido;

public class Listagem {
	
    public static void main(String[] args) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        PedidoDao pedidoDao = DaoFactory.createPedidoDao();

        // Solicitações do Nilson:
        System.out.println();
	    System.out.println("\n   Data    Pedidos Total");
		System.out.println("---------- ------- -----");
		List<Pedido> acharPelaData = pedidoDao.acharPelaData(sdf.parse("01/09/2022"), sdf.parse("30/09/2022"));
		Double somaTotal = 0.0;
		Integer qtdePedido = 0;
		Date dataAtual = null;
		Double somaPedido = 0.0;
	
		for (Pedido ped : acharPelaData) {
			
			if (dataAtual == null) {
				dataAtual = ped.getData();
			}
			
			if (dataAtual.compareTo(ped.getData()) == 0) {
				qtdePedido += 1;
				somaPedido += ped.total();
			}
	
			if (dataAtual.compareTo(ped.getData()) != 0) {
				System.out.printf(sdf.format(dataAtual) + "    " + qtdePedido + "    " + String.format("%.2f\n", somaPedido));
				somaPedido = 0.0;
				qtdePedido = 0;
				dataAtual = ped.getData();
				qtdePedido += 1;
				somaPedido += ped.total();
			}
			somaTotal += ped.total();
		}
		System.out.printf(sdf.format(dataAtual) + "    " + qtdePedido + "    " + String.format("%.2f\n", somaPedido));
		System.out.println("\nTotal geral:       " + String.format("%.2f", somaTotal));
	}
}
