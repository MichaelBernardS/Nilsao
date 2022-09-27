package model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ItemPedido implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer qtde;
	private Double precoVenda;
	
	private Pedido pedido;
	private Item item;
	
	private List<Item> itens = new ArrayList<>();
	
	public ItemPedido() {
	}

	public ItemPedido(Integer id, Integer qtde, Double precoVenda, Pedido pedido, Item item) {
		this.id = id;
		this.qtde = qtde;
		this.precoVenda = precoVenda;
		this.pedido = pedido;
		this.item = item;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQtde() {
		return qtde;
	}

	public void setQtde(Integer qtde) {
		this.qtde = qtde;
	}

	public Double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(Double precoVenda) {
		this.precoVenda = precoVenda;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public List<Item> getItens() {
		return itens;
	}
	
	public void addItem(Item item) {
		itens.add(item);
	}
	
	public void removeItem(Item item) {
		itens.remove(item);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, item, itens, pedido, precoVenda, qtde);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		return Objects.equals(id, other.id) && Objects.equals(item, other.item) && Objects.equals(itens, other.itens)
				&& Objects.equals(pedido, other.pedido) && Objects.equals(precoVenda, other.precoVenda)
				&& Objects.equals(qtde, other.qtde);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("ItemPedido: " + "Id: " + id);
		sb.append(", Qtde: " + qtde);
		sb.append(", PrecoVenda: " + String.format("%.2f", precoVenda) + "\n");
		sb.append(pedido + "\n");
		sb.append("Itens: " + "\n");
		for (Item i : itens) {
			sb.append("Id: " + i.getId() + "," + " Descrição: " + i.getDescricao() + "," + " Preco: " + i.getPreco() + "\n");
		}
	return sb.toString();
	}
	
	public double total() {
		return this.precoVenda += precoVenda;
	}
	
}