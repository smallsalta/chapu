package com.fct.comun;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import com.fct.persistencia.TContratos;
import com.fct.persistencia.TFacturas;

public interface Miscelanea 
{
	public ModelMap crearModelo();
	public byte[] generarLogo(HttpServletRequest req, int oid) throws IOException;
	public byte[] generarSello(HttpServletRequest req) throws IOException;
	public byte[] generarEmpresa(HttpServletRequest req) throws IOException;
	public String facturaNombre(TFacturas f);
	public String contratoNombre(TContratos c);
}
