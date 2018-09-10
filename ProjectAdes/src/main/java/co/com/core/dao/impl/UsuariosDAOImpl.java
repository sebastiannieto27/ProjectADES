package co.com.core.dao.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.hql.internal.ast.HqlASTFactory;

import co.com.core.dao.UsuariosDAO;
import co.com.core.domain.Usuarios;

public class UsuariosDAOImpl implements UsuariosDAO {

	
	private SessionFactory sessionFactory;
    private Session session;
    private static final Logger logger = Logger.getLogger(UsuariosDAOImpl.class);
    
   

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Usuarios getUserById(Integer userID) {
		Usuarios usuarios = null;
		try {
			session = this.sessionFactory.openSession();
			usuarios = (Usuarios)session.get(Usuarios.class, userID);
		} catch (Exception e) {
			logger.error("Throwed Exception [UsuariosDAOImpl.getUserById]:"+e.getMessage());
		}finally {
			session.close();
		}
		return usuarios;
	}

	@Override
	public List<Usuarios> getAll() {
		List<Usuarios> list = null;
		try {
			session = this.sessionFactory.openSession();
			Query query = session.getNamedQuery("Usuarios.findAll");
			list = query.list();
		} catch (Exception e) {
			logger.error("Throwed Exception [UsuariosDAOImpl.getAll]:"+e.getMessage());
		}finally {
			session.close();
		}
		return list;
	}

	@Override
	public void createUser(Usuarios user) {
		
		try {
			session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.save(user);
			tx.commit();
			
		} catch (Exception e) {
			logger.error("Throwed Exception [UsuariosDAOImpl.createUser]:"+e.getMessage());
			session.getTransaction().rollback();
		}finally {
			session.close();
		}
		
	}

	@Override
	public void delete(Usuarios user) {
		try {
			session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.delete(user);
			tx.commit();
		} catch (Exception e) {
			logger.error("Throwed Exception [UsuariosDAOImpl.delete]:"+e.getMessage());
			session.getTransaction().rollback();
		}finally {
			session.close();
		}		
	}

	@Override
	public void update(Usuarios user) {
		// TODO Auto-generated method stub
		try {
			session= this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.update(user);
			tx.commit();
		} catch (Exception e) {
			logger.error("Throwed Exception [UsuariosDAOImpl.update]:"+e.getMessage());
			session.getTransaction().rollback();
		}finally {
			session.close();
		}
	}

	@Override
	public Usuarios getByMail(String userEmail) {
		Usuarios usuarios = null;
		try {
			session = this.sessionFactory.openSession();
			StringBuilder hql = new StringBuilder();
			hql.append("SELECT u FROM Usuarios u WHERE u.correo = '").append(userEmail).append("'");
			Query query = session.createQuery(hql.toString());
			List usuario = query.list();
			
			for (Iterator iterator = usuario.iterator(); iterator.hasNext();) {
				 usuarios = (Usuarios) iterator.next();
			}
			
		} catch (Exception e) {
			logger.error("Throwed Exception [UsuariosDAOImpl.getByMail]:"+e.getMessage());
		}finally {
			session.close();
		}
		
		return null;
	}

	@Override
	public List<Usuarios> getUserByName(String name) {
		List<Usuarios> entityList = null;
		
		try {
			session= this.sessionFactory.openSession();
			StringBuilder hql = new StringBuilder();
	        hql.append("SELECT u FROM Usuarios u WHERE u.nombreCompleto ='").append(name).append("'");
	        Query query = session.createQuery(hql.toString());
	        query.setParameter("nombreCompleto", "%"+name+"%");
	        entityList = query.list();
	        
		} catch (Exception e) {
			logger.error("Throwed Exception [UsuariosDAOImpl.getUserByName]:"+e.getMessage());	
		}finally {
			session.close();
		}
		return entityList;
	}

	@Override
	public Usuarios login(String userEmail, String userPassword) {
		Usuarios usuarios = null;
		
		try {
			
			session = this.sessionFactory.openSession();
			if (session != null) {
				StringBuilder hql = new StringBuilder();
				hql.append("SELECT u FROM Usuarios u WHERE u.correo ='").append(userEmail).append("'");
				hql.append("and u.password ='").append(userPassword).append("'");
				logger.info("hql  :"+hql);
				Query query = session.createQuery(hql.toString());
				List  usuario = query.list();
				if (usuario != null && usuario.size()>0) {
					for(Iterator iterator = usuario.iterator(); iterator.hasNext();) {	
					usuarios = (Usuarios)iterator.next();
				}
				}else {
					logger.error("List empity [UsuariosDAOImpl.login]:");
				}
				
		
			}else {
				logger.error("Error Connetion [UsuariosDAOImpl.login]:");
			}
			
		} catch (Exception e) {
			
			logger.error("Throwed Exception [UsuariosDAOImpl.login]:"+e.getMessage());
			
		}finally {
			session.close();
		}
		return usuarios;
	}

	

}
