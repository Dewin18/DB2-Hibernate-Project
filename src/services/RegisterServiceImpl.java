package services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import main.HibernateConnector;
import materials.EstateAgent;

public class RegisterServiceImpl implements RegisterServiceIF {

    private HibernateConnector _hibernateConnection;

    public RegisterServiceImpl(HibernateConnector hibernateConnection) {
	_hibernateConnection = hibernateConnection;
    }
    
    @Override
    public boolean registerEstateAgent(EstateAgent newAgent) {

	Session session = _hibernateConnection.getNewSession();

	try {
	    session.beginTransaction();
	    session.save(newAgent);
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
    public boolean deleteEstateAgent(String loginName) {

	Session session = _hibernateConnection.getNewSession();

	session.beginTransaction();
	int affectedRows = session.createQuery("delete from EstateAgent where _login = '" + loginName + "'")
		.executeUpdate();
	session.getTransaction().commit();
	session.close();

	boolean deleted = (affectedRows == 1) ? true : false;
	return deleted;
    }

    @Override
    public boolean updateEstateAgent(String name, String address, String loginName, String password) {

	Session session = _hibernateConnection.getNewSession();

	session.beginTransaction();
	@SuppressWarnings("unchecked")
	List<EstateAgent> agents = session.createQuery("from EstateAgent a where a._login = '" + loginName + "'")
		.getResultList();

	if (!agents.isEmpty()) {
	    EstateAgent agent = agents.get(0);

	    agent.setName(name);
	    agent.setAddress(address);
	    agent.setPassword(password);

	    session.getTransaction().commit();
	    session.close();
	    return true;
	}
	session.getTransaction().rollback();
	return false;
    }

}
