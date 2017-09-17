package com.fct.fb;

import java.util.Date;

public class ContratosBuscarFb 
{
	private Date fini;
	private Date ffin;
	private boolean checkcliente;
	private Integer oidcliente;
	private String[] fctoid;
	
	public Date getFini() {
		return fini;
	}
	public void setFini(Date fini) {
		this.fini = fini;
	}
	public Date getFfin() {
		return ffin;
	}
	public void setFfin(Date ffin) {
		this.ffin = ffin;
	}
	public boolean isCheckcliente() {
		return checkcliente;
	}
	public void setCheckcliente(boolean checkcliente) {
		this.checkcliente = checkcliente;
	}
	public Integer getOidcliente() {
		return oidcliente;
	}
	public void setOidcliente(Integer oidcliente) {
		this.oidcliente = oidcliente;
	}
	public String[] getFctoid() {
		return fctoid;
	}
	public void setFctoid(String[] fctoid) {
		this.fctoid = fctoid;
	}	
}