package com.fct.pdf;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fct.dao.ContratosDAO;
import com.fct.persistencia.TContratos;
import com.itextpdf.text.DocumentException;

public class Prueba2 
{
	public static void main(String ... args) 
	throws DocumentException, IOException
	{
		ApplicationContext context	= new ClassPathXmlApplicationContext("classpath:beans*.xml");
		ContratosDAO dao			= context.getBean( ContratosDAO.class );
		ContratoPDF pdf				= context.getBean( ContratoPDF.class );
		
		InputStream iss				= ClassLoader.getSystemResourceAsStream("sello.png");
		byte[] bytesSello			= IOUtils.toByteArray(iss);
		
		TContratos tc				= dao.getContratoNoLazy(216);
		
		OutputStream fos			= new FileOutputStream("C:/Users/jsilva/Desktop/f.pdf");
		
		pdf.setContrato(tc);
		pdf.setSello(bytesSello);
		
		fos.write( pdf.createPDF().toByteArray() );
		fos.flush();
		fos.close();
		
		System.out.println(1);
	}
}