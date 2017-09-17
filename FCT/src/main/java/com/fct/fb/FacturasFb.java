package com.fct.fb;

public class FacturasFb 
{
	private Integer oid;
	private Integer oidcliente;
	private Integer oidempresa;
	private Integer oidpago;
	private Integer numero;
	private String fecha;
	private Float iva;
	private Float[] cantidad;
	private String[] descripcion;
	private Float[] precio;
	
	public Integer getOidcliente() {
		return oidcliente;
	}
	public void setOidcliente(Integer oidcliente) {
		this.oidcliente = oidcliente;
	}
	public Integer getOidempresa() {
		return oidempresa;
	}
	public void setOidempresa(Integer oidempresa) {
		this.oidempresa = oidempresa;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public Float getIva() {
		return iva;
	}
	public void setIva(Float iva) {
		this.iva = iva;
	}
	public Float[] getCantidad() {
		return cantidad;
	}
	public void setCantidad(Float[] cantidad) {
		this.cantidad = cantidad;
	}
	public String[] getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String[] descripcion) {
		this.descripcion = descripcion;
	}
	public Float[] getPrecio() {
		return precio;
	}
	public void setPrecio(Float[] precio) {
		this.precio = precio;
	}
	public Integer getOid() {
		return oid;
	}
	public void setOid(Integer oid) {
		this.oid = oid;
	}
	public Integer getOidpago() {
		return oidpago;
	}
	public void setOidpago(Integer oidpago) {
		this.oidpago = oidpago;
	}
}
