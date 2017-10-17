package com.fct.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fct.comun.Miscelanea;
import com.fct.dao.FacturasDAO;
import com.fct.fb.FacturasBuscarFb;
import com.fct.fb.FacturasFb;
import com.fct.mail.MandarCorreo;
import com.fct.pdf.FacturaPDF;
import com.fct.persistencia.TClientes;
import com.fct.persistencia.TFacturas;
import com.fct.zip.GenerarZIP;
import com.itextpdf.text.DocumentException;

@Controller
public class Facturas 
{
	@Autowired
	private FacturasDAO fdao;
	
	@Autowired
	private FacturaPDF pdf;
	
	@Autowired
	private GenerarZIP zip;
	
	@Autowired
	private Miscelanea misc;
	
	@Autowired
	private MandarCorreo correo;
	
	@InitBinder
    public void initBinder(WebDataBinder binder) 
	{
		Binder.doBinder(binder);
    }
	
	@RequestMapping("facturas_alta")
	public ModelAndView cargarPantallaAlta(Integer oid)
	{
		LoggerFactory.getLogger( Facturas.class ).info( "" + Math.random() );
		
		ModelMap mm	= this.misc.crearModelo();
		
		mm.put("factura", new TFacturas());
		mm.put("fecha", new Date());
		mm.put("oidcliente", oid);
		
		return new ModelAndView("facturas_alta", mm);
	}
	
	@RequestMapping("facturas_guardar")
	public ModelAndView facturasGuardar(FacturasFb fb, HttpServletRequest req) 
	throws ParseException
	{
		LoggerFactory.getLogger( Facturas.class ).info( "" + Math.random() );
		
		TFacturas f	= this.fdao.addFactura(fb);
		
		req.setAttribute( "oid", f.getOid() );
		
		return new ModelAndView("forward:facturas_cargar.do");
	}
	
	@RequestMapping("facturas_modificar")
	public ModelAndView facturasModificar(FacturasFb fb, HttpServletRequest req) 
	throws ParseException
	{
		LoggerFactory.getLogger( Facturas.class ).info( "" + Math.random() );
		
		TFacturas f = this.fdao.alterFacturas(fb);
		
		req.setAttribute( "oid", f.getOid() );	
		
		return new ModelAndView("forward:facturas_cargar.do");
	}
	
	@RequestMapping("facturas_cargar")
	public ModelAndView cargarPantallaCargar(Integer oid, HttpServletRequest req) 
	throws ParseException
	{
		LoggerFactory.getLogger( Facturas.class ).info( "" + Math.random() );
		
		oid 				= (oid == null) ? (Integer) req.getAttribute("oid") : oid;
		TFacturas factura	= this.fdao.getFactura(oid);
		ModelMap mm			= this.misc.crearModelo();
		
		mm.put("factura", factura);
		mm.put("fecha", factura.getFecha());
		
		return new ModelAndView("facturas_cargar", mm);
	}
	
	@RequestMapping("facturas_ver")
	public ModelAndView cargarPantallaVer(Integer oid) 
	{
		LoggerFactory.getLogger( Facturas.class ).info( "" + Math.random() );
		
		TFacturas factura	= this.fdao.getFactura(oid);
		ModelMap mm			= this.misc.crearModelo();
		
		mm.put("factura", factura);
		
		return new ModelAndView("facturas_ver", mm);
	}
	
	@RequestMapping("facturas_buscar")
	public ModelAndView cargarPantallaBuscar()
	{
		LoggerFactory.getLogger( Facturas.class ).info( "" + Math.random() );
		
		ModelMap mm	= this.misc.crearModelo();

		return new ModelAndView("facturas_buscar", mm);
	}
	
