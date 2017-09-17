package com.fct.pdf;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.fct.persistencia.TClientes;
import com.fct.persistencia.TEmpresas;
import com.fct.persistencia.TFacturas;
import com.fct.persistencia.TLineasFactura;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Jpeg;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class GenerarPDFImpl 
implements GenerarPDF
{
	private final int[] porcentajes = {15, 70, 15}; 
	private TFacturas factura;
	private byte[] logo;
	private String tituloNumero;
	private float margenInferior;
	
	/**
	 * Creates a PDF document.
	 * 
	 * @param filename
	 *            the path to the new PDF document
	 * @throws DocumentException
	 * @throws IOException
	 */
	public ByteArrayOutputStream createPDF() 
	throws DocumentException, IOException 
	{
		ByteArrayOutputStream out 	= new ByteArrayOutputStream();
		Document document 			= new Document( PageSize.A4, 30f, 30f, 60f, this.margenInferior );
		PdfWriter writer 			= PdfWriter.getInstance( document, out );
		HeaderAndFooter event		= new HeaderAndFooter();
		
		event.setBase( this.calculateBase( this.factura ) );
		event.setIva( this.factura.getIva() );
		event.setCcc( this.factura.getTEmpresas().getCcc() );
		event.setInfo( this.factura.getTEmpresas().getSede() );
		
		writer.setPageEvent(event);
		
		document.open();
		document.add( this.createEsqueleto( this.factura ) );
		document.close();
		
		return out;
	}
	
	public ByteArrayOutputStream createPDF(TFacturas f, byte[] logo) 
	throws DocumentException, IOException 
	{
		StringBuilder titulo 	= new StringBuilder();
		TEmpresas e				= f.getTEmpresas();
		
		if( e.isPresupuesto() )
		{
			titulo.append("Presupuesto número");
		}
		else if( e.isB() )
		{
			titulo.append("Número");
		}
		else if( e.isProforma() )
		{
			titulo.append("Proforma número");
		}
		else
		{
			titulo.append("Factura número");
		}
		
		titulo.append("");

		this.factura		= f;
		this.logo 			= logo;
		this.tituloNumero	= titulo.toString();
		
		return this.createPDF();
	}
	
	private PdfPTable createEsqueleto(TFacturas f) 
	throws IOException, DocumentException
	{
		PdfPTable tabla 	= new PdfPTable(3);
		
		tabla.setWidths( this.porcentajes );
		
        tabla.addCell( this.createCabecera(f) );		// f1
        tabla.addCell( ComunPDF.createFilaBlanco() ); 	// f2
        tabla.addCell( this.createFechaNumero(f) ); 	// f3
        tabla.addCell( ComunPDF.createFilaBlanco() ); 	// f4
        tabla.addCell( this.createCabeceraLF() ); 		// f5
        tabla.addCell( this.createLineaLF(f) ); 		// f6

        tabla.setHeaderRows(5);
        
        return tabla;
	}
	
	private PdfPCell createCliente(TClientes c)
	{
		StringBuffer sb = new StringBuffer();
		
		sb.append( c.getNombre() + " " + c.getApellidos() );
		sb.append( "\n" );
		sb.append( c.getDni() );
		sb.append( "\n" );
		sb.append( c.getDireccion() );
		sb.append( "\n" );
		sb.append( c.getCp() + " "  + c.getLocalidad() + " (" + c.getProvincia() + ")" );
		
		PdfPCell usuario = new PdfPCell( new Phrase( sb.toString() ) );
        
        usuario.setColspan(2);
        usuario.setHorizontalAlignment( PdfPCell.ALIGN_RIGHT );
        
        usuario.setBorder( Rectangle.NO_BORDER ); 
        
        return usuario;
	}
	
	private PdfPCell createLogo(TEmpresas e) 
	throws BadElementException, IOException
	{
		Jpeg jpg = new Jpeg( this.logo );
		jpg.scaleAbsolute(140f, 100f);
		
		PdfPCell logo = new PdfPCell(jpg, false);
			
		jpg.setBorder( Jpeg.NO_BORDER );
		logo.setBorder( Rectangle.NO_BORDER ); 
		
		return logo;
	}
	
	private PdfPCell createCabecera(TFacturas f) 
	throws BadElementException, IOException
	{
		PdfPTable tcabecera	= new PdfPTable(3);
		
		PdfPCell usuario	= this.createCliente( f.getTClientes() );
		PdfPCell logo		= f.getNumero() == 0 ? 
									ComunPDF.createCeldaDescripcion(" ") : 
									this.createLogo( f.getTEmpresas() );
		
		logo.setBorder( Rectangle.NO_BORDER ); 
		usuario.setBorder( Rectangle.BOX );
		
		usuario.setPadding(10f);
		usuario.setColspan(2);
		
		tcabecera.addCell(logo);
		tcabecera.addCell(usuario);
		
		PdfPCell cabecera	= new PdfPCell(tcabecera);
		cabecera.setBorder( Rectangle.NO_BORDER );
		cabecera.setColspan(3);
		
		return cabecera;
	}
	
	private PdfPCell createFechaNumero(TFacturas f) 
	throws DocumentException, UnsupportedEncodingException 
	{
		PdfPTable tfnum	= new PdfPTable(3);
		tfnum.setWidths( new int[]{45, 25, 30} );
				
        PdfPCell blanco		= ComunPDF.createCeldaInfo(f);
        PdfPCell fecha		= ComunPDF.createCeldaTH("Fecha");
        PdfPCell numero		= ComunPDF.createCeldaTH( this.tituloNumero );
        
        blanco.setBorder( Rectangle.NO_BORDER ); 
        blanco.setRowspan(2);
        
        tfnum.addCell(blanco);
        tfnum.addCell(fecha);
        tfnum.addCell(numero);
        
        PdfPCell fecha1		= ComunPDF.createCeldaFecha( f.getFecha() );
        PdfPCell numero1	= ComunPDF.createCeldaNumero( f.getNumero() );
        
        tfnum.addCell(fecha1);
        tfnum.addCell(numero1);
        
        PdfPCell fnum = new PdfPCell(tfnum);
        fnum.setBorder( Rectangle.NO_BORDER );
        fnum.setColspan(3);
        
        return fnum;
	}
	
	private PdfPCell createCabeceraLF() 
	throws IOException, DocumentException
	{
		PdfPTable tcabecera	= new PdfPTable(3);
		tcabecera.setWidths( this.porcentajes );
		
		PdfPCell cant		= ComunPDF.createCeldaTH("Cantidad");
        PdfPCell descr		= ComunPDF.createCeldaTH("Descripción");
        PdfPCell precio		= ComunPDF.createCeldaTH("Precio");
        
        tcabecera.addCell(cant);
        tcabecera.addCell(descr);
        tcabecera.addCell(precio);
        
		PdfPCell cabecera = new PdfPCell(tcabecera);
		cabecera.setColspan(3);
		
		return cabecera;
	}
	
	private PdfPCell createLineaLF(TFacturas f) 
	throws IOException, DocumentException
	{
		PdfPTable t = new PdfPTable(3);
		t.setWidths( this.porcentajes );
		
		for( TLineasFactura l : f.getTLineasFacturas() )
		{
			PdfPCell cant	= ComunPDF.createCeldaCantidad( l.getCantidad() );
			PdfPCell descr	= ComunPDF.createCeldaDescripcion( l.getDescripcion() );
			PdfPCell precio	= ComunPDF.createCeldaPrecio( l.getPrecio() );
			
	        precio.setHorizontalAlignment( Element.ALIGN_RIGHT );
	        
	        cant.setBorder( Rectangle.LEFT );
			descr.setBorder( Rectangle.LEFT );
			precio.setBorder( Rectangle.LEFT );
	        
			t.addCell(cant);
	        t.addCell(descr);
	        t.addCell(precio);
		}
        
        PdfPCell lf	= new PdfPCell(t);
		lf.setBorder( Rectangle.BOX );
		lf.setColspan(3);
		
		return lf;
	}
	
	private float calculateBase(TFacturas f)
	{
		float base = 0f;
		
		for(TLineasFactura lf : f.getTLineasFacturas())
		{
			base += lf.getPrecio();
		}
		
		return base;
	}

	public byte[] getLogo() {
		return logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	public TFacturas getFactura() {
		return factura;
	}
	
	public void setFactura(TFacturas factura) {
		this.factura = factura;
	}
	
	public void setTituloNumero(String txt)
	{
		this.tituloNumero = txt;
	}

	public float getMargenInferior() {
		return margenInferior;
	}

	public void setMargenInferior(float margenInferior) {
		this.margenInferior = margenInferior;
	}
}
