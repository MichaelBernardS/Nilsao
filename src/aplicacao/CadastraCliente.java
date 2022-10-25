package aplicacao;

import java.text.ParseException;

import model.dao.ClienteDao;
import model.dao.DaoFactory;
import model.entities.Cliente;

public class CadastraCliente {

	public static void main(String[] args) throws ParseException {
		
		ClienteDao clienteDao = DaoFactory.createClienteDao();
		
		// Adição de 3 clientes no BD: Nilson, José e Marcelo;
		System.out.println();
		System.out.println("Inserção dos clientes:");
		Cliente cliente1 = new Cliente(null, "Nilson");
		clienteDao.adicionarCliente(cliente1);
		System.out.println("Adicionado! Novo id: " + cliente1.getId());
		Cliente cliente2 = new Cliente(null, "Jose");
		clienteDao.adicionarCliente(cliente2);
		System.out.println("Adicionado! Novo id: " + cliente2.getId());
		Cliente cliente3 = new Cliente(null, "Marcelo");
		clienteDao.adicionarCliente(cliente3);
		System.out.println("Adicionado! Novo id: " + cliente3.getId());
		
		// Localizar clientes pelo ID do BD;
		System.out.println();
		System.out.println("Achar cliente pelo id: ");
		Cliente acharCliente = clienteDao.acharPeloId(3);
		System.out.println(acharCliente);
	}
}