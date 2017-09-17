package com.fct.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fct.fb.FacturasBuscarFb;
import com.fct.fb.FacturasFb;
import com.fct.order.TLineasFacturaComp;
import com.fct.persistencia.TClientes;
import com.fct.persistencia.TEmpresas;
import com.fct.persistencia.TFacturas;
import com.fct.persistencia.TLineasFactura;
import com.fct.persistencia.TNumero;
import com.fct.persistencia.TPagos;

@Repository
public class FacturasDAOImpl 
implements FacturasDAO
{
	@Autowired
	private ClientesDAO cdao;
	
	@Autowired
	private EmpresasDAO edao;
	
	@Autowired
	private LineasFacturaDAO ldao;
	
	@Autowired
	private PagosDAO pdao;
	
	@Autowired
	private SessionFactory sessionFactory;
		
	@Transactional
	public TFacturas addFactura(FacturasFb fb) 
	throws ParseException
	{
		Session session	= this.sessionFactory.getCurrentSession();
		TFacturas f		= new TFacturas();
		
		this.actualizaFactura(fb, f);
		
		session.persist(f);
		
		return f;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<TFacturas> getFacturas(FacturasBuscarFb fb) 
	throws ParseException
	{
		Session session		= this.sessionFactory.getCurrentSession();
		Criteria dc			= session.createCriteria(TFacturas.class);
		
		DateFormat df		= new SimpleDateFormat("yyyy-MM-dd");
		Date fini			= df.parse( fb.getFini() );
		Date ffin			= df.parse( fb.getFfin() );
		
		if( fb.isCheckempresa() )
		{
			dc.createCriteria("TEmpresas", "TEmpresas").add
			( 
				Restrictions.eq("TEmpresas.oid", fb.getOidempresa()) 
			);
		}
		
		if( fb.isCheckcliente() )
		{
			dc.createCriteria("TClientes", "TClientes").add
			( 
				Restrictions.eq("TClientes.oid", fb.getOidcliente()) 
			);
		}
		
		if( fb.isCheckfactura() )
		{
			dc.add( Restrictions.eq("numero", fb.getNumfactura()) );
		}
			
		dc.add( Restrictions.between("fecha", fini, ffin) );
		dc.addOrder( Order.desc("numero") );

		return dc.list();
	}
	
	@Transactional(readOnly=true)
	public TFacturas getFactura(Integer oid) 
	{
		Session session = this.sessionFactory.getCurrentSession();
		TFacturas f		= (TFacturas) session.get(TFacturas.class, oid);
		
		Collections.sort( f.getTLineasFacturas(), new TLineasFacturaComp() ); 
		
		return f;
	}
	
	@Transactional(readOnly=true)
	public TFacturas getFacturaNoLazy(Integer oid) 
	{
		Session session = this.sessionFactory.getCurrentSession();
		TFacturas f 	= (TFacturas) session.get(TFacturas.class, oid);
		
//		Criteria dc1	= session.createCriteria( TFacturas.class );
		
//		dc1.createAlias( "TClientes", "cliente", Criteria.FULL_JOIN ).createAlias( "TEmpresas", "empresa", Criteria.FULL_JOIN ).createAlias( "TLineasFacturas", "lineas", Criteria.FULL_JOIN );
//		dc1.add( Restrictions.eq("oid", oid) );
		
//		dc1.uniqueResult();
		
		Hibernate.initialize( f.getTClientes() );
		Hibernate.initialize( f.getTEmpresas() );
		Hibernate.initialize( f.getTLineasFacturas() );
		
		Collections.sort( f.getTLineasFacturas(), new TLineasFacturaComp() );
		
		return f;
	}
	
	/**
	 * Recibimos el oid de la empreasa y buscamos para la empresa los 
	 * números máximos por año.
	 * 
	 * Para el año actual.
	 * Si tenemos máximo.
	 * Devolvemos el máximo más 1.
	 * 
	 * Si no hay máximo.
	 * Cogemos el del año anterior.
	 * Devolvemos el máximo más 1. 
	 */
	@Transactional(readOnly=true)
	@SuppressWarnings("unchecked")
	public int getFacturaNumero(Integer oid)
	{
		StringBuilder sb 			= new StringBuilder();
		
		sb.append(" select"); 
		sb.append(" max(numero) as numero, anyo");
		sb.append(" from");
		sb.append(" ( select"); 
		sb.append(" numero, cast( extract(year from fecha) as integer ) as anyo");
		sb.append(" from");
		sb.append(" t_facturas");
		sb.append(" where");
		sb.append(" oidempresa = :oid ) s1");
		sb.append(" group by anyo");
		
		Session session 			= this.sessionFactory.getCurrentSession();
		ResultTransformer rst		= Transformers.aliasToBean( TNumero.class );
		SQLQuery sql				= session.createSQLQuery( sb.toString() );
		
		sql.setParameter("oid", oid);
		
		List<TNumero> lmax			= sql.setResultTransformer(rst).list();
		Map<Integer, Integer> mmax 	= new HashMap<Integer, Integer>();
		
		for(TNumero n : lmax)
		{
			mmax.put( n.getAnyo() , n.getNumero() );
		}
		
		int anyo					= Calendar.getInstance().get( Calendar.YEAR );
		
		while( !mmax.containsKey(anyo) && anyo != 0 )
		{
			anyo--;
		}
		
		return mmax.get(anyo)+1;
	}

	@Transactional
	public void deleteFacturas(Integer oid) 
	{
		Session session		= this.sessionFactory.getCurrentSession();
		TFacturas borrar 	= (TFacturas) session.get(TFacturas.class, oid);
		session.delete(borrar);
	}

	@Transactional
	public TFacturas alterFacturas(FacturasFb fb) 
	throws ParseException 
	{
		Session session	= this.sessionFactory.getCurrentSession();
		TFacturas tf 	= (TFacturas) session.get( TFacturas.class, fb.getOid() );
		
		// Borramos las líneas viejas
		for( TLineasFactura lf : tf.getTLineasFacturas() )
		{
			this.ldao.deleteLineaFacturas(lf);
		}
		
		// Creamos las nuevas
		tf.getTLineasFacturas().clear();
				
		this.actualizaFactura(fb, tf);
		
		session.update(tf);
		
		return tf;
	}

	private void actualizaFactura(FacturasFb fb, TFacturas f) 
	throws ParseException
	{
		DateFormat df 	= new SimpleDateFormat("yyyy-MM-dd");
		TClientes c		= this.cdao.getCliente( fb.getOidcliente() );
		TEmpresas e		= this.edao.getEmpresa( fb.getOidempresa() );
		TPagos p		= this.pdao.getPago( fb.getOidpago() );
		
		f.setFecha( df.parse( fb.getFecha() ) );
		f.setNumero( fb.getNumero() );
		f.setIva( fb.getIva() );
		f.setTClientes(c);
		f.setTEmpresas(e);
		f.setTPagos(p);
		
		for(int i=0; i<fb.getCantidad().length; i++)
		{
			TLineasFactura lf 	= new TLineasFactura();
			
			Float tc			= fb.getCantidad()[i];
			Float tp			= fb.getPrecio()[i];
			
			// Fila blanca
			// {cantidad, descripción, precio} = {null, '', null}
			// Lo falseamos a {0, '', 0}
			
			lf.setCantidad( tc == null ? 0 : tc  );
			lf.setDescripcion( fb.getDescripcion()[i] );
			lf.setPrecio( tp == null ? 0 : tp );
			lf.setTFacturas(f);
			
			f.getTLineasFacturas().add(lf);
		}
	}
}