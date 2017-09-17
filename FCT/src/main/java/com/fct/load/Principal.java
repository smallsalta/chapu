package com.fct.load;

import java.sql.SQLException;

public class Principal 
{
	/**
	 * @param args
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) 
	throws ClassNotFoundException, SQLException 
	{
		ParserAccess pa = new ParserAccess();
		
//		pa.setFileName("C:/Documents and Settings/josea.silva.ext/Escritorio/clientes.mdb");
		pa.setFileName("C:/Users/jsilva/Desktop/clientes.mdb");
		pa.setDatosMy( pa.getDatosAccess() );
	}
}
