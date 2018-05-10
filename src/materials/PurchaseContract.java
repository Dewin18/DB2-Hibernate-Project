package materials;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "purchase_contract")
public class PurchaseContract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_purchase_contract")
    private int _purchaseContractID;
    
    @Column(name = "fk_id_person")
    private int _personID;
    
    @Column(name = "fk_id_house")
    private int _houseID;
    
    @Column(name = "fk_id_contract")
    private int _contractID;
    
    @Column(name = "no_of_installments")
    private int _noOfInstallments;
    
    @Column(name = "intrest_rate")
    private int _intrestRate;

    public PurchaseContract() {
    }
    
    public PurchaseContract(int personID, int houseID, int contractID, int noOfInstallments, int intrestRate) {
	_personID = personID;
	_houseID = houseID;
	_contractID = contractID;
	_noOfInstallments = noOfInstallments;
	_intrestRate = intrestRate;
    }
    
    public int getPurchaseContractID() {
	return _purchaseContractID;
    }
    
    public void setPurchaseContractID(int purchaseContractID) {
	_purchaseContractID = purchaseContractID;
    }
    
    public int getPersonID() {
	return _personID;
    }
    
    public void setPersonID(int personID) {
	_personID = personID;
    }
    
    public int getHouseID() {
	return _houseID;
    }
    
    public void setHouseID(int houseID) {
	_houseID = houseID;
    }
    
    public int getContractID() {
	return _contractID;
    }
    
    public void setContractID(int contractID) {
	_contractID = contractID;
    }
    
    public int getNumberOfInstallments() {
	return _noOfInstallments;
    }
    
    public void setNumberOfInstallments(int noOfInstallments) {
	_noOfInstallments = noOfInstallments;
    }
    
    public int getIntrestRate() {
	return _intrestRate;
    }
    
    public void setIntrestRate(int intrestRate) {
	_intrestRate = intrestRate;
    }

    @Override
    public String toString() {
	return "PurchaseContract [_purchaseContractID=" + _purchaseContractID + ", _personID=" + _personID
		+ ", _houseID=" + _houseID + ", _contractID=" + _contractID + ", _noOfInstallments=" + _noOfInstallments
		+ ", _intrestRate=" + _intrestRate + "]";
    }
}