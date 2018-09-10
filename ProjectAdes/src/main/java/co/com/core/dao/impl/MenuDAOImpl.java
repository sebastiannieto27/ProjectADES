package co.com.core.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import co.com.core.dao.MenuDAO;
import co.com.core.domain.Menu;
import co.com.core.domain.RoleMenu;
import co.com.core.domain.RoleUsuarios;
import co.com.core.domain.Usuarios;

public class MenuDAOImpl implements MenuDAO{
	
	
	private SessionFactory sessionFactory;
	private Session session;
	private static final Logger logger  = Logger.getLogger(MenuDAOImpl.class);
	
	
          
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<RoleUsuarios> getUserRoles(Usuarios usuarios) {
		List<RoleUsuarios> roleUsuarios = null;
		try {
			session = this.sessionFactory.openSession();
			StringBuilder hql = new StringBuilder();
			hql.append("SELECT r FROM RoleUsuarios r WHERE r.idUsuario = ?");
			SQLQuery query = session.createSQLQuery("SELECT * FROM role_usuarios ur WHERE ur.ID_USUARIO =:idUsuario");
			query.addEntity(RoleUsuarios.class);
			query.setParameter("idUsuario", usuarios.getIdUsuario());
			roleUsuarios = query.list();
		} catch (Exception e) {
			logger.error("Throwed Exception [MenuDAOImpl.getUserRoles]:"+e.getMessage());
			e.getStackTrace();
		}finally {
			session.close();
		}
		
		return roleUsuarios;
	}

	@Override
	public List<RoleMenu> getUserRoleMenu(List<RoleUsuarios> list) {
		int counter = 0; 
		StringBuilder rolIds = new StringBuilder();
		if (list != null && list.size() > 0) {
			for (RoleUsuarios roleUsuarios : list) {
				if (counter > 0) {
					rolIds.append(",");
					
				}
				rolIds.append(roleUsuarios.getRoleId().getRoleId());
				counter++;
			}
			
		}
		List<RoleMenu> menus = null;
		try {
			session = this.sessionFactory.openSession();
			StringBuilder hql = new StringBuilder();
			hql.append("SELECT r FROM RoleMenu r WHERE r.roleId in(").append(rolIds).append(")");
			Query query = session.createQuery(hql.toString());
			menus = query.list();
		} catch (Exception e) {
			logger.error("Throwed Exception [MenuDAOImpl.getUserRoleMenu]:"+e.getMessage());
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return menus;
	}

	@Override
	public List<Menu> getUserMenuOptions(List<RoleMenu> list) {
		int counter = 0;
		StringBuilder menuIds = new StringBuilder();
			if (list != null && list.size() > 0) {
				for (RoleMenu roleMenu : list) {
					if (counter > 0) {
				       menuIds.append(",");
					}
					
					menuIds.append(roleMenu.getMenuId().getMenuId());
					counter++;
				}
				
			}
			
			List<Menu> menus = null;
			
			try {
				session = this.sessionFactory.openSession();
				StringBuilder hql = new StringBuilder();
				hql.append("SELECT m FROM Menu m WHERE m.menuId in(").append(menuIds).append(")");
				Query query = session.createQuery(hql.toString());
				menus = query.list();
			} catch (Exception e) {
				logger.error("Throwed Exception [MenuDAOImpl.getUserMenuOptions]:"+e.getStackTrace());
				e.printStackTrace();

			} finally {
				session.close();
			}
		return menus;
	}

	@Override
	public List<Menu> getMenuGeneral() {
		
		List<Menu> menuList = null;
		
		try {
			session = this.sessionFactory.openSession();
			StringBuilder hql = new StringBuilder();
			hql.append("SELECT m FROM Menu m WHERE m.general = 1");
			Query query = session.createQuery(hql.toString());
			menuList = query.list();
			
		} catch (Exception e) {
			logger.error("Throwed Exception [MenuDAOImpl.getMenuGeneral]: " +e.getMessage());
		}finally {
			session.close();

		}
		
		
		return menuList;
	}

	@Override
	public List<Menu> getAll() {
		List<Menu> entity = null;
		try {
			session = this.sessionFactory.openSession();
			Query query = session.getNamedQuery("Menu.findAll");
			entity = query.list();
		} catch (Exception e) {
			logger.error("Throwed Exception [MenuDAOImpl.getAll]:"+e.getMessage());
		}finally {
			session.close();
		}
		return entity;
	}

	@Override
	public List<Menu> getNotAssignedMenu(String ids) {
		List<Menu> entity = null;
		try {
			session = this.sessionFactory.openSession();
			StringBuilder hql = new StringBuilder();
			hql.append("SELECT m FROM Menu m WHERE m.menuId NOT IN (").append(ids).append(")");
			Query query = session.createQuery(hql.toString());
			entity = query.list();
		} catch (Exception e) {
			logger.error("Throwed Exception [MenuDAOImpl.getNotAssignedMenu]:"+e.getMessage());
		}finally {
			session.close();
		}
		return entity;
	}

	@Override
	public void create(Menu menu) {
		try {
			session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.save(menu);
			tx.commit();
		} catch (Exception e) {
			logger.error("Throwed Exception [MenuDAOImpl.create]:"+e.getMessage());
			session.getTransaction().rollback();
		}finally {
			session.close();
		}
		
	}

	@Override
	public void delete(Menu menu) {
		try {
			session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.delete(menu);
			tx.commit();
		} catch (Exception e) {
			logger.error("Throwed Exception [MenuDAOImpl.delete]:"+e.getMessage());
			session.getTransaction().rollback();
		}finally {
			session.close();
		}
		
	}

	@Override
	public void update(Menu menu) {
		try {
			session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.update(menu);
			tx.commit();
		} catch (Exception e) {
			logger.error("Throwed Exception [MenuDAOImpl.update]:"+e.getMessage());
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		
		
	}

}
