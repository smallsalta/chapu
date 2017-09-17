package com.fct.fb;

public class FacturasBuscarFb 
{
	private String fini;
	private String ffin;
	private Integer oidempresa;
	private Integer oidcliente;
	private Integer oidpago;
	private Integer numfactura;
	private boolean checkcliente;
	private boolean checkempresa;
	private boolean checkfactura;
	private String[] fctoid;
	
	public String getFini() {
		return fini;
	}
	public String getFfin() {
		return ffin;
	}
	public Integer getOidempresa() {
		return oidempresa;
	}
	public Integer getOidcliente() {
		return oidcliente;
	}
	public Integer getOidpago() {
		return oidpago;
	}
	public Integer getNumfactura() {
		return numfactura;
	}
	public boolean isCheckcliente() {
		return checkcliente;
	}
	public boolean isCheckempresa() {
		return checkempresa;
	}
	public boolean isCheckfactura() {
		return checkfactura;
	}
	public void setFini(String fini) {
		this.fini = fini;
	}
	public void setFfin(String ffin) {
		this.ffin = ffin;
	}
	public void setOidempresa(Integer oidempresa) {
		this.oidempresa = oidempresa;
	}
	public void setOidcliente(Integer oidcliente) {
		this.oidcliente = oidcliente;
	}
	public void setOidpago(Integer oidpago) {
		this.oidpago = oidpago;
	}
	public void setNumfactura(Integer numfactura) {
		this.numfactura = numfactura;
	}
	public void setCheckcliente(boolean checkcliente) {
		this.checkcliente = checkcliente;
	}
	public void setCheckempresa(boolean checkempresa) {
		this.checkempresa = checkempresa;
	}
	public void setCheckfactura(boolean checkfactura) {
		this.checkfactura = checkfactura;
	}
	public String[] getFctoid() {
		return fctoid;
	}
	public void setFctoid(String[] fctoid) {
		this.fctoid = fctoid;
	}
}