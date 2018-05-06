package materials;

public class Estate {

    private int _estateAgentID;
    private String _city;
    private String _postalCode;
    private String _street;
    private String _number;
    private String _squareArea;
    
    public Estate(int estateAgentID, String city, String postalCode, String street, String number, String squareArea) {
	_estateAgentID = estateAgentID;
	_city = city;
	_postalCode = postalCode;
	_street = street;
	_number = number;
	_squareArea = squareArea;
    }
    
    public int getEstateAgentID() {
	return _estateAgentID;
    }
    
    public String getCity() {
	return _city;
    }
    
    public String getPostalCode() {
	return _postalCode;
    }
    
    public String getStreet() {
	return _street;
    }
    
    public String getStreetNumber() {
	return _number;
    }
    
    public String getSquareArea() {
	return _squareArea;
    }
}
