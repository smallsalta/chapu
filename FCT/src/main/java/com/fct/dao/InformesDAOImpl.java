package com.fct.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fct.fb.FacturasBuscarFb;
import com.fct.persistencia.TInforme;
import com.fct.persistencia.TPagos;

@Repository
public class InformesDAOImpl 
implements InformesDAO
{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private PagosDAO pdao;
		
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<TInforme> getInformeFacturacion(FacturasBuscarFb fb)
	{
		StringBuffer sb = new StringBuffer();

		sb.append(" select oid, numero, base, iva, base+iva as total");
		sb.append(" from");
		sb.append(" (");
		sb.append(" select f.oid, f.numero, sum(l.precio) as base, sum(l.precio*f.iva/100) as iva");
		sb.append(" from t_facturas f, t_lineas_factura l");
		sb.append(" WHERE f.oid = l.oidfactura");
		sb.append(" AND f.oid");
		sb.append(" IN (");
		sb.append( this.generaSubConsulta(fb) );
		sb.append(" )");
		sb.append(" group by f.numero, f.oid");
		sb.append(" ) s");
		sb.append(" ORDER BY numero");
		
		Session session 		= this.sessionFactory.getCurrentSession();
		ResultTransformer rst	= Transformers.aliasToBean( TInforme.class );
		SQLQuery sql			= session.createSQLQuery( sb.toString() );
		
		return sql.setResultTransformer(rst).list();
	}

	@SuppressWarnings("rawtypes")
	@Transactional(readOnly=true, propagation=Propagation.REQUIRED)
	public Map<String, Map> getInformeEstado(FacturasBuscarFb fb) 
	{
		Map<String, TInforme> total 		= new TreeMap<String, TInforme>();
		Map<String, List<TInforme>> fact 	= new HashMap<String, List<TInforme>>();
		Map<String, Map> estado				= new HashMap<String, Map>();
		
		for( TPagos p : this.pdao.getPagos() )
		{
			fb.setOidpago( p.getOid() );
			total.put( p.getDescr() , this.getInformeEstadoPago(fb) );
			fact.put( p.getDescr() , this.getFacturasPago(fb) );
		}
		
		estado.put("total", total);
		estado.put("fact", fact);
		
		return estado;
	}

	@Transactional(readOnly=true)
	private TInforme getInformeEstadoPago(FacturasBuscarFb fb)
	{
		StringBuffer sb = new StringBuffer();
		
		sb.append(" SELECT oid, numero, base, iva, total");
		sb.append(" FROM (");
		sb.append(" SELECT 0 AS oid, 0 AS numero, SUM(precio) AS base, SUM(precio*(iva/100)) AS iva, SUM(precio*(1+iva/100)) AS total");
		sb.append(" FROM t_facturas f, t_lineas_factura l");
		sb.append(" WHERE f.fecha");
		sb.append(" BETWEEN TO_DATE(  '" + fb.getFini() +  "',  'DD/MM/YYYY' )"); 
		sb.append(" AND TO_DATE(  '" + fb.getFfin() + "',  'DD/MM/YYYY' )");
		sb.append(" AND oidempresa =" + fb.getOidempresa());
		sb.append(" AND oidpago =" + fb.getOidpago() );
		sb.append(" AND l.oidfactura = f.oid");
		sb.append(" )q");
		sb.append(" ORDER BY numero");
		
		Session session 		= this.sessionFactory.getCurrentSession();
		ResultTransformer rst	= Transformers.aliasToBean( TInforme.class );
		SQLQuery sql			= session.createSQLQuery( sb.toString() );
		TInforme informe		= (TInforme) sql.setResultTransformer(rst).uniqueResult();
		
		return informe;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	private List<TInforme> getFacturasPago(FacturasBuscarFb fb) 
	{
		StringBuffer sb = new StringBuffer();
		
		sb.append(" SELECT oid, numero, base, iva, total");
		sb.append(" FROM (");
		sb.append(" SELECT oid, numero, 0 AS base, 0 AS iva, 0 AS total");
		sb.append(" FROM t_facturas f");
		sb.append(" WHERE oid IN (");
		sb.append( this.generaSubConsulta(fb) );
		sb.append(" )");
		sb.append(" )s");
		sb.append(" ORDER BY numero");
		
		Session session 		= this.sessionFactory.getCurrentSession();
		ResultTransformer rst	= Transformers.aliasToBean( TInforme.class );
		SQLQuery sql			= session.createSQLQuery( sb.toString() );
		List<TInforme> datos	= sql.setResultTransformer(rst).list();
		
		return datos;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<TInforme> getInformeRecordatorio(FacturasBuscarFb fb) 
	{
		StringBuffer sb = new StringBuffer();
		
		sb.append(" SELECT");
		sb.append(" sum(lf.precio) * (1 + f.iva / 100) AS total,");
		sb.append(" CONCAT(c.nombre, ' ', c.apellidos) as cliente,");
		sb.append(" f.numero, f.oid");
		sb.append(" FROM");
		sb.append(" t_facturas f,");
		sb.append(" t_clientes c,");
		sb.append(" t_lineas_factura lf");
		sb.append(" WHERE");
		sb.append(" f.oidclientes = c.oid");
		sb.append(" AND lf.oidfactura = f.oid");
		sb.append(" AND f.oid IN (");
		sb.append( this.generaSubConsulta(fb) );
		sb.append(" )");
		sb.append(" GROUP BY f.oid, c.nombre, c.apellidos ");
		sb.append(" ORDER BY f.numero ASC");

		Session session 		= this.sessionFactory.getCurrentSession();
		ResultTransformer rst	= Transformers.aliasToBean( TInforme.class );
		SQLQuery sql			= session.createSQLQuery( sb.toString() );
		List<TInforme> datos	= sql.setResultTransformer(rst).list();
		
		return datos;
	}
	
	private StringBuffer generaSubConsulta(FacturasBuscarFb fb)
	{
		StringBuffer sb = new StringBuffer();
		
		sb.append(" SELECT oid");
		sb.append(" FROM t_facturas f");
		sb.append(" WHERE f.fecha");
		sb.append(" BETWEEN TO_DATE( '" + fb.getFini() + "',  'YYYY-MM-DD' )");
		sb.append(" AND TO_DATE( '" + fb.getFfin() + "',  'YYYY-MM-DD' ) ");
//		sb.append(" BETWEEN TO_DATE( '" + fb.getFini() + "',  'DD/MM/YYYY' )");
//		sb.append(" AND TO_DATE( '" + fb.getFfin() + "',  'DD/MM/YYYY' ) ");
		
		if( fb.isCheckcliente() )
		{	
			sb.append(" AND oidclientes =" + fb.getOidcliente());
		}
		
		if( fb.isCheckempresa() )
		{
			sb.append(" AND oidempresa =" + fb.getOidempresa());
		}
		
		// Este valor no viene en el formulario
		if( fb.getOidpago() != null )
		{
			sb.append(" AND oidpago =" + fb.getOidpago());
		}
		
		return sb;
	}
}