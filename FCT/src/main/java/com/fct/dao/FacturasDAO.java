package com.fct.dao;

import java.text.ParseException;
import java.util.List;

import com.fct.fb.FacturasBuscarFb;
import com.fct.fb.FacturasFb;
import com.fct.persistencia.TFacturas;

public interface FacturasDAO 
{
	public TFacturas addFactura(FacturasFb fb) throws ParseException;
	public List<TFacturas> getFacturas(FacturasBuscarFb fb) throws ParseException;
	public TFacturas getFactura(Integer oid);
	public TFacturas getFacturaNoLazy(Integer oid);
	public int getFacturaNumero(Integer oid);
	public void deleteFacturas(Integer oid);
	public TFacturas alterFacturas(FacturasFb fb) throws ParseException;
}
