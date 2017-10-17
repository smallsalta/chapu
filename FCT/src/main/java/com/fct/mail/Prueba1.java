package com.fct.mail;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.mail.MessagingException;

import org.apache.commons.io.IOUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fct.dao.FacturasDAO;
import com.fct.pdf.FacturaPDF;
import com.fct.persistencia.TFacturas;
import com.itextpdf.text.DocumentException;

public class Prueba1 {

	public static void main(String[] args) 
	throws MessagingException, IOException, DocumentException 
	{
		ApplicationContext context	= new ClassPathXmlApplicationContext("classpath:beans_*.xml");
		
		FacturasDAO dao		= context.getBean( FacturasDAO.class );
		FacturaPDF pdf		= context.getBean( FacturaPDF.class );
		MandarCorreo mail	= context.getBean( MandarCorreo.class );
		
		InputStream ise		= ClassLoader.getSystemResourceAsStream("empresa_3.jpg");
		byte[] bytes 		= IOUtils.toByteArray(ise);
		TFacturas tf		= dao.getFacturaNoLazy(1135);
		byte[] fact			= pdf.createPDF(tf, bytes).toByteArray();
		
		mail.send( tf.getTClientes().getEmail(), fact );
		
		System.out.println(mail);
	}
	
	public static byte[] getPDF() 
	throws IOException
	{
		FileInputStream fis 	= new FileInputStream("C:/Users/jsilva/Desktop/xxx.pdf");
		byte[] bytes 			= IOUtils.toByteArray(fis);
		
		fis.close();
		
		return bytes;
	}
}