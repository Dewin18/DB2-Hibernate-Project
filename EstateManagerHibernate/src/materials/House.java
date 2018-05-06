package materials;

public class House {
    private int _estateID;
    private int _floors;
    private double _price;
    private int _garden;
    
    public House(int estateID, int floors, double price, int garden) {
	_estateID = estateID;
	_floors = floors;
	_price = price;
	_garden = garden;
    }
    
    public int getEstateID() {
	return _estateID;
    }
    
    public int getFloors() {
	return _floors;
    }
    
    public double getPrice() {
	return _price;
    }
    
    public int getGarden() {
	return _garden;
    }
}
