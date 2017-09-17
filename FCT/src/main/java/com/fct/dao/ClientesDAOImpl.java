package com.fct.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fct.fb.ClientesFb;
import com.fct.persistencia.TClientes;

@Repository
public class ClientesDAOImpl 
implements ClientesDAO
{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public TClientes addClientes(ClientesFb fb)
	{
		Session session	= this.sessionFactory.getCurrentSession();
		TClientes tc 	= new TClientes();
		
		this.actualizarCliente(fb, tc);		
		session.save(tc);
		
		return tc;
	}
	
	@Transactional
	public void alterClientes(ClientesFb fb)
	{
		Session session	= this.sessionFactory.getCurrentSession();
		TClientes tc 	= (TClientes) session.get( TClientes.class, fb.getOid() );
		
		this.actualizarCliente(fb, tc);
		session.update(tc);
	}
	
	@Transactional(readOnly=true)
	public TClientes getCliente(Integer oid)
	{
		Session session	= this.sessionFactory.getCurrentSession();
		return (TClientes) session.get(TClientes.class, oid);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<TClientes> getClientes()
	{
		Session session	= this.sessionFactory.getCurrentSession();
		Query query		= session.createQuery("FROM TClientes ORDER BY nombre ASC");
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<TClientes> getClientes(String like)
	{
		Session session		= this.sessionFactory.getCurrentSession();
		Criteria dc 		= session.createCriteria(TClientes.class);
		Disjunction or		= Restrictions.disjunction();
		
		like = "%" + like + "%";
		
		or.add( Restrictions.ilike("dni", like) );
		or.add( Restrictions.ilike("nombre", like) );
		or.add( Restrictions.ilike("apellidos", like) );
		or.add( Restrictions.ilike("direccion", like) );
		or.add( Restrictions.ilike("localidad", like) );
		or.add( Restrictions.ilike("provincia", like) );
		or.add( Restrictions.ilike("observaciones", like) );
		
		dc.add(or);
		
		return dc.list();
	}
	
	@Transactional
	public void deleteClientes(Integer oid)
	{
		Session session	= this.sessionFactory.getCurrentSession();
		Object borrar	= session.get(TClientes.class, oid);
		session.delete(borrar);
	}
	
	private void actualizarCliente(ClientesFb fb, TClientes tc)
	{
		tc.setApellidos( fb.getApellidos() );
		tc.setCp( fb.getCp() );
		tc.setDireccion( fb.getDireccion() );
		tc.setDni( fb.getDni() );
		tc.setLocalidad( fb.getLocalidad() );
		tc.setNombre( fb.getNombre() );
		tc.setObservaciones( fb.getObservaciones() );
		tc.setProvincia( fb.getProvincia() );
		tc.setTelefono1( fb.getTelefono1() );
		tc.setTelefono2( fb.getTelefono2() );
		tc.setEmail( fb.getEmail() );
	}
}
