package com.fct.dao;

import java.text.ParseException;
import java.util.List;

import com.fct.fb.ContratosBuscarFb;
import com.fct.fb.ContratosFb;
import com.fct.persistencia.TContratos;

public interface ContratosDAO 
{
	public TContratos addContrato(ContratosFb fb) throws ParseException;
	public TContratos getContrato(int oid);
	public TContratos getContratoNoLazy(int oid);
	public List<TContratos> getContratos(ContratosBuscarFb fb);
	public void deleteContratos(int oid);
	public TContratos alterContratos(ContratosFb fb) throws ParseException;
	public int getProximoNumero();
}
