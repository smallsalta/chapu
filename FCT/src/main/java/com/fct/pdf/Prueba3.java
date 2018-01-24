package com.fct.pdf;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fct.dao.ContratosDAO;
import com.fct.fb.ContratosBuscarFb;
import com.fct.persistencia.TContratos;
import com.itextpdf.text.DocumentException;

public class Prueba3 
{
	public static void main(String ... args) 
	throws DocumentException, IOException
	{
		ApplicationContext context	= new ClassPathXmlApplicationContext("classpath:beans*.xml");
		ContratosDAO dao			= context.getBean( ContratosDAO.class );
		ContratoPDF pdf				= context.getBean( ContratoPDF.class );
		
		InputStream iss				= ClassLoader.getSystemResourceAsStream("sello.png");
		InputStream ise				= ClassLoader.getSystemResourceAsStream("oca.jpg");
		
		byte[] bytesSello			= IOUtils.toByteArray(iss);
		byte[] bytesEmpresa			= IOUtils.toByteArray(ise);
		
		ContratosBuscarFb fb		= new ContratosBuscarFb();
		Calendar cal 				= Calendar.getInstance();
		
		cal.set(2017, 11, 1);
		fb.setFini( cal.getTime() );
		
		cal.set(2018, 0, 20);
		fb.setFfin( cal.getTime() );
		
		List<TContratos> lcont		= dao.getContratos(fb);
		
		for(TContratos t : lcont)
		{
			TContratos tc		= dao.getContratoNoLazy( t.getOid() );
			OutputStream fos	= new FileOutputStream("C:/Users/jsilva/Desktop/tmp/" + t.getOid() + ".pdf");
			
			pdf.setContrato(tc);
			pdf.setSello(bytesSello);
			pdf.setEmpresa(bytesEmpresa);
			
			fos.write( pdf.createPDF().toByteArray() );
			fos.flush();
			fos.close();
		}
		
		System.out.println( lcont.size() );
	}
}