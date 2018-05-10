package materials;

import javax.persistence.*;

@Entity
@Table(name = "house")
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_house")
    private int _houseID;

    @Column(name = "fk_id_estate")
    private int _estateID;

    @Column(name = "floors")
    private int _floors;

    @Column(name = "price")
    private double _price;

    @Column(name = "garden")
    private int _garden;

    public House() {
    }

    public House(int estateID, int floors, double price, int garden) {
	_estateID = estateID;
	_floors = floors;
	_price = price;
	_garden = garden;
    }

    public int getHouseID() {
	return _houseID;
    }

    public void setHouseID(int houseID) {
	this._houseID = houseID;
    }

    public int getEstateID() {
	return _estateID;
    }

    public void setEstateID(int estateID) {
	this._estateID = estateID;
    }

    public int getFloors() {
	return _floors;
    }

    public void setFloors(int floors) {
	this._floors = floors;
    }

    public double getPrice() {
	return _price;
    }

    public void setPrice(double price) {
	this._price = price;
    }

    public int getGarden() {
	return _garden;
    }

    public void setGarden(int garden) {
	this._garden = garden;
    }

    @Override
    public String toString() {
	return "House [_houseID=" + _houseID + ", _estateID=" + _estateID + ", _floors=" + _floors + ", _price="
		+ _price + ", _garden=" + _garden + "]";
    }
}
