package services;

import java.util.List;

import materials.Apartment;
import materials.Estate;
import materials.EstateAgent;
import materials.House;

public interface EstateServiceIF {
    
    // inserts 
    public boolean insertNewEstate(Estate newEstate);
    
    public boolean insertNewHouse(House newHouse);
    
    public boolean insertNewApartment(Apartment newApartment);
    

    // updates
    public boolean updateEstate(String estateID, String[] newEstateAttributes);
    
    public boolean updateHouse(String estateID, String[] newHouseAttributes);
    
    public boolean updateApartment(String estateID, String[] newApartmentAttributes);
    
    // deletions
    public boolean deleteEstate(String estateID);
    
    // queries
    public int getEstateAgentIDFromLoginName(String loginName);
    
    public List<Estate> getEstatesForEstateAgent(EstateAgent estateAgent);

    public boolean estateAgentManagesEstate(EstateAgent estateAgent, String estateID);
    
    public boolean estateAndHouseIsManaged(int estateAgentID, 
					   String city, 
					   String postalCode, 
					   String street,
					   String number, 
					   String squareArea, 
					   int floors, 
					   double price, 
					   int garden);
    
    public boolean estateAndApartmentIsManaged(int estateAgentID,
	    				      String city, 
	    				      String postalCode, 
	    				      String street,
	    				      String number, 
	    				      String squareArea,
	    				      int floor, 
	    				      double rent, 
	    				      int rooms, 
	    				      int balcony, 
	    				      int kitchen);
}
