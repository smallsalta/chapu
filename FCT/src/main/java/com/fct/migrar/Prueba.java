package com.fct.migrar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Prueba 
{
	public static void main (String ... args) 
	throws IOException
	{
		String directorio	= "C:/Users/jsilva/Desktop/";
		File fin			= new File(directorio + "127_0_0_1(1).sql");
		File fout			= new File(directorio + "fct_datos.sql");
		FileReader fr 		= new FileReader(fin);
		FileWriter fw 		= new FileWriter(fout);
		BufferedReader br 	= new BufferedReader(fr); 
		String s			= null;
		String retorno		= "\\r\\n";
		String token		= "#";
		
		while( (s = br.readLine()) != null ) 
		{
			String aux		= s.replace(retorno, token);
			String[] partes = aux.split(token);
			
			for(String p : partes)
			{
				fw.write(p);
				fw.write("\n");
				fw.flush();
				
				System.out.println(p);
			}
		} 
		
		fw.close();
		br.close();
		
		System.out.println("ZA KA BO");
	}
}
