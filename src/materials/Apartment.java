package materials;

import javax.persistence.*;

@Entity
@Table(name = "apartment")
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_apartment")
    private int _apartmentID;
    
    @Column(name = "fk_id_estate")
    private int _estateID;
    
    @Column(name = "floor")
    private int _floor;
    
    @Column(name = "rent")
    private double _rent;
    
    @Column(name = "rooms")
    private int _rooms;
    
    @Column(name = "balcony")
    private int _balcony;
    
    @Column(name = "built_in_kitchen")
    private int _kitchen;

    public Apartment() {
    }
    
    public Apartment(int estateID, int floor, double rent, int rooms, int balcony, int kitchen) {
	_estateID = estateID;
	_floor = floor;
	_rent = rent;
	_rooms = rooms;
	_balcony = balcony;
	_kitchen = kitchen;
    }

    public int getApartmentID() {
	return _apartmentID;
    }

    public void setApartmentID(int apartmentID) {
	_apartmentID = apartmentID;
    }

    public int getEstateID() {
	return _estateID;
    }

    public void setEstateID(int estateID) {
	_estateID = estateID;
    }

    public int getFloor() {
	return _floor;
    }

    public void setFloor(int floor) {
	_floor = floor;
    }

    public double getRent() {
	return _rent;
    }

    public void setRent(double rent) {
	_rent = rent;
    }

    public int getRooms() {
	return _rooms;
    }

    public void setRooms(int rooms) {
	_rooms = rooms;
    }

    public int getBalcony() {
	return _balcony;
    }

    public void setBalcony(int balcony) {
	_balcony = balcony;
    }

    public int getKitchen() {
	return _kitchen;
    }

    public void setKitchen(int kitchen) {
	_kitchen = kitchen;
    }

    @Override
    public String toString() {
	return "Apartment [_apartmentID=" + _apartmentID + ", _estateID=" + _estateID + ", _floor=" + _floor
		+ ", _rent=" + _rent + ", _rooms=" + _rooms + ", _balcony=" + _balcony + ", _kitchen=" + _kitchen + "]";
    }
}
