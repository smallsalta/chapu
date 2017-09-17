package com.fct.load;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fct.dao.ClientesDAO;
import com.fct.fb.ClientesFb;

public class ParserAccess 
{
	private String fileName;
	
	private Connection getConexion() 
	throws ClassNotFoundException, SQLException
	{
		
		Properties props 	= new Properties();
		String url			= "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=" + fileName;
		
        props.put ("charSet", "iso-8859-1");
	    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	    
	    return DriverManager.getConnection( url, props );
	}
	
	private String noNull(ResultSet rs, String col) 
	throws SQLException
	{
		String txt = rs.getString(col);
		return txt == null ? "" : txt;
	}
	
	public Set<ClientesFb> getDatosAccess() 
	throws ClassNotFoundException, SQLException
	{
		Set<ClientesFb> sc	= new HashSet<ClientesFb>(); 
		
		Connection con		= this.getConexion();
	    Statement st		= con.createStatement();
	    ResultSet rs		= st.executeQuery("select * from Clientes");
	    
	    while( rs.next() )
	    {
	    	ClientesFb fb = new ClientesFb();
	    	
	    	fb.setDni( noNull(rs, "dni") );
	    	fb.setNombre( noNull(rs, "nombre") );
	    	fb.setApellidos( noNull(rs, "apellidos") );
	    	fb.setDireccion( noNull(rs, "direccion") );
	    	fb.setLocalidad( noNull(rs, "localidad") );
	    	fb.setProvincia( noNull(rs, "provincia") );
	    	fb.setCp( rs.getInt("cp") );
	    	
	    	fb.setTelefono1(0);
	    	fb.setTelefono2(0);
	    	fb.setObservaciones("");
	    	
	    	sc.add(fb);
	    }
	    
	    return sc;
	}
	
	public void setDatosMy(Set<ClientesFb> sc) 
	throws ClassNotFoundException, SQLException
	{
		ApplicationContext context	= new ClassPathXmlApplicationContext("classpath:beans*.xml");
		ClientesDAO cdao			= context.getBean( ClientesDAO.class );
		
		for(ClientesFb fb : sc)
		{
			cdao.addClientes(fb);
		}
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
