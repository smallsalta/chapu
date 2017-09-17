package com.fct.zip;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import com.fct.persistencia.TContratos;
import com.fct.persistencia.TFacturas;
import com.itextpdf.text.DocumentException;

public interface GenerarZIP 
{
	public ByteArrayOutputStream createFacturaZIP() throws DocumentException, IOException;
	public ByteArrayOutputStream createContratoZIP() throws DocumentException, IOException;
	public void setFacturas(List<TFacturas> facturas);
	public void setContratos(List<TContratos> contratos);
	public void setLogo(byte[] logo);
	public void setSello(byte[] sello);
}
