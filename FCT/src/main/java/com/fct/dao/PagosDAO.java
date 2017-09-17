package com.fct.dao;

import java.util.List;

import com.fct.persistencia.TPagos;

public interface PagosDAO 
{
	public List<TPagos> getPagos();
	public TPagos getPago(int oid);
}
