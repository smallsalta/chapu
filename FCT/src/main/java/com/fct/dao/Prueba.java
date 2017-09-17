package com.fct.dao;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.itextpdf.text.DocumentException;

public class Prueba 
{
	public static void main(String ... args) 
	throws DocumentException, IOException
	{
		ApplicationContext context	= new ClassPathXmlApplicationContext("classpath:beans*.xml");
		FacturasDAO dao				= context.getBean( FacturasDAO.class );
	    
	    System.out.println( dao.getFacturaNumero(3) );
	}
}
