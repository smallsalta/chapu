package com.fct.order;

import java.text.Collator;
import java.util.Comparator;

import com.fct.persistencia.TClientes;

public class TClientesComp implements Comparator<TClientes>
{
	private Collator collator;
	
	public TClientesComp()
	{
		this.collator = Collator.getInstance();
		this.collator.setStrength(Collator.PRIMARY); 
	}
    
	public int compare(TClientes o1, TClientes o2) 
	{
		String nombre1 = o1.getNombre() + " " + o1.getApellidos();
		String nombre2 = o2.getNombre() + " " + o2.getApellidos();
		
		return this.collator.compare(nombre1, nombre2);
	}
}