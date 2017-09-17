package com.fct.dao;

import java.util.List;

import com.fct.persistencia.TPruebas;

public interface PruebasDAO 
{
	public List<TPruebas> getPruebas();
	public TPruebas getPrueba(int oid);
}
