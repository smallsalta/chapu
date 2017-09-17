package com.fct.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fct.persistencia.TLineasContrato;

@Repository
public class LineasContratoDAOImpl 
implements LineasContratoDAO
{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public void deleteLineaContrato(Integer oid)
	{
		Session session		= this.sessionFactory.getCurrentSession();
		TLineasContrato lc 	= (TLineasContrato) session.get(TLineasContrato.class, oid);
		session.delete(lc);
	}
	
	@Transactional
	public void deleteLineaContrato(TLineasContrato lc)
	{
		Session session	= this.sessionFactory.getCurrentSession();
		session.delete(lc);
	}
}
