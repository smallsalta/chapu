package com.fct.pdf;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.fct.persistencia.TFacturas;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;

public class ComunPDF 
{
	public static PdfPCell createCeldaTH(String d) 
	throws UnsupportedEncodingException
	{
		PdfPCell c 	= new PdfPCell( new Phrase(d) );
		 
		c.setBackgroundColor( BaseColor.LIGHT_GRAY );
		ComunPDF.celdaBonita(c);
		 
		return c;
	}
	
	public static PdfPCell createCeldaNumero(int i)
	{
		NumberFormat df = new DecimalFormat("0000");
		String fmt 		= df.format(i);
		PdfPCell c		= new PdfPCell( new Phrase(fmt) );
		
		ComunPDF.celdaBonita(c);
		
		return c;
	}
	
	public static PdfPCell createCeldaPrecio(Float f)
	{
		return ComunPDF.createCeldaFloat( f, "###,###,##0.00", null );
	}
	
	public static PdfPCell createCeldaPrecio(Float f, Font fnt)
	{
		return ComunPDF.createCeldaFloat( f, "###,###,##0.00", fnt );
	}
	
	public static PdfPCell createCeldaCantidad(float f)
	{
		return ComunPDF.createCeldaFloat( f, "###,###,##0.##", null );
	}
	
	public static PdfPCell createCeldaDescripcion(String d)
	{
		return ComunPDF.createCeldaDescripcion( new Phrase(d) );
	}
	
	public static PdfPCell createCeldaDescripcion(String d, Font fnt)
	{
		return ComunPDF.createCeldaDescripcion( new Phrase(d, fnt) );
	}
	
	private static PdfPCell createCeldaDescripcion(Phrase fr)
	{
		PdfPCell c = new PdfPCell(fr);
		c.setBorder( Rectangle.NO_BORDER );
		c.setPaddingLeft(5f);
		return c;
	}
	
	public static PdfPCell createCeldaFecha(Date d)
	{
		return ComunPDF.createCeldaFecha2( d, null );
	}
	
	public static PdfPCell createCeldaFecha(Date d, Font fnt)
	{
		PdfPCell cf = ComunPDF.createCeldaFecha2( d, fnt );
		
		cf.setBorder(0);
		
		return cf;
	}
	
	private static PdfPCell createCeldaFecha2(Date d, Font fnt)
	{
		DateFormat df 	= new SimpleDateFormat("dd/MM/yyyy");
		String fmt 		= df.format(d);
		Phrase fr		= (fnt != null) ? new Phrase(fmt, fnt) : new Phrase(fmt);
		PdfPCell c		= new PdfPCell(fr);
		
		ComunPDF.celdaBonita(c);
		 
		return c;
	}
	
	public static PdfPCell createFilaBlanco() 
	{
		PdfPCell blanco	= new PdfPCell( new Phrase(" ") );
		blanco.setBorder( Rectangle.NO_BORDER );
		blanco.setColspan(3);
        
        return blanco;
	}
	
	private static PdfPCell createCeldaFloat(Float f, String format, Font fnt)
	{
		PdfPCell c 	= null;
		Phrase fr	= null;
		
		if( f == null || f == 0f )
		{
			fr = (fnt != null) ? new Phrase(" ", fnt) : new Phrase(" ");
		}
		else
		{	
			DecimalFormatSymbols otherSymbols 	= new DecimalFormatSymbols( new Locale("es", "ES") );
			DecimalFormat df					= new DecimalFormat(format, otherSymbols);
			
			otherSymbols.setDecimalSeparator(',');
			otherSymbols.setGroupingSeparator('.');
			
			fr = (fnt != null) ? new Phrase( df.format(f), fnt ) : new Phrase( df.format(f) );
		}
		
		c = new PdfPCell(fr);
		
		c.setBorder( Rectangle.NO_BORDER );
		c.setPaddingLeft(5f);
		
		return c;
	}
	
	public static void celdaBonita(PdfPCell c)
	{
		c.setHorizontalAlignment( Element.ALIGN_CENTER );
        c.setBorder( Rectangle.BOX );
        c.setPaddingLeft(5f);
        c.setPaddingRight(5f);
        c.setPaddingBottom(5f);
	}
	
	public static PdfPCell createCeldaCCC(TFacturas f)
	{
		Font font			= new Font();
		String nccc			= f.getTEmpresas().getCcc();
        String mensaje		= "Cuenta donde puede realizar el pago\n" + nccc;
        mensaje				= nccc == null ? "" : mensaje;
        
		font.setSize(8);
        
        Phrase txt			= new Phrase(mensaje, font);
        PdfPCell ccc		= new PdfPCell(txt);
        
        ccc.setBorder( Rectangle.NO_BORDER );
        
        return ccc;
	}
	
	public static PdfPCell createCeldaInfo(TFacturas f)
	{
		Font font			= new Font();
		
		font.setSize(9);
		 
		Phrase frase		= new Phrase( f.getTEmpresas().getInfo(), font );

		return new PdfPCell(frase);
	}
	
	public static PdfPCell createCeldaSello(byte[] sello) 
	throws BadElementException, MalformedURLException, IOException
	{
		Image imagen = Image.getInstance(sello);
		
		imagen.scalePercent(25F);
		imagen.setBorder( Image.NO_BORDER );

		PdfPCell c = new PdfPCell(imagen, false);
		
		c.setBorder( PdfPCell.NO_BORDER );
		
		return c;
	}
}
