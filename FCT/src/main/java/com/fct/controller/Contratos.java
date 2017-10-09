package com.fct.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.fct.comun.Miscelanea;
import com.fct.dao.AgentesDAO;
import com.fct.dao.ClientesDAO;
import com.fct.dao.ContratosDAO;
import com.fct.dao.PruebasDAO;
import com.fct.fb.ContratosBuscarFb;
import com.fct.fb.ContratosFb;
import com.fct.mail.MandarCorreo;
import com.fct.order.TClientesComp;
import com.fct.pdf.ContratoPDF;
import com.fct.persistencia.TClientes;
import com.fct.persistencia.TContratos;
import com.fct.zip.GenerarZIP;
import com.itextpdf.text.DocumentException;

@Controller
@SessionAttributes( {"agentes", "pruebas"} )
public class Contratos 
{
	@Autowired
	private Miscelanea misc;
	
	@Autowired
	private PruebasDAO pdao;
	
	@Autowired
	private AgentesDAO adao;
	
	@Autowired
	private ContratosDAO cdao;
	
	@Autowired
	private ClientesDAO kdao;
	
	@Autowired
	private ContratoPDF pdf;
	
	@Autowired
	private MandarCorreo correo;
	
	@Autowired
	private GenerarZIP zip;
	
	@InitBinder
    public void initBinder(WebDataBinder binder) 
	{
		Binder.doBinder(binder);
    }
	
	@RequestMapping("contratos_alta")
	public ModelAndView cargarPantallaAlta(Integer oid)
	{
		LoggerFactory.getLogger( Contratos.class ).info( "" + Math.random() );
		
		ModelMap mm	= this.misc.crearModelo();
		mm.put("numero", this.cdao.getProximoNumero());
		mm.put("fecha", new Date());
		mm.put("oidcliente", oid);
		
		return new ModelAndView("contratos_alta", mm);
	}
	
	@RequestMapping("contratos_buscar")
	public ModelAndView cargarPantallaBuscar()
	{
		LoggerFactory.getLogger( Contratos.class ).info( "" + Math.random() );
		
		ModelMap mm	= new ModelMap();
		
		List<TClientes> clientes = this.kdao.getClientes();
		Collections.sort(clientes, new TClientesComp());
		
		mm.put("fecha", new Date());
		mm.put("clientes", clientes);
		
		return new ModelAndView("contratos_buscar", mm);
	}
	
	@RequestMapping("contratos_buscar_resultados")
	public ModelAndView cargarPantallaBuscarResultados(ContratosBuscarFb fb)
	{
		LoggerFactory.getLogger( Contratos.class ).info( "" + Math.random() );
		
		ModelMap mm	= new ModelMap();
		
		mm.put( "contratos", this.cdao.getContratos(fb) );
		
		return new ModelAndView("contratos_buscar_resultados", mm);
	}
	
	@RequestMapping("contratos_fila")
	public ModelAndView cargarFila(	Model model )
	{
		if( !model.containsAttribute("pruebas") ) 
		{
		      model.addAttribute("pruebas", this.pdao.getPruebas() );
		}
		
		if( !model.containsAttribute("agentes") ) 
		{
		      model.addAttribute("agentes", this.adao.getAgentes() );
		}
		
		model.addAttribute("fecha", new Date());
		
		return new ModelAndView("contratos_fila");
	}
	
	@RequestMapping("contratos_guardar")
	public ModelAndView contratosGuardar(ContratosFb fb, HttpServletRequest req) 
	throws ParseException
	{
		LoggerFactory.getLogger( Contratos.class ).info( "" + Math.random() );

		TContratos c = this.cdao.addContrato(fb);
		
		req.setAttribute( "oid", c.getOid() );
		
		return new ModelAndView("forward:contratos_cargar.do");
	}
	
	@RequestMapping("contratos_cargar")
	public ModelAndView contratosCargar(Integer oid, HttpServletRequest req)
	{
		LoggerFactory.getLogger( Contratos.class ).info( "" + Math.random() );
		
		oid							= (oid == null) ? (Integer) req.getAttribute("oid") : oid;
		TContratos c				= this.cdao.getContrato(oid);
		List<TClientes> clientes 	= this.kdao.getClientes();
		ModelMap mm 				= new ModelMap();
		
		Collections.sort(clientes, new TClientesComp());
		
		mm.put("contrato", c);
		mm.put("numero", c.getNumero());
		mm.put("fecha", c.getFecha());
		mm.put("clientes", clientes);
		mm.put("oidcliente", c.getTClientes().getOid());
		
		return new ModelAndView("contratos_cargar", mm);
	}
	
