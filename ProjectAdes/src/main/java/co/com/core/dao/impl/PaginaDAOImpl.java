package co.com.core.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import co.com.core.dao.PaginaDAO;
import co.com.core.domain.Pagina;
import oracle.net.aso.q;

public class PaginaDAOImpl implements PaginaDAO {
	
	private SessionFactory sessionFactory;
	private  Session session;
	private static final Logger logger = Logger.getLogger(PaginaDAOImpl.class);

	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Pagina> getAll() {
		List<Pagina> entity = null;
		try {
			session = this.sessionFactory.openSession();
			Query query = session.getNamedQuery("Pagina.findAll");
			entity = query.list();
		} catch (Exception e) {
			logger.error("Throwed Exception [PaginaDAOImpl.getAll]:"+e.getMessage());
		}finally {
			session.close();
		}
		return entity;
	}

	@Override
	public void create(Pagina pagina) {
		try {
			session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.save(pagina);
			tx.commit();
		} catch (Exception e) {
			logger.error("Throwed Exception [PaginaDAOImpl.create]:"+e.getMessage());
		}finally {
			session.close();
		}
		
	}

	@Override
	public void delete(Pagina pagina) {
           try {
			session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.delete(pagina);
			tx.commit();
		} catch (Exception e) {
		   logger.error("Throwed Exception [PaginaDAOImpl.delete]:"+e.getMessage());
		}finally {
			session.close();
		}		
	}

	@Override
	public void update(Pagina pagina) {
		try {
			session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.update(pagina);
			tx.commit();
		} catch (Exception e) {
			logger.error("Throwed Exception [PaginaDAOImpl.update]:"+e.getMessage());
		}finally {
			session.close();
		}
		
	}

	@Override
	public List<Pagina> getPageByUrl(String url) {
		
		  List<Pagina> entity = null;
		try {
			session = this.sessionFactory.openSession();
			StringBuilder hql = new StringBuilder();
			hql.append("SELECT p FROM Pagina p WHERE p.realUrl = :realUrl");
			Query query = session.createQuery(hql.toString());
			query.setParameter("realUrl", "%"+url+"%");
			entity = query.list();
		} catch (Exception e) {
			logger.error("Throwed Exception [PaginaDAOImpl.getPageByUrl]:"+e.getMessage());
		}finally {
			session.close();
		}
		return entity;
	}

}
