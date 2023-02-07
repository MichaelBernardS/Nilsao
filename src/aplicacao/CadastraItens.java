package aplicacao;

import model.dao.DaoFactory;
import model.dao.ItemDao;
import model.entities.Item;

public class CadastraItens {

	public static void main(String[] args) {
		
		ItemDao itemDao = DaoFactory.createItemDao();
		
		// Adição de 3 itens no BD: Bolacha, Macarrão e Leite;
		System.out.println("Inser��o de itens:");
		Item Item1 = new Item(null, "Bolacha", 7.50);
		itemDao.adicionarItem(Item1);
		System.out.println("Adicionado! Novo id: " + Item1.getId());
		Item Item2 = new Item(null, "Macarr�o", 13.15);
		itemDao.adicionarItem(Item2);
		System.out.println("Adicionado! Novo id: " + Item2.getId());
		Item Item3 = new Item(null, "Leite", 4.99);
		itemDao.adicionarItem(Item3);
		System.out.println("Adicionado! Novo id: " + Item3.getId());
		
		// Localizar itens pelo ID no BD;
		System.out.println();
		System.out.println("Achar item pelo id: ");
		Item acharItem = itemDao.acharPeloId(1);
		System.out.println(acharItem);
	}
}