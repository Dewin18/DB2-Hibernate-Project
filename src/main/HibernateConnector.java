package main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import materials.EstateAgent;

public class HibernateConnector {

    private static SessionFactory _factory;
    private static HibernateConnector _instance;
    
    private HibernateConnector() {
	_factory = new Configuration()
		.configure("hibernate.db2.cfg.xml")
		.addAnnotatedClass(EstateAgent.class)
		.buildSessionFactory();
    }
    
    public static HibernateConnector getInstance() {
	if (_factory == null) {
	    return new HibernateConnector();
	}
	return _instance;
    }
    
    public Session getCurrentSession() {
	return _factory.getCurrentSession();
    }

}
