package com.fct.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fct.persistencia.TLineasFactura;

@Repository
public class LineasFacturaDAOImpl 
implements LineasFacturaDAO
{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public void deleteLineaFacturas(Integer oid)
	{
		Session session		= this.sessionFactory.getCurrentSession();
		TLineasFactura lf	= (TLineasFactura) session.get(TLineasFactura.class, oid);
		session.delete(lf);
	}
	
	@Transactional
	public void deleteLineaFacturas(TLineasFactura lf)
	{
		Session session		= this.sessionFactory.getCurrentSession();
		session.delete(lf);
	}
}
