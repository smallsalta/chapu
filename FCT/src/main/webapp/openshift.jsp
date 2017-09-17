<%@page import="com.fct.dao.FacturasDAO"%>
<%@page import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="java.util.Date"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>JBOSS Application Server 7 &amp; JBOSS EAP</title>
</head>
<body>
	<%
		out.println("<br/>-------- PostgreSQL JDBC Connection Testing ------------");
		out.println("<br/>" + new Date());
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			out.println("<br/>Where is your PostgreSQL JDBC Driver? "
					+ "Include in your library path!");
			e.printStackTrace();
			return;
		}

		out.println("<br/>PostgreSQL JDBC Driver Registered!");

		Connection connection = null;

		try {
			String host = System.getenv("OPENSHIFT_POSTGRESQL_DB_HOST");
			String port = System.getenv("OPENSHIFT_POSTGRESQL_DB_PORT");
			String user = System.getenv("OPENSHIFT_POSTGRESQL_DB_USERNAME");
			String pass = System.getenv("OPENSHIFT_POSTGRESQL_DB_PASSWORD");

			out.println("<br/>"+host);
			out.println("<br/>"+port);
			out.println("<br/>"+user);
			out.println("<br/>"+pass);

			connection = DriverManager.getConnection("jdbc:postgresql://"
					+ host + ":" + port + "/smallsalta", user, pass);

		} catch (SQLException e) {
			out.println("<br/>Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}

		if (connection != null) {
			out.println("<br/>You made it, take control your database now!");
		} else {
			out.println("<br/>Failed to make connection!");
		}
	%>

	<%
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:beans*.xml");
		FacturasDAO dao = context.getBean(FacturasDAO.class);
		out.println("<br/>"+dao.getFacturaNumero(3));
	%>
</body>
</html>