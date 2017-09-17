package com.fct.controller;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fct.dao.ClientesDAO;
import com.fct.fb.ClientesBuscarFb;
import com.fct.fb.ClientesFb;
import com.fct.order.TClientesComp;
import com.fct.persistencia.TClientes;

@Controller
public class Clientes 
{
	@Autowired
	private ClientesDAO cdao;
	
	@Autowired
	private Boolean verBtnContrato;
	
	@RequestMapping("clientes_alta")
	public ModelAndView cargarPantallaAlta()
	{
		LoggerFactory.getLogger( Clientes.class ).info( "" + Math.random() );
		
		return new ModelAndView("clientes_alta");
	}
	
	@RequestMapping("clientes_guardar")
	public ModelAndView clientesGuardar(ClientesFb fb)
	{
		LoggerFactory.getLogger( Clientes.class ).info( "" + Math.random() );
		
		TClientes c	= this.cdao.addClientes(fb);
		ModelMap mm = new ModelMap();
		
		mm.put("fb", c);
		
		return new ModelAndView("clientes_cargar", mm);
	}
	
	@RequestMapping("clientes_modificar")
	public ModelAndView clientesModificar(ClientesFb fb)
	{
		LoggerFactory.getLogger( Clientes.class ).info( "" + Math.random() );
		
		this.cdao.alterClientes(fb);
		
		ModelMap mm = new ModelMap();
		
		mm.put("oid", fb.getOid());
		
		return new ModelAndView("forward:clientes_cargar.do", mm);
	}
	
	@RequestMapping("clientes_buscar")
	public ModelAndView cargarPantallaBuscar()
	{
		LoggerFactory.getLogger( Clientes.class ).info( "" + Math.random() );
		
		ModelMap mm 				= new ModelMap();
		List<TClientes> clientes 	= this.cdao.getClientes();
		
		Collections.sort(clientes, new TClientesComp());
		
		mm.put("clientes", clientes);
		
		return new ModelAndView("clientes_buscar", mm);
	}
	
	@RequestMapping("clientes_buscar_resultados")
	public ModelAndView cargarPantallaBuscarResultados(ClientesBuscarFb fb, HttpServletRequest req)
	{
		LoggerFactory.getLogger( Clientes.class ).info( "" + Math.random() );
		
		List<TClientes> clientes 	= null;
		ModelMap mm 				= new ModelMap();
		ModelAndView mav			= null;
		
		if( fb.getCheckcliente() )
		{
			req.setAttribute( "oid", fb.getOid() );
			mav = new ModelAndView("forward:clientes_cargar.do");
		}
		else
		{
			clientes = this.cdao.getClientes( fb.getBuscar() );
			
			if( clientes.size() == 1 )
			{
				req.setAttribute( "oid", clientes.get(0).getOid() );
				mav = new ModelAndView("forward:clientes_cargar.do");
			}
			else
			{
				mm.put("clientes", clientes);
				mav = new ModelAndView("clientes_buscar_resultados", mm);
			}
		}
		
		return mav;
	}
	
	@RequestMapping("clientes_cargar")
	public ModelAndView cargarPantallaCargar(Integer oid, HttpServletRequest req)
	{
		LoggerFactory.getLogger( Clientes.class ).info( "" + Math.random() );
		
		oid 			= req.getAttribute("oid") == null ? oid : (Integer) req.getAttribute("oid");
		TClientes fb 	= this.cdao.getCliente(oid);
		ModelMap mm		= new ModelMap();
		
		mm.put("fb", fb);
		mm.put("verBtnContrato", this.verBtnContrato);
		
		return new ModelAndView("clientes_cargar", mm);
	}
	
	@RequestMapping("clientes_borrar")
	public ModelAndView cargarPantallaBorrar(Integer oid)
	{
		LoggerFactory.getLogger( Clientes.class ).info( "" + Math.random() );
		
		ModelMap mm = new ModelMap();
		
		mm.put("oid", oid);
		
		return new ModelAndView("clientes_borrar", mm);
	}
	
	@RequestMapping("clientes_reborrar")
	public String cargarPantallaReborrar(Integer oid)
	{
		LoggerFactory.getLogger( Clientes.class ).info( "" + Math.random() );
		
		this.cdao.deleteClientes(oid);
		
		return "forward:clientes_buscar.do";
	}
}
