package com.fct.fb;

public class ClientesFb 
{
	private Integer oid;
	private String dni;
	private String nombre;
	private String apellidos;
	private String direccion;
	private String localidad;
	private String provincia;
	private Integer cp;
	private Integer telefono1;
	private Integer telefono2;
	private String observaciones;
	private String email;
	
	public String toString()
	{
		return this.nombre + " " + this.apellidos;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public Integer getCp() {
		return cp;
	}
	public void setCp(Integer cp) {
		this.cp = cp;
	}
	public Integer getTelefono1() {
		return telefono1;
	}
	public void setTelefono1(Integer telefono1) {
		this.telefono1 = telefono1;
	}
	public Integer getTelefono2() {
		return telefono2;
	}
	public void setTelefono2(Integer telefono2) {
		this.telefono2 = telefono2;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public Integer getOid() {
		return oid;
	}
	public void setOid(Integer oid) {
		this.oid = oid;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
