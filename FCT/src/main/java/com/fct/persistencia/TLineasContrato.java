package com.fct.persistencia;

// Generated 13-sep-2013 13:34:07 by Hibernate Tools 3.4.0.CR1

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TLineasContrato generated by hbm2java
 */
@Entity
@Table(name = "t_lineas_contrato")
public class TLineasContrato implements java.io.Serializable {

	private Integer oid;
	private TPruebas TPruebas;
	private TContratos TContratos;
	private TAgentes TAgentes;
	private String numeroPlaca;
	private Float capacidad;
	private Date fecha;
	private float precio;
	private Integer cantidad;

	public TLineasContrato() {
	}

	public TLineasContrato(TPruebas TPruebas, TContratos TContratos,
			TAgentes TAgentes, String numeroPlaca, float capacidad, Date fecha,
			float precio) {
		this.TPruebas = TPruebas;
		this.TContratos = TContratos;
		this.TAgentes = TAgentes;
		this.numeroPlaca = numeroPlaca;
		this.capacidad = capacidad;
		this.fecha = fecha;
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
	@JoinColumn(name = "oidprueba", nullable = false)
	public TPruebas getTPruebas() {
		return this.TPruebas;
	}

	public void setTPruebas(TPruebas TPruebas) {
		this.TPruebas = TPruebas;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "oidcontrato", nullable = false)
	public TContratos getTContratos() {
		return this.TContratos;
	}

	public void setTContratos(TContratos TContratos) {
		this.TContratos = TContratos;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "oidagente", nullable = false)
	public TAgentes getTAgentes() {
		return this.TAgentes;
	}

	public void setTAgentes(TAgentes TAgentes) {
		this.TAgentes = TAgentes;
	}

	@Column(name = "numero_placa", nullable = false)
	public String getNumeroPlaca() {
		return this.numeroPlaca;
	}

	public void setNumeroPlaca(String numeroPlaca) {
		this.numeroPlaca = numeroPlaca;
	}

	@Column(name = "capacidad", nullable = true, precision = 12, scale = 0)
	public Float getCapacidad() {
		return this.capacidad;
	}

	public void setCapacidad(Float capacidad) {
		this.capacidad = capacidad;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha", nullable = false, length = 10)
	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Column(name = "precio", nullable = false, precision = 12, scale = 0)
	public float getPrecio() {
		return this.precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	@Column(name = "cantidad", unique = true, nullable = true)
	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	

}