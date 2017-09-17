package com.fct.zip;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.beans.factory.annotation.Autowired;

import com.fct.comun.Miscelanea;
import com.fct.pdf.ContratoPDF;
import com.fct.pdf.GenerarPDF;
import com.fct.persistencia.TContratos;
import com.fct.persistencia.TFacturas;
import com.itextpdf.text.DocumentException;

public class GenerarZIPImpl 
implements GenerarZIP
{
	@Autowired
	private GenerarPDF fpdf;
	
	@Autowired
	private ContratoPDF cpdf;
	
	@Autowired
	private Miscelanea misc;
	
	private byte[] logo;
	private byte[] sello;
	private List<TFacturas> facturas;
	private List<TContratos> contratos;
	
	public ByteArrayOutputStream createFacturaZIP() 
	throws DocumentException, IOException
	{
		ByteArrayOutputStream salida	= new ByteArrayOutputStream();
	    ZipOutputStream out 			= new ZipOutputStream(salida);

	    for( TFacturas f : this.facturas )
	    {
	    	this.crearEntradaZip(out, f);
	    }

	    out.close();
	    
	    return salida;
	}
	
	public ByteArrayOutputStream createContratoZIP() 
	throws DocumentException, IOException
	{
		ByteArrayOutputStream salida	= new ByteArrayOutputStream();
	    ZipOutputStream out 			= new ZipOutputStream(salida);

	    for( TContratos c : this.contratos )
	    {
	    	this.crearEntradaZip(out, c);
	    }

	    out.close();
	    
	    return salida;
	}
	
	private void crearEntradaZip(ZipOutputStream out, TFacturas f) 
	throws IOException, DocumentException
	{
		byte[] pdf		= this.fpdf.createPDF(f, this.logo).toByteArray();	
		String nombre	= this.misc.facturaNombre(f);
	    ZipEntry e 		= new ZipEntry(nombre);
	    
	    out.putNextEntry(e);
	    out.write( pdf, 0, pdf.length );
	    out.closeEntry();
	}
	
	private void crearEntradaZip(ZipOutputStream out, TContratos c) 
	throws IOException, DocumentException
	{
		byte[] pdf		= this.cpdf.createPDF(this.sello, c).toByteArray();	
		String nombre	= this.misc.contratoNombre(c);
	    ZipEntry e 		= new ZipEntry(nombre);
	    
	    out.putNextEntry(e);
	    out.write( pdf, 0, pdf.length );
	    out.closeEntry();
	}

	public List<TFacturas> getFacturas() {
		return facturas;
	}

	public void setFacturas(List<TFacturas> facturas) {
		this.facturas = facturas;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	public List<TContratos> getContratos() {
		return contratos;
	}

	public void setContratos(List<TContratos> contratos) {
		this.contratos = contratos;
	}

	public void setSello(byte[] sello) {
		this.sello = sello;
	}
}