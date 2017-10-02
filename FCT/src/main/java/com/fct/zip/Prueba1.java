package com.fct.zip;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

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
		GenerarZIP zip 				= context.getBean( GenerarZIP.class );
		
		OutputStream fos			= new FileOutputStream("C:/Users/jsilva/Desktop/facturas.zip");
	    
	    TFacturas f1				= dao.getFacturaNoLazy(25);
	    TFacturas f2				= dao.getFacturaNoLazy(26);
	    List<TFacturas> facturas	= Arrays.asList(f1, f2);
	    
		InputStream is				= ClassLoader.getSystemResourceAsStream("empresa_" + f1.getTEmpresas().getOid() + ".jpg");
		byte[] logo 				= IOUtils.toByteArray(is);
	    
	    zip.setFacturas(facturas);
	    zip.setLogo(logo);
	    
	    fos.write( zip.createFacturaZIP().toByteArray() );
		fos.close();
	    
	    System.out.println(1);
	}
}
