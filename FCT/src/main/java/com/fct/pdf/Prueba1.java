package com.fct.pdf;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fct.dao.FacturasDAO;
import com.fct.persistencia.TFacturas;
import com.itextpdf.text.DocumentException;

public class Prueba1 
{
	public static void main(String ... args) 
	throws DocumentException, IOException
	{
		ApplicationContext context	= new ClassPathXmlApplicationContext("classpath:beans*.xml");
		FacturasDAO dao				= context.getBean( FacturasDAO.class );
		GenerarPDF pdf				= context.getBean( GenerarPDF.class );
		
		InputStream ise				= ClassLoader.getSystemResourceAsStream("empresa_3.jpg");
		byte[] bytes 				= IOUtils.toByteArray(ise);
		
		TFacturas tf				= dao.getFacturaNoLazy(25);
		
		OutputStream fos			= new FileOutputStream("C:/Users/jsilva/Desktop/f.pdf");
		
		fos.write( pdf.createPDF(tf, bytes).toByteArray() );
		fos.flush();
		fos.close();
		
		System.out.println(1);
	}
}