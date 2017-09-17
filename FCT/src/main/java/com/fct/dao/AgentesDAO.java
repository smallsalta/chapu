package com.fct.dao;

import java.util.List;

import com.fct.persistencia.TAgentes;

public interface AgentesDAO 
{
	public List<TAgentes> getAgentes();
	public TAgentes getAgente(int oid);
}
