package com.fct.persistencia;

// Generated 13-sep-2013 13:34:07 by Hibernate Tools 3.4.0.CR1

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * TContratos generated by hbm2java
 */
@Entity
@Table(name = "t_contratos", uniqueConstraints = @UniqueConstraint(columnNames = "numero"))
public class TContratos implements java.io.Serializable {

	private Integer oid;
	private TClientes TClientes;
	private Date fecha;
	private int numero;
	private List<TLineasContrato> TLineasContratos = new LinkedList<TLineasContrato>();

	public TContratos() {
	}

	public TContratos(TClientes TClientes, Date fecha, int numero) {
		this.TClientes = TClientes;
		this.fecha = fecha;
		this.numero = numero;
	}

	public TContratos(TClientes TClientes, Date fecha, int numero,
			List<TLineasContrato> TLineasContratos) {
		this.TClientes = TClientes;
		this.fecha = fecha;
		this.numero = numero;
		this.TLineasContratos = TLineasContratos;
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
	@JoinColumn(name = "oidcliente", nullable = false)
	public TClientes getTClientes() {
		return this.TClientes;
	}

	public void setTClientes(TClientes TClientes) {
		this.TClientes = TClientes;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha", nullable = false, length = 10)
	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Column(name = "numero", unique = true, nullable = false)
	public int getNumero() {
		return this.numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "TContratos")
	@Cascade({CascadeType.ALL})
	public List<TLineasContrato> getTLineasContratos() {
		return this.TLineasContratos;
	}

	public void setTLineasContratos(List<TLineasContrato> TLineasContratos) {
		this.TLineasContratos = TLineasContratos;
	}

}
