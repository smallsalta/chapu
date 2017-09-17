package com.fct.dao;

import java.util.List;
import java.util.Map;

import com.fct.fb.FacturasBuscarFb;
import com.fct.persistencia.TInforme;

public interface InformesDAO 
{
	public List<TInforme> getInformeFacturacion(FacturasBuscarFb fb);
	@SuppressWarnings("rawtypes")
	public Map<String, Map> getInformeEstado(FacturasBuscarFb fb);
	public List<TInforme> getInformeRecordatorio(FacturasBuscarFb fb);
}
