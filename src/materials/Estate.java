package materials;

import javax.persistence.*;

@Entity
@Table(name = "estate")
public class Estate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estate")
    private int _estateID;

    @Column(name = "fk_id_estate_agent")
    private int _estateAgentID;

    @Column(name = "city")
    private String _city;

    @Column(name = "postal_code")
    private String _postalCode;

    @Column(name = "street")
    private String _street;

    @Column(name = "street_number")
    private String _streetNumber;

    @Column(name = "square_area")
    private String _squareArea;

    public Estate() {
    }

    public Estate(int estateAgentID, String city, String postalCode, String street, String streetNumber,
	    String squareArea) {
	_estateAgentID = estateAgentID;
	_city = city;
	_postalCode = postalCode;
	_street = street;
	_streetNumber = streetNumber;
	_squareArea = squareArea;
    }

    public int getEstateID() {
	return _estateID;
    }

    public void setEstateID(int estateID) {
	_estateID = estateID;
    }

    public int getEstateAgentID() {
	return _estateAgentID;
    }

    public void setEstateAgentID(int estateAgentID) {
	_estateAgentID = estateAgentID;
    }

    public String getCity() {
	return _city;
    }

    public void setCity(String city) {
	_city = city;
    }

    public String getPostalCode() {
	return _postalCode;
    }

    public void setPostalCode(String postalCode) {
	_postalCode = postalCode;
    }

    public String getStreet() {
	return _street;
    }

    public void setStreet(String street) {
	_street = street;
    }

    public String getStreetNumber() {
	return _streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
	_streetNumber = streetNumber;
    }

    public String getSquareArea() {
	return _squareArea;
    }

    public void setSquareArea(String squareArea) {
	_squareArea = squareArea;
    }

    @Override
    public String toString() {
	return "Estate [_estateID=" + _estateID + ", _estateAgentID=" + _estateAgentID + ", _city=" + _city
		+ ", _postalCode=" + _postalCode + ", _street=" + _street + ", _streetNumber=" + _streetNumber
		+ ", _squareArea=" + _squareArea + "]";
    }
}
