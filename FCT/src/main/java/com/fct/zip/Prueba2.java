package com.fct.zip;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fct.dao.FacturasDAO;
import com.fct.fb.FacturasBuscarFb;
import com.fct.persistencia.TFacturas;
import com.itextpdf.text.DocumentException;

public class Prueba2 
{
	public static void main(String ... args) 
	throws DocumentException, IOException, ParseException
	{
		long ms						= System.currentTimeMillis();
		
		ApplicationContext context	= new ClassPathXmlApplicationContext("classpath:beans*.xml");
		FacturasDAO dao				= context.getBean( FacturasDAO.class );
		GenerarZIP zip 				= context.getBean( GenerarZIP.class );
		
		FacturasBuscarFb fb			= new FacturasBuscarFb();
		fb.setFini("2017-07-01");
		fb.setFfin("2017-09-30");
		fb.setCheckempresa(true);
		fb.setOidempresa(2);
		
		List<TFacturas> facturas 	= dao.getFacturas(fb);
		List<TFacturas> facturasnl 	= new LinkedList<TFacturas>();
		TFacturas f					= facturas.get(0);
		InputStream is				= ClassLoader.getSystemResourceAsStream("empresa_" + f.getTEmpresas().getOid() + ".jpg");
		byte[] logo 				= IOUtils.toByteArray(is);
		FileOutputStream fos		= new FileOutputStream("C:/Users/jsilva/Desktop/jul-ago-sept.zip");
		
		for(TFacturas flz : facturas)
		{
			facturasnl.add( dao.getFacturaNoLazy( flz.getOid() ) );
		}
		
		zip.setFacturas(facturasnl);
		zip.setLogo(logo);
		
		fos.write( zip.createFacturaZIP().toByteArray() );
		fos.close();
	    
		ms						= System.currentTimeMillis() - ms;
		
	    System.out.println( ms/1000 );
	}
}
