package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Item implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String descricao;
	private Double preco;
	
	public Item() {
	}

	public Item(Integer id, String descricao, Double preco) {
		this.id = id;
		this.descricao = descricao;
		this.preco = preco;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	@Override
	public int hashCode() {
		return Objects.hash(descricao, id, preco);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		return Objects.equals(descricao, other.descricao) && Objects.equals(id, other.id)
				&& Objects.equals(preco, other.preco);
	}

	@Override
	public String toString() {
		return "Item: Id: " + id + ", Descricao: " + descricao + ", Preço: " + String.format("%.2f", preco);
	}
}