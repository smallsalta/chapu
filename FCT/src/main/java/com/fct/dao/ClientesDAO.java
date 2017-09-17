package com.fct.dao;

import java.util.List;

import com.fct.fb.ClientesFb;
import com.fct.persistencia.TClientes;

public interface ClientesDAO 
{
	public TClientes addClientes(ClientesFb fb);
	public void alterClientes(ClientesFb fb);
	public TClientes getCliente(Integer oid);
	public List<TClientes> getClientes();
	public List<TClientes> getClientes(String like);
	public void deleteClientes(Integer oid);
}
