package co.com.core.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import co.com.core.dao.RoleDAO;
import co.com.core.domain.Role;

public class RoleDAOImpl implements RoleDAO {

	private SessionFactory sessionFactory;
	private Session session;
	private static final Logger logger = Logger.getLogger(RoleDAOImpl.class);

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Role> getAll() {
		List<Role> list = null;

		try {
			session = this.sessionFactory.openSession();
			Query query = session.getNamedQuery("Role.findAll");
			list = query.list();
		} catch (Exception e) {
			logger.error("Throwed Exception [RoleDAOImpl.getAll]:" + e.getMessage());
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public void createRole(Role role) {
		try {
			session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.save(role);
			tx.commit();
		} catch (Exception e) {
			logger.error("Throwed Exception [RoleDAOImpl.createRole]:" + e.getMessage());
			session.getTransaction().rollback();
		}finally {
			session.close();
		}
	}

	@Override
	public void delete(Role role) {
		try {
			session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.delete(role);
			tx.commit();
		} catch (Exception e) {
			logger.error("Throwed Exception [RoleDAOImpl.delete]:" + e.getMessage());
			session.getTransaction().rollback();
		}finally {
			session.close();
		}
	}

	@Override
	public void update(Role role) {
        try {
        	session = this.sessionFactory.openSession();
        	Transaction tx = session.beginTransaction();
        	session.update(role);
        	tx.commit();
		} catch (Exception e) {
			logger.error("Throwed Exception [RoleDAOImpl.update]:" + e.getMessage());
			session.getTransaction().rollback();
		}finally {
			session.close();
		}
	}

	@Override
	public List<Role> getNotAssignedRole(String ids) {
		
		List<Role> entity = null;
		try {
			session= this.sessionFactory.openSession();
			StringBuilder hql = new StringBuilder();
			
			if (ids != null && ids.isEmpty()) {
				hql.append("SELECT r FROM Role r WHERE r.roleId NOT IN(").append(ids).append(")");
				
			} else {
				hql.append("SELECT r FROM Role r");

			}
			
			Query query = session.createQuery(hql.toString());
			entity = query.list();
		} catch (Exception e) {
			logger.error("Throwed Exception [RoleDAOImpl.getNotAssignedRole]:" + e.getMessage());
		}finally {
			session.close();
		}
		return null;
	}

}
