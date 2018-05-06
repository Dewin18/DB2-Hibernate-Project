package materials;

public class Apartment {
    private int _estateID;
    private int _floor;
    private double _rent;
    private int _room;
    private int _balcony;
    private int _kitchen;
    
    public Apartment(int estateID, int floor, double rent, int room, int balcony, int kitchen) {
	_estateID = estateID;
	_floor = floor;
	_rent = rent;
	_room = room;
	_balcony = balcony;
	_kitchen = kitchen;
    }
    
    public int getEstateID() {
	return _estateID;
    }
    
    public int getFloor() {
	return _floor;
    }
    
    public double getRent() {
	return _rent;
    }
    
    public int getRooms() {
	return _room;
    }
    
    public int getBalcony() {
	return _balcony;
    }
    
    public int getKitchen() {
	return _kitchen;
    }
}
