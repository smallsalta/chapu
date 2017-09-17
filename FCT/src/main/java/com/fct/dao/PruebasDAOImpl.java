package com.fct.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fct.persistencia.TPruebas;

@Repository
public class PruebasDAOImpl
implements PruebasDAO
{
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<TPruebas> getPruebas() 
	{
		Session session	= this.sessionFactory.getCurrentSession();
		Query query		= session.createQuery("FROM TPruebas ORDER BY descr ASC");
		return query.list();
	}

	@Transactional(readOnly=true)
	public TPruebas getPrueba(int oid) 
	{
		Session session = this.sessionFactory.getCurrentSession();
		return (TPruebas) session.get(TPruebas.class, oid);
	}
}