	@RequestMapping("contratos_borrar")
	public ModelAndView cargarPantallaBorrar(Integer oid)
	{
		LoggerFactory.getLogger( Contratos.class ).info( "" + Math.random() );
		
		ModelMap mm = new ModelMap();
		
		mm.put("oid", oid);
		
		return new ModelAndView("contratos_borrar", mm);
	}
	
	@RequestMapping("contratos_reborrar")
	public ModelAndView contratosReborrar(Integer oid)
	{
		LoggerFactory.getLogger( Contratos.class ).info( "" + Math.random() );
		
		this.cdao.deleteContratos(oid);
		
		return new ModelAndView("forward:contratos_buscar.do");
	}
	
	@RequestMapping("contratos_modificar")
	public ModelAndView contratosModificar(ContratosFb fb, HttpServletRequest req) 
	throws ParseException
	{
		LoggerFactory.getLogger( Contratos.class ).info( "" + Math.random() );

		TContratos c = this.cdao.alterContratos(fb);
		
		req.setAttribute( "oid", c.getOid() );
		
		return new ModelAndView("forward:contratos_cargar.do");
	}
	
	@RequestMapping("contratos_pdf")
	public void generarPDF(Integer oid, HttpServletRequest req, HttpServletResponse resp) 
	throws DocumentException, IOException 
	{
		TContratos c	= this.cdao.getContrato(oid);
		byte[] sello	= this.misc.generarSello(req);
		byte[] empre	= this.misc.generarEmpresa(req);
		String nombre	= this.misc.contratoNombre(c);
		
		this.pdf.setContrato(c);
		this.pdf.setSello(sello);
		this.pdf.setEmpresa(empre);
		
		resp.setContentType("application/pdf");
		resp.setCharacterEncoding("UTF-8");
		resp.setHeader("Content-Disposition", "attachment;filename=" + nombre);
		resp.getOutputStream().write( this.pdf.createPDF().toByteArray() );
		resp.flushBuffer();
	}
	
	@RequestMapping("contratos_mail")
	public ModelAndView generarEmail(Integer oid, HttpServletRequest req, HttpServletResponse resp) 
	{
		boolean exito = true;
		
		try
		{
			TContratos c	= this.cdao.getContrato(oid);
			byte[] sello	= this.misc.generarSello(req);
			
			this.pdf.setContrato(c);
			this.pdf.setSello(sello);
			
			TClientes tf	= c.getTClientes();
			byte[] fact		= this.pdf.createPDF().toByteArray();
			
			this.correo.send( tf.getEmail(), fact );
		}
		catch(Exception e)
		{
			exito = false;
			LoggerFactory.getLogger( Facturas.class ).info( e.getMessage(), e );	
		}
		
		ModelMap mm = new ModelMap();
		mm.put("exito", exito);
		
		return new ModelAndView("facturas_correo", mm);
	}
	
	@RequestMapping("contratos_descargar")
	public ModelAndView cargarPantallaDescargar() 
	{
		LoggerFactory.getLogger( Contratos.class ).info( "" + Math.random() );
		
		ModelMap mm = this.misc.crearModelo();
		
		return new ModelAndView("contratos_descargar", mm);
	}
	
	@RequestMapping("contratos_descargar_resultados")
	public ModelAndView cargarPantallaDescargarResultados(ContratosBuscarFb fb, HttpServletRequest req, HttpServletResponse resp) 
	throws ParseException 
	{
		LoggerFactory.getLogger( Contratos.class ).info( "" + Math.random() );
		
		List<TContratos> contratos 	= this.cdao.getContratos(fb);
		ModelMap mm 				= this.misc.crearModelo();
		
		mm.put("contratos", contratos);
		
		return new ModelAndView("contratos_descargar_resultados", mm);
	}
	
	@RequestMapping("contratos_zip")
	public void generarZIP(ContratosBuscarFb fb, HttpServletRequest req, HttpServletResponse resp) 
	throws DocumentException, IOException, ParseException 
	{
		LoggerFactory.getLogger( Contratos.class ).info( "" + Math.random() );
		
		List<TContratos> contratos 	= new LinkedList<TContratos>();
		byte[] sello				= this.misc.generarSello(req);
		
		for( String oid : fb.getFctoid() )
		{
			TContratos f = this.cdao.getContrato( Integer.valueOf(oid) );
			contratos.add(f);
		}
		
		this.zip.setContratos(contratos);
		this.zip.setSello(sello);
		
		resp.setContentType("application/zip");
		resp.setHeader("Content-Disposition", "attachment;filename=contratos.zip");
		resp.getOutputStream().write( this.zip.createContratoZIP().toByteArray() );
		resp.flushBuffer();
	}
}