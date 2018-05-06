package services;

import materials.Apartment;
import materials.Estate;
import materials.House;

public interface EstateServiceIF {
    
    // queries
    public int getEstateAgentIDFromLoginName(String loginName);

    public int getEstateID(Estate estate);
    
    public boolean estateAndHouseIsManaged(
	        int estateAgentID, 
		String city, 
		String postalCode, 
		String street,
		String number, 
		String squareArea, 
		int floors, 
		double price, 
		int garden);
    
    public boolean estateAndApartmentIsManaged(
	    	   int estateAgentID, 
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
    
    public boolean estateAgentManagesEstate(String loginName, String estateID);

    public boolean estateAgentManagesApartment(String loginName, String estateID);

    public boolean estateAgentManagesHouse(String loginName, String estateID);
    
    // inserts 
    public boolean insertNewEstate(Estate estate);
    
    public boolean insertNewHouse(House house);
    
    public boolean insertNewApartment(Apartment newApartment);
    

    // updates
    public boolean updateGeneralEntry(String column, String estateID, String newAttribute);
    
    public boolean updateHouseEntry(String column, String estateID, String newAttribute);
    
    public boolean updateApartmentEntry(String column, String estateID, String newAttribute);

    
    // deletions
    public boolean deleteEstate(String estateID);
}
