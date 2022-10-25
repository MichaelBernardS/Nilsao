package model.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	private Integer id;
	private Date data;
	
	private Cliente cliente;
	
	private List<ItemPedido> itens = new ArrayList<>();
	
	public Pedido() {
	}

	public Pedido(Integer id, Date data, Cliente cliente) {
		this.id = id;
		this.data = data;
		this.cliente = cliente;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public List<ItemPedido> getItens() {
		return itens;
	}
	
	public void addItem(ItemPedido item) {
		itens.add(item);
	}
	
	public void removeItem(ItemPedido item) {
		itens.remove(item);
	}
	
	public Double total() {
		double soma = 0.0;
		for (ItemPedido i : itens) {
			soma += i.subTotal();
		}
		return soma;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(cliente, data, id, itens, sdf);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		return Objects.equals(cliente, other.cliente) && Objects.equals(data, other.data)
				&& Objects.equals(id, other.id) && Objects.equals(itens, other.itens)
				&& Objects.equals(sdf, other.sdf);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Pedido: " + "Id: " + id);
		sb.append(", Data: " + sdf.format(data));
		sb.append(", " + cliente + "\n");
		sb.append("Itens: " + "\n");
		for (ItemPedido i : itens) {
			sb.append(i + "\n");
		}
		sb.append("Preço total: R$" + String.format("%.2f", total()));
	return sb.toString();
	}
}