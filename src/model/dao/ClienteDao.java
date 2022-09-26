package model.dao;

import java.util.List;

import model.entities.Cliente;

public interface ClienteDao {
	
	void adicionarCliente(Cliente obj);
	void atualizar(Cliente obj);
	void deletarPeloId(Integer id);
	Cliente acharPeloId(Integer id);
	List<Cliente> acharTodos();
}