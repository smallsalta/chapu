package com.fct.order;

import java.util.Comparator;

import com.fct.persistencia.TEmpresas;

public class TEmpresasComp implements Comparator<TEmpresas>
{
	public int compare(TEmpresas o1, TEmpresas o2) 
	{
		return o1.getDescripcion().compareTo( o2.getDescripcion() );
	}
}