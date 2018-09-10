package co.com.core.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import co.com.core.dao.RoleMenuDAO;
import co.com.core.domain.Role;
import co.com.core.domain.RoleMenu;

public class RoleMenuDAOImpl implements RoleMenuDAO{
	
	 private SessionFactory sessionFactory;
	 private Session session;
	 private static final Logger logger = Logger.getLogger(RoleMenuDAOImpl.class);
	 
	 

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<RoleMenu> getAll() {
		List<RoleMenu> entity = null;
		try {
			session = this.sessionFactory.openSession();
			Query query = session.getNamedQuery("RoleMenu.findAll");
			entity = query.list();
			
		} catch (Exception e) {
			logger.error("Throwed Exception [RoleMenuDAOImpl.getAll]"+e.getMessage());
		}finally {
			session.close();
		}
		return entity;
	}

	@Override
	public void createRoleMenu(RoleMenu entity) {
		try {
			session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.save(entity);
			tx.commit();
		} catch (Exception e) {
			logger.error("Throwed Exception [RoleMenuDAOImpl.createRoleMenu]"+e.getMessage());	
			session.getTransaction().rollback();
		}finally {
			session.close();
		} 
		
	}

	@Override
	public void delete(RoleMenu entity) {
           try {
			session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.delete(entity);
			tx.commit();
		} catch (Exception e) {
			logger.error("Throwed Exception [RoleMenuDAOImpl.delete]"+e.getMessage());	
			session.getTransaction().rollback();

		}finally {
			session.close();
		}		
	}

	@Override
	public void update(RoleMenu entity) {
           try {
			session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.update(entity);
			tx.commit();
		} catch (Exception e) {
			logger.error("Throwed Exception [RoleMenuDAOImpl.update]"+e.getMessage());	
			session.getTransaction().rollback();

		}finally {
			session.close();
		}		
	}

	@Override
	public List<RoleMenu> findMenuByRole(Role role) {
		List<RoleMenu> entity = null;
		try {
			session = this.sessionFactory.openSession();
	        Query query = session.getNamedQuery("RoleMenu.findByRoleId");
	        query.setParameter("roleId", role);
	        entity = query.list();
		} catch (Exception e) {
			logger.error("Throwed Exception [RoleMenuDAOImpl.findMenuByRole]"+e.getMessage());
		}finally {
			session.close();
		}
		return entity;
	}

}
