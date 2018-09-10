package co.com.core.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import co.com.core.dao.RoleUsuariosDAO;
import co.com.core.domain.RoleUsuarios;
import co.com.core.domain.Usuarios;

public class RoleUsuariosDAOImpl  implements RoleUsuariosDAO{
	
	  private SessionFactory sessionFactory;
	  private Session session;
	  private static final Logger logger = Logger.getLogger(RoleUsuariosDAOImpl.class);
	  
	  
	  

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<RoleUsuarios> getAll() {
		 List<RoleUsuarios> entiy = null;
		try {
			session = this.sessionFactory.openSession();
			Query query = session.getNamedQuery("RoleUsuarios.findAll");
			entiy = query.list();
		} catch (Exception e) {
			logger.error("Throwed Exception [RoleUsuariosImpl.getAll]: " +e.getMessage());
		}finally {
			session.close();
			}
		
		return entiy;
	}

	@Override
	public void createUserRole(RoleUsuarios userRole) {
		 try {
			session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.save(userRole);
			tx.commit();
		} catch (Exception e) {
			logger.error("Throwed Exception [RoleUsuariosImpl.createUserRole]: " +e.getMessage());	
			session.getTransaction().rollback();
		}finally {
			session.close();
		}
		
	}

	@Override
	public void delete(RoleUsuarios userRole) {
		try {
			session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.delete(userRole);
			tx.commit();
		} catch (Exception e) {
			logger.error("Throwed Exception [RoleUsuariosImpl.delete]: " +e.getMessage());	
			session.getTransaction().rollback();
		}finally {
			session.close();
		}
		
	}

	@Override
	public void update(RoleUsuarios userRole) {
		try {
			session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.update(userRole);
			tx.commit();
		} catch (Exception e) {
			logger.error("Throwed Exception [RoleUsuariosImpl.delete]: " +e.getMessage());	
			session.getTransaction().rollback();
		}finally {
			session.close();
		}		
	}

	@Override
	public List<RoleUsuarios> findByUser(Usuarios user) {
		List<RoleUsuarios> entity = null;
		try {
			session = this.sessionFactory.openSession();
			StringBuilder hql = new StringBuilder(); 
			hql.append("SELECT r FROM RoleUsuarios r WHERE r.idUsuario = :idUsuario");
			Query query = session.createQuery(hql.toString());
			entity = query.list();
		} catch (Exception e) {
			logger.error("Throwed Exception [RoleUsuariosImpl.findByUser]: " +e.getMessage());	
		}finally {
			session.close();
		}
		return entity;
	}

}
