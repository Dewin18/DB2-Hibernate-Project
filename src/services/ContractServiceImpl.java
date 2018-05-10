package services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import main.HibernateConnector;
import materials.Apartment;
import materials.Contract;
import materials.House;
import materials.Person;
import materials.PurchaseContract;
import materials.TenancyContract;

public class ContractServiceImpl implements ContractServiceIF {

    private HibernateConnector _hibernateConnection;
    
    public ContractServiceImpl(HibernateConnector hibernateConnection) {
	_hibernateConnection  = hibernateConnection;
    }

    @Override
    public boolean insertContract(Contract newContract) {
	
	Session session = _hibernateConnection.getNewSession();

	try {
	    session.beginTransaction();
	    session.save(newContract);
	    session.getTransaction().commit();
	    session.close();
	    return true;
	} catch (ConstraintViolationException e) {
	    e.printStackTrace();
	    session.getTransaction().rollback();
	    return false;
	}
    }

    @Override
    public boolean insertPurchaseContract(PurchaseContract newPurchaseContract) {

	Session session = _hibernateConnection.getNewSession();

	try {
	    session.beginTransaction();
	    session.save(newPurchaseContract);
	    session.getTransaction().commit();
	    session.close();
	    return true;
	} catch (ConstraintViolationException e) {
	    e.printStackTrace();
	    session.getTransaction().rollback();
	    return false;
	}
    }

    @Override
    public boolean insertTenancyContract(TenancyContract newTenancyContract) {

	Session session = _hibernateConnection.getNewSession();

	try {
	    session.beginTransaction();
	    session.save(newTenancyContract);
	    session.getTransaction().commit();
	    session.close();
	    return true;
	} catch (ConstraintViolationException e) {
	    e.printStackTrace();
	    session.getTransaction().rollback();
	    return false;
	}
    }

    @Override
    public List<Contract> getContractList() {
	
	Session session = _hibernateConnection.getNewSession();

	session.beginTransaction();
	@SuppressWarnings("unchecked")
	List<Contract> contracts = 
	session.createQuery("from Contract").getResultList();
	session.getTransaction().commit();
	session.close();
	return contracts;
    }

    @Override
    public boolean personExist(String firstName, String name, String address) {
	
	Session session = _hibernateConnection.getNewSession();

	session.beginTransaction();
	@SuppressWarnings("unchecked")
	List<Person> persons = 
	session.createQuery("from Person p "
			  + "where p._firstName ='" + firstName + "' "
			  + "and p._name = '" + name + "' "
			  + "and p._address = '" + address + "'")
			.getResultList();
	
	
	session.getTransaction().commit();
	session.close();
	return !persons.isEmpty();
    }

    @Override
    public boolean insertPerson(Person newPerson) {
	
	Session session = _hibernateConnection.getNewSession();

	try {
	    session.beginTransaction();
	    session.save(newPerson);
	    session.getTransaction().commit();
	    session.close();
	    return true;
	} catch (ConstraintViolationException e) {
	    e.printStackTrace();
	    session.getTransaction().rollback();
	    return false;
	}
    }

    @Override
    public boolean personExist(String personID) {
	
	Session session = _hibernateConnection.getNewSession();

	session.beginTransaction();
	@SuppressWarnings("unchecked")
	List<Person> persons = 
	session.createQuery("from Person p "
			  + "where p._personID ='" + personID + "'")
			.getResultList();
	
	session.getTransaction().commit();
	session.close();
	return !persons.isEmpty();
    }

    @Override
    public boolean contractExist(String contractNo) {
	Session session = _hibernateConnection.getNewSession();

	session.beginTransaction();
	@SuppressWarnings("unchecked")
	List<Contract> contracts = 
	session.createQuery("from Contract c "
			  + "where c._contractNumber ='" + contractNo + "'")
			.getResultList();
	
	session.getTransaction().commit();
	session.close();
	return !contracts.isEmpty();
    }

    @Override
    public boolean houseExist(String houseID) {
	Session session = _hibernateConnection.getNewSession();

	session.beginTransaction();
	@SuppressWarnings("unchecked")
	List<House> houses = 
	session.createQuery("from House h "
			  + "where h._houseID ='" + houseID + "'")
			.getResultList();
	
	session.getTransaction().commit();
	session.close();
	return !houses.isEmpty();
    }

    @Override
    public boolean apartmentExist(String apartmentID) {
	Session session = _hibernateConnection.getNewSession();

	session.beginTransaction();
	@SuppressWarnings("unchecked")
	List<Apartment> apartments = 
	session.createQuery("from Apartment a "
			  + "where a._apartmentID ='" + apartmentID + "'")
			.getResultList();
	
	session.getTransaction().commit();
	session.close();
	return !apartments.isEmpty();
    }
}
