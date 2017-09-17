package com.fct.dao;

import com.fct.persistencia.TLineasFactura;


public interface LineasFacturaDAO 
{
	public void deleteLineaFacturas(Integer oid);
	public void deleteLineaFacturas(TLineasFactura lf);
}
