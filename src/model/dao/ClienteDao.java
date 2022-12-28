package model.dao;

import model.entities.Cliente;

public interface ClienteDao {
	
	void adicionarCliente(Cliente obj);
	Cliente acharPeloId(Integer id);
}