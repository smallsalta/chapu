package com.fct.fb;

import java.util.Date;

public class ContratosFb 
{
	private Integer oid;
	private Date fechaCert;
	private Integer numCert;
	private Integer oidclienteCert;
	private Float[] capacidadext;
	private Float[] precioext;
	private Date[] fechaext;
	private String[] placaext;
	private Integer[] agentesext;
	private Integer[] pruebasext;
	private Integer[] cantidadext;
	
	public String[] getPlacaext() {
		return placaext;
	}
	public void setPlacaext(String[] placaext) {
		this.placaext = placaext;
	}
	public Float[] getCapacidadext() {
		return capacidadext;
	}
	public void setCapacidadext(Float[] capacidadext) {
		this.capacidadext = capacidadext;
	}
	public Date[] getFechaext() {
		return fechaext;
	}
	public void setFechaext(Date[] fechaext) {
		this.fechaext = fechaext;
	}
	public Integer[] getAgentesext() {
		return agentesext;
	}
	public void setAgentesext(Integer[] agentesext) {
		this.agentesext = agentesext;
	}
	public Integer[] getPruebasext() {
		return pruebasext;
	}
	public void setPruebasext(Integer[] pruebasext) {
		this.pruebasext = pruebasext;
	}
	public Date getFechaCert() {
		return fechaCert;
	}
	public void setFechaCert(Date fechaCert) {
		this.fechaCert = fechaCert;
	}
	public Integer getNumCert() {
		return numCert;
	}
	public void setNumCert(Integer numCert) {
		this.numCert = numCert;
	}
	public Integer getOidclienteCert() {
		return oidclienteCert;
	}
	public void setOidclienteCert(Integer oidclienteCert) {
		this.oidclienteCert = oidclienteCert;
	}
	public Float[] getPrecioext() {
		return precioext;
	}
	public void setPrecioext(Float[] precioext) {
		this.precioext = precioext;
	}
	public Integer getOid() {
		return oid;
	}
	public void setOid(Integer oid) {
		this.oid = oid;
	}
	public Integer[] getCantidadext() {
		return cantidadext;
	}
	public void setCantidadext(Integer[] cantidadext) {
		this.cantidadext = cantidadext;
	}
}
