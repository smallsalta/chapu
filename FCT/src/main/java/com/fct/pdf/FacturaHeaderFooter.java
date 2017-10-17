package com.fct.pdf;

import java.io.UnsupportedEncodingException;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class FacturaHeaderFooter 
extends PdfPageEventHelper 
{
	private float base;
	private float iva;
	private String ccc;
	private String info;

	public void onEndPage(PdfWriter writer, Document document) 
	{
        try 
        {
			this.crearTablaResumen(writer);
		} 
        catch (UnsupportedEncodingException e) 
        {
			e.printStackTrace();
		}
        
		this.crearNumeroPie(writer);
	}
	
	private void crearNumeroPie(PdfWriter writer)
	{
		PdfContentByte cb	= writer.getDirectContent();
		String txt			= " - " + writer.getPageNumber() + " - ";
		Font font			= new Font();
		
		font.setSize(8);
		
		Phrase fr 	= new Phrase(txt, font);
		
		ColumnText.showTextAligned( cb, Element.ALIGN_CENTER, fr, 300f, 20f, 0 );
	}
	
	private void crearTablaResumen(PdfWriter writer) 
	throws UnsupportedEncodingException
	{
		int cont			= 2;
		int separar			= 80;		
		PdfPTable tcabecera	= new PdfPTable(3);
		
        if( this.info != null && this.info.length()>0 )
        {
        	cont			+= 2;
        	separar			+= 30;
        	
        	this.filaExtra( tcabecera, this.info );
        }
        
        if( this.ccc != null && this.ccc.length()>0 )
        {
        	cont			+= 2;
        	separar			+= 30;
        	
        	this.filaExtra( tcabecera, "Número de cuenta bancaria: " + this.ccc );
        }
        
        this.filaNumero(tcabecera);
        
        tcabecera.setTotalWidth(400f);
        tcabecera.writeSelectedRows( 0, cont, 100, separar, writer.getDirectContent() );
	}
	
	/**
	 * Cajón del base, IVA y total
	 * @param tcabecera
	 * @throws UnsupportedEncodingException
	 */
	private void filaNumero(PdfPTable tcabecera) 
	throws UnsupportedEncodingException
	{
		float biva			= this.base * (this.iva / 100);
		
		PdfPCell base		= ComunPDF.createCeldaTH("Base");
        PdfPCell iva		= ComunPDF.createCeldaTH("IVA - " + (int)this.iva + "%");
        PdfPCell total		= ComunPDF.createCeldaTH("Total");
        
        PdfPCell base1		= ComunPDF.createCeldaPrecio(this.base);
        PdfPCell iva1		= ComunPDF.createCeldaPrecio(biva);
        PdfPCell total1		= ComunPDF.createCeldaPrecio(this.base + biva);
        
        ComunPDF.celdaBonita(base1);
        ComunPDF.celdaBonita(iva1);
        ComunPDF.celdaBonita(total1);
        
        tcabecera.addCell(base);
        tcabecera.addCell(iva);
        tcabecera.addCell(total);
        tcabecera.addCell(base1);
        tcabecera.addCell(iva1);
        tcabecera.addCell(total1);
	}
	
	/**
	 * Fila extra del CCC o de la dirección de la empresa
	 * @param tcabecera
	 * @param txt
	 */
	private void filaExtra(PdfPTable tcabecera, String txt)
	{
		Font f = new Font();
		f.setSize(11F);
		
        PdfPCell ccc	= ComunPDF.createCeldaDescripcion(txt, f);
        ccc.setColspan(3);
        
        ComunPDF.celdaBonita(ccc);
        
        tcabecera.addCell(ccc);
        tcabecera.addCell( ComunPDF.createFilaBlanco() );		
	}

	public float getBase() {
		return base;
	}

	public void setBase(float base) {
		this.base = base;
	}

	public float getIva() {
		return iva;
	}

	public void setIva(float iva) {
		this.iva = iva;
	}

	public String getCcc() {
		return ccc;
	}

	public void setCcc(String ccc) {
		this.ccc = ccc;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
}