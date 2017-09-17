package com.fct.order;

import java.util.Comparator;

import com.fct.persistencia.TPagos;

public class TPagosComp implements Comparator<TPagos>
{
	public int compare(TPagos o1, TPagos o2) 
	{
		return o1.getDescr().compareTo( o2.getDescr() );
	}
}