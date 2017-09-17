package com.fct.dao;

import java.text.ParseException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fct.fb.ContratosBuscarFb;
import com.fct.fb.ContratosFb;
import com.fct.persistencia.TContratos;
import com.fct.persistencia.TLineasContrato;

@Repository
public class ContratosDAOImpl 
implements ContratosDAO
{
	@Autowired
	private AgentesDAO adao;
	
	@Autowired
	private PruebasDAO pdao;
	
	@Autowired
	private ClientesDAO cdao;
	
	@Autowired
	private LineasContratoDAO ldao;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public TContratos addContrato(ContratosFb fb) 
	throws ParseException 
	{
		Session session	= this.sessionFactory.getCurrentSession();
		TContratos c 	= new TContratos();
		
		this.actualizaContrato(fb, c);
		
		session.save(c);
		
		return c;
	}

	@Transactional(readOnly=true)
	public TContratos getContrato(int oid) 
	{
		Session session = this.sessionFactory.getCurrentSession();
		return (TContratos) session.get( TContratos.class, oid );
	}
	
	@Transactional(readOnly=true)
	public TContratos getContratoNoLazy(int oid) 
	{
		Session session	= this.sessionFactory.getCurrentSession();
		TContratos k 	= (TContratos) session.get( TContratos.class, oid );
		
		Hibernate.initialize( k.getTClientes() );
		Hibernate.initialize( k.getTLineasContratos() );
		
		for( TLineasContrato l : k.getTLineasContratos() )
		{
			Hibernate.initialize( l.getTAgentes() );
			Hibernate.initialize( l.getTPruebas() );
		}
		
		return k;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<TContratos> getContratos(ContratosBuscarFb fb) 
	{
		Session session	= this.sessionFactory.getCurrentSession();
		Criteria dc		= session.createCriteria(TContratos.class);
		
		dc.add
		( 
			Restrictions.between( "fecha", fb.getFini(), fb.getFfin() ) 
		);
		
		if( fb.isCheckcliente() )
		{
			dc.add
			(
				Restrictions.eq( "TClientes.oid", fb.getOidcliente() )
			);
		}
		
		dc.addOrder( Order.desc("numero") );
		
		return dc.list();
	}

	@Transactional
	public void deleteContratos(int oid) 
	{
		Session session		= this.sessionFactory.getCurrentSession();
		TContratos borrar	= (TContratos) session.get(TContratos.class, oid);
		session.delete(borrar);
	}

	@Transactional
	public TContratos alterContratos(ContratosFb fb) 
	throws ParseException 
	{
		Session session	= this.sessionFactory.getCurrentSession();
		TContratos c	= (TContratos) session.get( TContratos.class, fb.getOid() );
		
		for( TLineasContrato lc : c.getTLineasContratos() )
		{
			this.ldao.deleteLineaContrato(lc);
		}
		
		c.getTLineasContratos().clear();
				
		this.actualizaContrato(fb, c);
		
		session.merge(c);
		
		return c;
	}
	
	private void actualizaContrato(ContratosFb fb, TContratos c) 
	throws ParseException
	{
		int oidcliente = fb.getOidclienteCert();
		
		c.setFecha( fb.getFechaCert() );
		c.setNumero( fb.getNumCert() );
		c.setTClientes( this.cdao.getCliente(oidcliente) );
		
		for(int i=0; i<fb.getPlacaext().length; i++)
		{
			TLineasContrato l	= new TLineasContrato();
			int agente			= fb.getAgentesext()[i];
			int prueba			= fb.getPruebasext()[i];
			
			l.setCapacidad( fb.getCapacidadext()[i] );
			l.setFecha(  fb.getFechaext()[i]  );
			l.setNumeroPlaca( fb.getPlacaext()[i] );
			l.setPrecio( fb.getPrecioext()[i] );
			l.setTAgentes( this.adao.getAgente(agente) );
			l.setTPruebas( this.pdao.getPrueba(prueba) );
			l.setTContratos(c);
			l.setCantidad( fb.getCantidadext()[i] );
			
			c.getTLineasContratos().add(l);
		}
	}

	@Transactional(readOnly=true)
	public int getProximoNumero() 
	{
		Session session		= this.sessionFactory.getCurrentSession();
		Criteria dc			= session.createCriteria(TContratos.class);

		dc.setProjection( Projections.max("numero") );
		
		Integer max			= (Integer) dc.uniqueResult();
		max					= max == null ? 0 : max;
		
		return max+1;
	}
}