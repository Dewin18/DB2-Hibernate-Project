package services;

import java.sql.*;

import main.DB2ConnectionManager;
import materials.Apartment;
import materials.Estate;
import materials.House;

public class EstateServiceImpl implements EstateServiceIF {	
    
    private Connection connection;
    private Statement stmt;

    public EstateServiceImpl() {
	try {
	    connection = DB2ConnectionManager.getInstance().getConnection();
	    stmt = connection.createStatement();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }
    
    @Override
    public int getEstateAgentIDFromLoginName(String loginName) {
	try {
	    String sqlSelectStatement = 
			  "SELECT * "
			+ "FROM estate_agent "
			+ "WHERE login = '" + loginName + "'";

	    ResultSet resultSet = stmt.executeQuery(sqlSelectStatement);
	    
	    if(resultSet.next()) {
		return resultSet.getInt("id_estate_agent");
	    }
	    
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return -1;
    }


    @Override
    public boolean insertNewEstate(Estate estate) {
	try {
	    String sqlInsertEstateStatement = 
		    "INSERT INTO "
	           + "estate(FK_ID_ESTATE_AGENT, CITY, POSTAL_CODE, STREET, STREET_NUMBER, SQUARE_AREA) "
	           + "VALUES (?, ?, ?, ?, ?, ?)";
	    
	    PreparedStatement insertEstateStmt = connection.prepareStatement(sqlInsertEstateStatement);

	    insertEstateStmt.setInt(1, estate.getEstateAgentID());
	    insertEstateStmt.setString(2, estate.getCity());
	    insertEstateStmt.setString(3, estate.getPostalCode());
	    insertEstateStmt.setString(4, estate.getStreet());
	    insertEstateStmt.setString(5, estate.getStreetNumber());
	    insertEstateStmt.setString(6, estate.getSquareArea());
	    insertEstateStmt.executeUpdate();
	    
	    return true;
	    } catch (SQLException e) {
		e.printStackTrace();
		}
	return false;
    }
    
    @Override
    public int getEstateID(Estate estate) {
	try {
	    String sqlSelectEstateID = 
		    "SELECT id_estate "
		  + "FROM estate "
		  + "WHERE FK_ID_ESTATE_AGENT = '"+ estate.getEstateAgentID() + "' "
		  + "AND CITY = '" + estate.getCity() + "' "
		  + "AND POSTAL_CODE = '" + estate.getPostalCode() + "' "
		  + "AND STREET = '" + estate.getStreet() + "' "
		  + "AND STREET_NUMBER = '" + estate.getStreetNumber() + "' "
		  + "AND SQUARE_AREA = '" + estate.getSquareArea() + "'";
	    
	    ResultSet resultSet = stmt.executeQuery(sqlSelectEstateID);

	    if(resultSet.next()) {
		return resultSet.getInt("id_estate");
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return -1;
    }

    @Override
    public boolean insertNewHouse(House house) {
	try {
	    String sqlInsertHouseStatement = 
		    "INSERT INTO "
   	          + "house(FK_ID_ESTATE, FLOORS, PRICE, GARDEN) "
	          + "VALUES (?, ?, ?, ?)";
	    
	    PreparedStatement insertHouseStmt = connection.prepareStatement(sqlInsertHouseStatement);

	    insertHouseStmt.setInt(1, house.getEstateID());
	    insertHouseStmt.setInt(2, house.getFloors());
	    insertHouseStmt.setDouble(3, house.getPrice());
	    insertHouseStmt.setInt(4, house.getGarden());
	    insertHouseStmt.executeUpdate();
	    return true;
	    } catch (SQLException e) {
		e.printStackTrace();
		}
	return false;
    }

    @Override
    public boolean estateAndHouseIsManaged(int estateAgentID, String city, String postalCode, String street, String streetNumber, String squareArea, int floors, double price, int garden) {
	    try {
		    String sqlSelectEstateAndHouseForAgent = 
		       "SELECT * "
		     + "FROM estate e, house h "
		     + "WHERE e.id_estate = h.fk_id_estate "
		     + "AND e.FK_ID_ESTATE_AGENT = '"+ estateAgentID + "' "
		     + "AND e.CITY = '" + city + "' "
		     + "AND e.POSTAL_CODE = '" + postalCode + "' "
		     + "AND e.STREET = '" + street + "' "
		     + "AND e.STREET_NUMBER = '" + streetNumber + "' "
		     + "AND e.SQUARE_AREA = '" + squareArea + "' "
		     + "AND h.FLOORS = '" + floors + "' "
		     + "AND h.PRICE = '" + price + "' "
		     + "AND h.GARDEN = '" + garden + "'";
		    
		ResultSet resultSet = stmt.executeQuery(sqlSelectEstateAndHouseForAgent);
		
		if(resultSet.next()) return true;
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	   return false;
    }

    @Override
    public boolean updateGeneralEntry(String column, String estateID, String newAttribute) {
	try {
	    Statement updateStmt = connection.createStatement();
	    String updateGeneral =  
		    "UPDATE estate "
	     	  + "SET " + column + " = '" + newAttribute + "' " 
	     	  + "WHERE id_estate = '" + estateID + "'";
	    
	    updateStmt.executeUpdate(updateGeneral);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return false;
    }

    @Override
    public boolean updateHouseEntry(String column, String estateID, String newAttribute) {
	try {
	    String updateHouse =  
		    "UPDATE house "
		  + "SET " + column + " = '" + newAttribute + "' " 
		  + "WHERE fk_id_estate = '" + estateID + "'";
	    
	    stmt.executeUpdate(updateHouse);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return false;
    }

    @Override
    public boolean updateApartmentEntry(String column, String estateID, String newAttribute) {
	try {
	    String updateApartment =  
		    "UPDATE apartment "
		  + "SET " + column + " = '" + newAttribute + "' " 
		  + "WHERE fk_id_estate = '" + estateID + "'";
	    
	    stmt.executeUpdate(updateApartment);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return false;
    }
    
    @Override
    public boolean estateAndApartmentIsManaged(int estateAgentID, String city, String postalCode, String street,
	    String number, String squareArea, int floor, double rent, int rooms, int balcony, int kitchen) {
	    try {
		    String sqlSelectEstateAndApartmentForAgent = 
			    "SELECT * "
			  + "FROM estate e, apartment a "
			  + "WHERE e.id_estate = a.fk_id_estate "
			  + "AND e.FK_ID_ESTATE_AGENT = '"+ estateAgentID + "' "
			  + "AND e.CITY = '" + city + "' "
			  + "AND e.POSTAL_CODE = '" + postalCode + "' "
			  + "AND e.STREET = '" + street + "' "
			  + "AND e.STREET_NUMBER = '" + number + "' "
			  + "AND e.SQUARE_AREA = '" + squareArea + "' "
			  + "AND a.FLOOR = '" + floor + "' "
			  + "AND a.RENT = '" + rent + "' "
			  + "AND a.ROOMS = '" + rooms + "' "
			  + "AND a.BALCONY = '" + balcony + "' "
			  + "AND a.BUILT_IN_KITCHEN = '" + kitchen + "'";
		    
		ResultSet resultSet = stmt.executeQuery(sqlSelectEstateAndApartmentForAgent);
		
		if(resultSet.next()) return true;
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	   return false;
    }

    @Override
    public boolean insertNewApartment(Apartment newApartment) {
	try {
	    String sqlInsertApartmentStatement = 
		     "INSERT INTO "
		   + "apartment(FK_ID_ESTATE, FLOOR, RENT, ROOMS, BALCONY, BUILT_IN_KITCHEN) "
	           + "VALUES (?, ?, ?, ?, ?, ?)";
	  
	    PreparedStatement insertApartmentStmt = connection.prepareStatement(sqlInsertApartmentStatement);

	    insertApartmentStmt.setInt(1,newApartment.getEstateID());
	    insertApartmentStmt.setInt(2, newApartment.getFloor());
	    insertApartmentStmt.setDouble(3, newApartment.getRent());
	    insertApartmentStmt.setInt(4, newApartment.getRooms());
	    insertApartmentStmt.setInt(5, newApartment.getBalcony());
	    insertApartmentStmt.setInt(6, newApartment.getKitchen());
	    insertApartmentStmt.executeUpdate();
	    return true;
	    } catch (SQLException e) {
		e.printStackTrace();
		}
	return false;
    }
  
    @Override
    public boolean estateAgentManagesApartment(String loginName, String estateID) {
	
	int estateAgentID = getEstateAgentIDFromLoginName(loginName);
	try {
	    String sqlSelectStatement = 
		    "SELECT * " 
		  + "FROM estate e, apartment a "
		  + "WHERE e.id_estate = a.fk_id_estate "
		  + "AND e.id_estate = '" + estateID + "' "
		  + "AND e.fk_id_estate_agent = '" + estateAgentID + "'";

	    ResultSet resultSet = stmt.executeQuery(sqlSelectStatement);
	    if(resultSet.next()) return true;
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return false;
    }
    
    @Override
    public boolean estateAgentManagesHouse(String loginName, String estateID) {
	
	int estateAgentID = getEstateAgentIDFromLoginName(loginName);
	
	try {
	    String sqlSelectStatement = 
		    "SELECT * " 
		  + "FROM estate e, house h "
		  + "WHERE e.id_estate = h.fk_id_estate "
		  + "AND e.id_estate = '" + estateID + "' "
		  + "AND e.fk_id_estate_agent = '" + estateAgentID + "'";
	  
	    ResultSet resultSet = stmt.executeQuery(sqlSelectStatement);
	    if(resultSet.next()) return true;
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return false;
    }

    @Override
    public boolean estateAgentManagesEstate(String loginName, String estateID) {

	int estateAgentID = getEstateAgentIDFromLoginName(loginName);
	
	try {
	    String sqlSelectStatement = 
		    "SELECT * " 
		  + "FROM estate "
		  + "WHERE id_estate = '" + estateID + "' "
		  + "AND fk_id_estate_agent = '" + estateAgentID + "'";

	    ResultSet resultSet = stmt.executeQuery(sqlSelectStatement);
	    if(resultSet.next()) return true;
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return false;
    }
    
    @Override
    public boolean deleteEstate(String estateID) {
	try {
	    String sqlDeleteStatement = 
		    "DELETE " 
		  + "FROM estate " 
		  + "WHERE id_estate = '" + estateID + "'";
	    
	    stmt.executeUpdate(sqlDeleteStatement);
	    return true;
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	return false;
	}
}
