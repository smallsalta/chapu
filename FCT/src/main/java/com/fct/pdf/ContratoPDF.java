package com.fct.pdf;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.fct.persistencia.TContratos;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;

public interface ContratoPDF 
{
	public ByteArrayOutputStream createPDF() throws DocumentException, IOException; 
	public ByteArrayOutputStream createPDF(byte[] sello, TContratos c) throws DocumentException, IOException; 
	public void setSello(byte[] sello) throws BadElementException, IOException;
	public void setContrato(TContratos contratos);
}
