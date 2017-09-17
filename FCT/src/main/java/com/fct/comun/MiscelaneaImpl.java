package com.fct.comun;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;

import com.fct.dao.ClientesDAO;
import com.fct.dao.EmpresasDAO;
import com.fct.dao.PagosDAO;
import com.fct.order.TClientesComp;
import com.fct.order.TEmpresasComp;
import com.fct.order.TPagosComp;
import com.fct.persistencia.TClientes;
import com.fct.persistencia.TContratos;
import com.fct.persistencia.TEmpresas;
import com.fct.persistencia.TFacturas;
import com.fct.persistencia.TPagos;

public class MiscelaneaImpl 
implements Miscelanea
{
	@Autowired
	private ClientesDAO cdao;
	
	@Autowired
	private EmpresasDAO edao;
	
	@Autowired
	private PagosDAO pdao;
	
	public ModelMap crearModelo()
	{
		ModelMap mm					= new ModelMap();
		
		List<TEmpresas> empresas	= this.edao.getEmpresas();
		List<TClientes> clientes	= this.cdao.getClientes();
		List<TPagos> pagos			= this.pdao.getPagos();
		
		Collections.sort( clientes, new TClientesComp() );
		Collections.sort( empresas, new TEmpresasComp() );
		Collections.sort( pagos, new TPagosComp() );
		
		mm.put("empresas", empresas);
		mm.put("clientes", clientes);
		mm.put("pagos", pagos);
		mm.put("fecha", new Date());
		
		return mm;
	}
	
	public byte[] generarLogo(HttpServletRequest req, int oid) 
	throws IOException
	{
		ServletContext sc	= req.getSession().getServletContext();
		InputStream input	= sc.getResourceAsStream("/WEB-INF/classes/empresa_" + oid + ".jpg");
		
		return IOUtils.toByteArray(input);
	}
	
	public byte[] generarSello(HttpServletRequest req) 
	throws IOException
	{
		ServletContext sc	= req.getSession().getServletContext();
		InputStream input	= sc.getResourceAsStream("/WEB-INF/classes/sello.png");
		
		return IOUtils.toByteArray(input);
	}

	public String facturaNombre(TFacturas f) 
	{
		NumberFormat fmt	= new DecimalFormat("000");
		StringBuilder sb 	= new StringBuilder();
		String numero		= fmt.format( f.getNumero() );
		TEmpresas e			= f.getTEmpresas();
		String base			= null;
		
		if( e.isPresupuesto() )
		{
			base = "presupuesto_";
		}
		else if( f.getTEmpresas().isB() )
		{
			base = "documento_";
		}
		else if( f.getTEmpresas().isProforma() )
		{
			base = "proforma_";
		}
		else
		{
			base = "factura_";
		}
		
		sb.append(base);
		sb.append( f.getOid() );
		sb.append("_");
		sb.append(numero);
		sb.append(".pdf");
		
		return  sb.toString();
	}

	@Override
	public String contratoNombre(TContratos c) 
	{
		return "contrato_" + c.getOid() + "_" + c.getNumero() + ".pdf";
	}
}
