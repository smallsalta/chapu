package com.fct.order;

import java.util.Comparator;

import com.fct.persistencia.TLineasFactura;

public class TLineasFacturaComp
implements Comparator<TLineasFactura>
{
	@Override
	public int compare(TLineasFactura o1, TLineasFactura o2) 
	{
		return o1.getOid().compareTo( o2.getOid() );
	}
}
