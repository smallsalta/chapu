package com.fct.pdf;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.fct.persistencia.TFacturas;
import com.itextpdf.text.DocumentException;

public interface FacturaPDF 
{
	public ByteArrayOutputStream createPDF() throws DocumentException, IOException;
	public ByteArrayOutputStream createPDF(TFacturas tFacturas, byte[] logo) throws DocumentException, IOException;
	public void setLogo(byte[] logo);
	public void setFactura(TFacturas factura);
	public void setTituloNumero(String txt);
}
