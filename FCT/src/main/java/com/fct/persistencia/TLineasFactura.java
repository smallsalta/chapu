package com.fct.persistencia;

// Generated 13-sep-2013 13:34:07 by Hibernate Tools 3.4.0.CR1

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * TLineasFactura generated by hbm2java
 */
@Entity
@Table(name = "t_lineas_factura")
public class TLineasFactura implements java.io.Serializable {

	private Integer oid;
	private TFacturas TFacturas;
	private float cantidad;
	private String descripcion;
	private float precio;

	public TLineasFactura() {
	}

	public TLineasFactura(TFacturas TFacturas, float cantidad,
			String descripcion, float precio) {
		this.TFacturas = TFacturas;
		this.cantidad = cantidad;
		this.descripcion = descripcion;
		this.precio = precio;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "oid", unique = true, nullable = false)
	public Integer getOid() {
		return this.oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "oidfactura", nullable = false)
	public TFacturas getTFacturas() {
		return this.TFacturas;
	}

	public void setTFacturas(TFacturas TFacturas) {
		this.TFacturas = TFacturas;
	}

	@Column(name = "cantidad", nullable = false, precision = 12, scale = 0)
	public float getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	@Column(name = "descripcion", nullable = false, length = 65535)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "precio", nullable = false, precision = 12, scale = 0)
	public float getPrecio() {
		return this.precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

}
