package com.fct.persistencia;

public class TInforme 
{
	private Number oid;
	private Number numero;
	private Number base;
	private Number iva;
	private Number total;
	private String cliente;
	
	public TInforme()
	{
		this.numero	= 0;
		this.base 	= 0;
		this.iva 	= 0;
		this.total 	= 0;
	}
	
	public TInforme(Number base, Number iva)
	{
		this.base	= base;
		this.iva 	= iva;
		this.total 	= base.floatValue()+ iva.floatValue();
	}
	
	public Number getNumero() {
		return numero;
	}
	public void setNumero(Number numero) {
		this.numero = numero;
	}
	public Number getBase() {
		return base;
	}
	public void setBase(Number base) {
		this.base = base;
	}
	public Number getIva() {
		return iva;
	}
	public void setIva(Number iva) {
		this.iva = iva;
	}
	public Number getTotal() {
		return total;
	}
	public void setTotal(Number total) {
		this.total = total;
	}
	public Number getOid() {
		return oid;
	}
	public void setOid(Number oid) {
		this.oid = oid;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
}
