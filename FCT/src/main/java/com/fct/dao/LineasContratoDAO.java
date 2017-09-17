package com.fct.dao;

import com.fct.persistencia.TLineasContrato;

public interface LineasContratoDAO 
{
	public void deleteLineaContrato(Integer oid);
	public void deleteLineaContrato(TLineasContrato lc);
}
