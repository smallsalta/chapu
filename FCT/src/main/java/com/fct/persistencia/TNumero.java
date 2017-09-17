package com.fct.persistencia;

public class TNumero 
{
	private int numero;
	private int anyo;
	
	@Override
	public String toString() 
	{
		return "{" + this.anyo + ", " + this.numero + "}";
	}
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public int getAnyo() {
		return anyo;
	}
	public void setAnyo(int anyo) {
		this.anyo = anyo;
	}
	
	
}