	@RequestMapping("facturas_buscar_resultados")
	public ModelAndView cargarPantallaBuscarResultados(FacturasBuscarFb fb, HttpServletRequest req) 
	throws ParseException
	{
		LoggerFactory.getLogger( Facturas.class ).info( "" + Math.random() );
		
		ModelMap mm 				= new ModelMap();
		List<TFacturas> facturas	= this.fdao.getFacturas(fb);
		ModelAndView mav 			= null;
		
		if( facturas.size() == 1 )
		{
			req.setAttribute( "oid", facturas.get(0).getOid() );
			mav = new ModelAndView("forward:facturas_cargar.do");
		}
		else
		{
			mm.put("facturas", facturas);
			mav = new ModelAndView("facturas_buscar_resultados", mm);
		}
		
		return mav;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("facturas_numero")
	public void obtenerNumeroFactura(Integer oid, HttpServletResponse resp) 
	throws IOException
	{
		int seq			= this.fdao.getFacturaNumero(oid);
		JSONObject json = new JSONObject();
		
		json.put("numero", seq);
		
		resp.getWriter().print( json.toJSONString() );
	}
		
	@RequestMapping("facturas_borrar")
	public ModelAndView cargarPantallaBorrar(Integer oid) 
	{
		LoggerFactory.getLogger( Facturas.class ).info( "" + Math.random() );
		
		ModelMap mm = new ModelMap();
		
		mm.put("oid", oid);
		
		return new ModelAndView("facturas_borrar", mm);
	}
	
	@RequestMapping("facturas_copiar")
	public ModelAndView cargarPantallaCopiar(FacturasFb fb, HttpServletRequest req) 
	throws ParseException 
	{
		LoggerFactory.getLogger( Facturas.class ).info( "" + Math.random() );
		
		int next	= this.fdao.getFacturaNumero( fb.getOidempresa() );
		
		fb.setNumero(next);
		
		TFacturas f	= this.fdao.addFactura(fb);
		
		int oid		= f.getOid();
		
		return this.cargarPantallaCargar(oid, req);
	}
	
	@RequestMapping("facturas_reborrar")
	public ModelAndView cargarPantallaReborrar(Integer oid) 
	{
		LoggerFactory.getLogger( Facturas.class ).info( "" + Math.random() );
		
		this.fdao.deleteFacturas(oid);
		
		return new ModelAndView("forward:facturas_buscar.do");
	}
	
	@RequestMapping("facturas_pdf")
	public void generarPDF(Integer oid, HttpServletRequest req, HttpServletResponse resp) 
	throws IOException, DocumentException 
	{
		TFacturas f		= this.fdao.getFactura(oid);
		byte[] logo		= this.misc.generarLogo( req, f.getTEmpresas().getOid() );
		String nombre	= this.misc.facturaNombre(f);

		resp.setContentType("application/pdf");
		resp.setCharacterEncoding("UTF-8");
		resp.setHeader("Content-Disposition", "attachment;filename=" + nombre);
		resp.getOutputStream().write( this.pdf.createPDF(f, logo).toByteArray() );
		resp.flushBuffer();
	}
	
	@RequestMapping("facturas_mail")
	public ModelAndView generarEmail(Integer oid, HttpServletRequest req, HttpServletResponse resp) 
	{
		boolean exito = true;
		
		try
		{
			TFacturas f		= this.fdao.getFactura(oid);
			TClientes tf	= f.getTClientes();
			byte[] logo		= this.misc.generarLogo( req, f.getTEmpresas().getOid() );
			byte[] fact		= this.pdf.createPDF( f, logo ).toByteArray();
			
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
	
	@RequestMapping("facturas_descargar")
	public ModelAndView cargarPantallaDescargar() 
	{
		LoggerFactory.getLogger( Facturas.class ).info( "" + Math.random() );
		
		ModelMap mm = this.misc.crearModelo();
		
		return new ModelAndView("facturas_descargar", mm);
	}
	
	@RequestMapping("facturas_descargar_resultados")
	public ModelAndView cargarPantallaDescargarResultados(FacturasBuscarFb fb, HttpServletRequest req, HttpServletResponse resp) 
	throws ParseException 
	{
		LoggerFactory.getLogger( Facturas.class ).info( "" + Math.random() );
		
		List<TFacturas> facturas 	= this.fdao.getFacturas(fb);
		ModelMap mm 				= this.misc.crearModelo();
		
		mm.put("facturas", facturas);
		
		return new ModelAndView("facturas_descargar_resultados", mm);
	}
	
	@RequestMapping("facturas_zip")
	public void generarZIP(FacturasBuscarFb fb, HttpServletRequest req, HttpServletResponse resp) 
	throws DocumentException, IOException, ParseException 
	{
		LoggerFactory.getLogger( Facturas.class ).info( "" + Math.random() );
		
		List<TFacturas> facturas = new LinkedList<TFacturas>();
		
		for( String oid : fb.getFctoid() )
		{
			TFacturas f = this.fdao.getFactura( Integer.valueOf(oid) );
			facturas.add(f);
		}
		
		TFacturas f	= facturas.get(0);
		byte[] logo	= this.misc.generarLogo( req, f.getTEmpresas().getOid() );
		
		this.zip.setFacturas(facturas);
		this.zip.setLogo(logo);
		
		resp.setContentType("application/zip");
		resp.setHeader("Content-Disposition", "attachment;filename=facturas.zip");
		resp.getOutputStream().write( this.zip.createFacturaZIP().toByteArray() );
		resp.flushBuffer();
	}
}
