package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class ItemPedido implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer qtde;
	private Double precoVenda;
	
	private Item item;
	
	public ItemPedido() {
	}

	public ItemPedido(Integer id, Integer qtde, Double precoVenda, Item item) {
		this.id = id;
		this.qtde = qtde;
		this.precoVenda = precoVenda;
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

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	
	public Double subTotal() {
		return precoVenda * qtde;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, item, precoVenda, qtde);
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
		return Objects.equals(id, other.id) && Objects.equals(item, other.item)
				&& Objects.equals(precoVenda, other.precoVenda) && Objects.equals(qtde, other.qtde);
	}

	@Override
	public String toString() {
		return 	qtde + ", "+ getItem().getDescricao() 
				+ ", " + String.format("%.2f", precoVenda) 
				+ ", SubTotal: " + String.format("%.2f", subTotal());
	}
}