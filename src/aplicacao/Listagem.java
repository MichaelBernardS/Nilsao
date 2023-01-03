package aplicacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.ItemPedidoDao;
import model.dao.PedidoDao;

public class Listagem {
	
    public static void main(String[] args) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        ItemPedidoDao itemPedidoDao = DaoFactory.createItemPedidoDao();
        PedidoDao pedidoDao = DaoFactory.createPedidoDao();

        // Solicitações do Nilson:
        System.out.println();
        System.out.println("   Data    Pedidos Total");
        System.out.println("---------- ------- -----");
        Double soma = 0.0;
        List<String> groupByDate = pedidoDao.groupByDate(sdf.parse("01/09/2022"), sdf.parse("30/09/2022"));
        List<Integer> countByDate = pedidoDao.countByDate(sdf.parse("01/09/2022"), sdf.parse("30/09/2022"));
        List<Double> somaDosPedidosPorData = itemPedidoDao.somaDosPedidosPorData(sdf.parse("01/09/2022"), sdf.parse("30/09/2022"));
        for (int i=0;i<groupByDate.size() && i<countByDate.size() && i<somaDosPedidosPorData.size();i++) {
            System.out.print(groupByDate.get(i) + "    ");
            System.out.print(countByDate.get(i) + "    ");
            System.out.println(somaDosPedidosPorData.get(i));
        }
        for (Double obj : somaDosPedidosPorData) {
            soma += obj;
        }
        System.out.println();
        System.out.println("Total geral:       " + String.format("%.2f", soma));
    }
}