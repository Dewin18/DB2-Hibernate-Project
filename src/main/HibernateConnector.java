package main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import materials.Apartment;
import materials.Contract;
import materials.Estate;
import materials.EstateAgent;
import materials.House;
import materials.Person;
import materials.PurchaseContract;
import materials.TenancyContract;

public class HibernateConnector {

    private static SessionFactory _factory = null;
    private static HibernateConnector _instance = null;
    private static Session _session = null;
    
    
    private HibernateConnector() {
	_factory = new Configuration().configure("hibernate.db2.cfg.xml")
		.addAnnotatedClass(EstateAgent.class)
		.addAnnotatedClass(Estate.class)
		.addAnnotatedClass(House.class)
		.addAnnotatedClass(Apartment.class)
		.addAnnotatedClass(Person.class)
		.addAnnotatedClass(Contract.class)
		.addAnnotatedClass(PurchaseContract.class)
		.addAnnotatedClass(TenancyContract.class)
		.buildSessionFactory();
	
	_session = _factory.getCurrentSession();
    }

    public static HibernateConnector getInstance() {
	if (_factory == null) {
	    return new HibernateConnector();
	}
	return _instance;
    }

    public Session getCurrentSession() {
	return _session;
    }
    
    public Session getNewSession() {
	return _factory.openSession();
    }
}
