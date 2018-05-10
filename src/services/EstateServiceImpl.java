package services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import main.HibernateConnector;
import materials.Apartment;
import materials.Estate;
import materials.EstateAgent;
import materials.House;

public class EstateServiceImpl implements EstateServiceIF {	
    
    private HibernateConnector _hibernateConnection;

    public EstateServiceImpl(HibernateConnector hibernateConnection) {
	_hibernateConnection = hibernateConnection;
    }
    
    @Override
    public boolean insertNewEstate(Estate newEstate) {
	
	Session session = _hibernateConnection.getNewSession();

	try {
	    session.beginTransaction();
	    session.save(newEstate);
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
    public boolean insertNewHouse(House newHouse) {
	Session session = _hibernateConnection.getNewSession();

	try {
	    session.beginTransaction();
	    session.save(newHouse);
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
    public boolean insertNewApartment(Apartment newApartment) {
	Session session = _hibernateConnection.getNewSession();

	try {
	    session.beginTransaction();
	    session.save(newApartment);
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
    public boolean updateEstate(String estateID, String[] newEstateAttributes) {
	Session session = _hibernateConnection.getNewSession();

	session.beginTransaction();
	@SuppressWarnings("unchecked")
	List<Estate> estate = session.createQuery("from Estate e where e._estateID = '" + estateID + "'")
		.getResultList();
	
	if (!estate.isEmpty()) {
	    Estate updatedEstate = estate.get(0);

	    updatedEstate.setCity(newEstateAttributes[0]);
	    updatedEstate.setPostalCode(newEstateAttributes[1]);
	    updatedEstate.setStreet(newEstateAttributes[2]);
	    updatedEstate.setStreetNumber(newEstateAttributes[3]);
	    updatedEstate.setSquareArea(newEstateAttributes[4]);
	    
	    session.getTransaction().commit();
	    session.close();
	    return true;
	}
	session.getTransaction().rollback();
	return false;
    }

    @Override
    public boolean updateHouse(String houseID, String[] newHouseAttributes) {
	Session session = _hibernateConnection.getNewSession();

	session.beginTransaction();
	@SuppressWarnings("unchecked")
	List<House> houses = session.createQuery("from House h where h._estateID = '" + houseID + "'")
		.getResultList();
	
	if (!houses.isEmpty()) {
	    House updatedHouse = houses.get(0);

	    updatedHouse.setFloors(Integer.parseInt(newHouseAttributes[0]));
	    updatedHouse.setPrice(Double.parseDouble(newHouseAttributes[1]));
	    updatedHouse.setGarden(Integer.parseInt(newHouseAttributes[2]));
	    
	    session.getTransaction().commit();
	    session.close();
	    return true;
	}
	session.getTransaction().rollback();
	return false;
    }

    @Override
    public boolean updateApartment(String apartmentID, String[] newApartmentAttributes) {
	Session session = _hibernateConnection.getNewSession();

	session.beginTransaction();
	@SuppressWarnings("unchecked")
	List<Apartment> apartments = session.createQuery("from Apartment a where a._estateID = '" + apartmentID + "'")
		.getResultList();
	
	if (!apartments.isEmpty()) {
	    Apartment updatedApartment= apartments.get(0);

	    updatedApartment.setFloor(Integer.parseInt(newApartmentAttributes[0]));
	    updatedApartment.setRent(Double.parseDouble(newApartmentAttributes[1]));
	    updatedApartment.setRooms(Integer.parseInt(newApartmentAttributes[2]));
	    updatedApartment.setBalcony(Integer.parseInt(newApartmentAttributes[3]));
	    updatedApartment.setKitchen(Integer.parseInt(newApartmentAttributes[4]));
	    
	    session.getTransaction().commit();
	    session.close();
	    return true;
	}
	session.getTransaction().rollback();
	return false;
    }
    
    @Override
    public boolean deleteEstate(String estateID) {
	
	Session session = _hibernateConnection.getNewSession();

	session.beginTransaction();
	int affectedRows = session.createQuery("delete from Estate where _estateID = '" + estateID + "'")
		.executeUpdate();
	session.getTransaction().commit();
	session.close();

	boolean deleted = (affectedRows == 1) ? true : false;
	return deleted;
    }
    
    @Override
    public int getEstateAgentIDFromLoginName(String loginName) {
	
	Session session = _hibernateConnection.getNewSession();

	session.beginTransaction();
	@SuppressWarnings("unchecked")
	List<EstateAgent> agents = 
	session.createQuery("from EstateAgent a "
			  + "where a._login = '" + loginName + "'")
			  .getResultList();
	
	EstateAgent agent = agents.get(0); 
	session.getTransaction().commit();
	session.close();
	return agent.getId();
    }
    
    @Override
    public List<Estate> getEstatesForEstateAgent(EstateAgent estateAgent) {
	
	Session session = _hibernateConnection.getNewSession();

	session.beginTransaction();
	@SuppressWarnings("unchecked")
	List<Estate> estate = session.createQuery("from Estate e where e._estateAgentID = '" + estateAgent.getId() + "'")
	.getResultList();
	
	session.getTransaction().commit();
	session.close();
	return estate;
    }
    
    @Override
    public boolean estateAgentManagesEstate(EstateAgent estateAgent, String estateID) {
	
	int estateAgentID = estateAgent.getId();
	
	Session session = _hibernateConnection.getNewSession();

	session.beginTransaction();
	@SuppressWarnings("unchecked")
	List<Estate> estate = 
	session.createQuery("from Estate e "
                          + "where e._estateID = '" + estateID + "' "
			  + "and e._estateAgentID = '" + estateAgentID + "'")
		          .getResultList();
	
	session.getTransaction().commit();
	session.close();
	return !estate.isEmpty();
    }

    @Override
    public boolean estateAndHouseIsManaged(int estateAgentID, String city, String postalCode, String street, String streetNumber, String squareArea, int floors, double price, int garden) {
	
	boolean estateIsManaged = estateIsManaged(estateAgentID, city, postalCode, street, streetNumber, squareArea);
	boolean houseIsManaged = houseIsManaged(floors, price, garden);
	
	return estateIsManaged && houseIsManaged;
    }
    
    @Override
    public boolean estateAndApartmentIsManaged(int estateAgentID, String city, String postalCode, String street,
	    String streetNumber, String squareArea, int floor, double rent, int rooms, int balcony, int kitchen) {
	
	boolean estateIsManaged = estateIsManaged(estateAgentID, city, postalCode, street, streetNumber, squareArea);
	boolean apartmentIsManaged = apartmentIsManaged(floor, rent, rooms, balcony, kitchen);
	
	return estateIsManaged && apartmentIsManaged;
    }

    private boolean estateIsManaged(int estateAgentID, String city, String postalCode, String street,
	    String streetNumber, String squareArea) {
	
	Session session = _hibernateConnection.getNewSession();

	session.beginTransaction();
	@SuppressWarnings("unchecked")
	List<Estate> estate = 
	session.createQuery("from Estate e "
			  + "where e._estateAgentID = '" + estateAgentID + "' "
			  + "and e._city = '" + city + "' "
			  + "and e._postalCode = '" + postalCode + "' "
			  + "and e._street = '" + street + "' "
		 	  + "and e._streetNumber = '" + streetNumber + "' "
			  + "and e._squareArea = '" + squareArea + "'")
			  .getResultList();
	
	
	session.getTransaction().commit();
	session.close();
	return !estate.isEmpty();
    }
    
    private boolean houseIsManaged(int floors, double price, int garden) {

	Session session = _hibernateConnection.getNewSession();

	session.beginTransaction();
	@SuppressWarnings("unchecked")
	List<House> houses = 
	session.createQuery("from House h "
                          + "where h._floors = '" + floors + "' "
			  + "and h._price = '" + price + "' "
			  + "and h._garden = '" + garden + "'")
		          .getResultList();
	
	session.getTransaction().commit();
	session.close();
	return !houses.isEmpty();	
     }
    
    private boolean apartmentIsManaged(int floor, double rent, int rooms, int balcony, int kitchen) {
	
	Session session = _hibernateConnection.getNewSession();

	session.beginTransaction();
	@SuppressWarnings("unchecked")
	List<Apartment> apartments = 
	session.createQuery("from Apartment a "
                          + "where a._floor = '" + floor + "' "
			  + "and a._rent = '" + rent + "' "
			  + "and a._rooms = '" + rooms + "' "
			  + "and a._balcony = '" + balcony + "' "
			  + "and a._kitchen = '" + kitchen + "'")
		          .getResultList();
	
	session.getTransaction().commit();
	session.close();
	return !apartments.isEmpty();
    }
}
