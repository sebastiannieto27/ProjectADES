package co.com.core.commons;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanFactory {
	
	ApplicationContext context = new ClassPathXmlApplicationContext("application-bean-context.xml");
	
	 private Session session;
	 private SessionFactory sessionFactory;
	 
	public SessionFactory getSessionFactory() {
		sessionFactory = (SessionFactory) context.getBean("hibernate4AnnotatedSessionFactory");
		return sessionFactory;
	}

}
