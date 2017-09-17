package com.fct.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fct.persistencia.TPagos;

@Repository
public class PagosDAOImpl 
implements PagosDAO
{
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<TPagos> getPagos()
	{
		Session session	= this.sessionFactory.getCurrentSession();
		Query query		= session.createQuery("FROM TPagos ORDER BY descr ASC");
		return query.list();
	}

	@Transactional(readOnly=true)
	public TPagos getPago(int oid) 
	{
		Session session	= this.sessionFactory.getCurrentSession();
		return (TPagos) session.get( TPagos.class, oid );
	}
}
