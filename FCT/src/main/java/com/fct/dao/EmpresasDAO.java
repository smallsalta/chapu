package com.fct.dao;

import java.util.List;

import com.fct.persistencia.TEmpresas;

public interface EmpresasDAO 
{
	public List<TEmpresas> getEmpresas();
	public TEmpresas getEmpresa(Integer oid);
}
