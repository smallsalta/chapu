package com.fct.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fct.persistencia.TEmpresas;

@Repository
public class EmpresasDAOImpl 
implements EmpresasDAO
{
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<TEmpresas> getEmpresas()
	{
		Session session	= this.sessionFactory.getCurrentSession();
		Query query		= session.createQuery("FROM TEmpresas ORDER BY descripcion ASC");
		return query.list();
	}
	
	@Transactional(readOnly=true)
	public TEmpresas getEmpresa(Integer oid)
	{
		Session session	= this.sessionFactory.getCurrentSession();
		return (TEmpresas) session.get(TEmpresas.class, oid);
	}
}
