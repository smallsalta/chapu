package com.fct.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fct.persistencia.TAgentes;

@Repository
public class AgentesDAOImpl
implements AgentesDAO
{
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<TAgentes> getAgentes() 
	{
		Session session	= this.sessionFactory.getCurrentSession();
		Query query		= session.createQuery("FROM TAgentes ORDER BY descr ASC");
		return query.list();
	}

	@Transactional(readOnly=true)
	public TAgentes getAgente(int oid) 
	{
		Session session	= this.sessionFactory.getCurrentSession();
		return (TAgentes) session.get(TAgentes.class, oid);
	}
}
