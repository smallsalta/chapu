package com.fct.controller;

import java.text.ParseException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fct.dao.ClientesDAO;
import com.fct.dao.EmpresasDAO;
import com.fct.dao.InformesDAO;
import com.fct.fb.FacturasBuscarFb;
import com.fct.order.TClientesComp;
import com.fct.order.TEmpresasComp;
import com.fct.persistencia.TClientes;
import com.fct.persistencia.TEmpresas;
import com.fct.persistencia.TInforme;

@Controller
public class Informes 
{
	@Autowired
	private ClientesDAO cdao;
	
	@Autowired
	private EmpresasDAO edao;
	
	@Autowired
	private InformesDAO idao;
	
	@InitBinder
    public void initBinder(WebDataBinder binder) 
	{
		Binder.doBinder(binder);
    }
	
	@RequestMapping("informes_facturacion")
	public ModelAndView cargarInformesFacturacion() 
	{
		LoggerFactory.getLogger( Informes.class ).info( "" + Math.random() );
		
		ModelMap mm = new ModelMap();
		this.preparaPantallaInforme(mm);
		
		return new ModelAndView("informes_facturacion", mm);
	}
	
	@RequestMapping("informes_facturacion_resultados")
	public ModelAndView cargarResultadosFacturacion(FacturasBuscarFb fb) 
	throws ParseException
	{
		LoggerFactory.getLogger( Informes.class ).info( "" + Math.random() );
		
		ModelMap mm 			= new ModelMap();
		List<TInforme> facturas	= this.idao.getInformeFacturacion(fb);
		TInforme total			= null;
		double base				= 0D;
		double iva				= 0D;
		
		for(TInforme f : facturas)
		{
			iva		+= f.getIva().doubleValue();
			base	+= f.getBase().doubleValue();
		}	
		
		total = new TInforme(base, iva);
		
		mm.put("facturas", facturas);
		mm.put("total", total);
		
		return new ModelAndView("informes_facturacion_resultados", mm);
	}
	
	@RequestMapping("informes_estado")
	public ModelAndView cargarInformesEstado() 
	{
		LoggerFactory.getLogger( Informes.class ).info( "" + Math.random() );
		
		ModelMap mm = new ModelMap();
		this.preparaPantallaInforme(mm);
		
		return new ModelAndView("informes_estado", mm);
	}
	
	@RequestMapping("informes_estado_resultados")
	public ModelAndView cargarResultadosEstados(FacturasBuscarFb fb) 
	throws ParseException
	{
		LoggerFactory.getLogger( Informes.class ).info( "" + Math.random() );
		
		ModelMap mm = new ModelMap();
		
		mm.put("estado", this.idao.getInformeEstado(fb));
		
		return new ModelAndView("informes_estados_resultados", mm);
	}
	
	@RequestMapping("informes_recordatorio")
	public ModelAndView cargarInformesRecordatorio() 
	{
		LoggerFactory.getLogger( Informes.class ).info( "" + Math.random() );
		
		ModelMap mm = new ModelMap();
		this.preparaPantallaInforme(mm);
		
		return new ModelAndView("informes_recordatorio", mm);
	}
	
	@RequestMapping("informes_recordatorio_resultados")
	public ModelAndView cargarResultadosRecordatorio(FacturasBuscarFb fb) 
	throws ParseException
	{
		LoggerFactory.getLogger( Informes.class ).info( "" + Math.random() );
		
		ModelMap mm = new ModelMap();
		
		mm.put("resumen", this.idao.getInformeRecordatorio(fb));
		
		return new ModelAndView("informes_recordatorio_resultados", mm);
	}
	
	private void preparaPantallaInforme(ModelMap mm)
	{
		List<TEmpresas> empresas	= this.edao.getEmpresas();
		List<TClientes> clientes	= this.cdao.getClientes();
		
		Collections.sort(clientes, new TClientesComp());
		Collections.sort(empresas, new TEmpresasComp());
		
		mm.put("empresas", empresas);
		mm.put("clientes", clientes);
		mm.put("fecha", new Date());
	}
}
