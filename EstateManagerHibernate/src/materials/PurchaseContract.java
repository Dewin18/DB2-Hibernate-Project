package materials;

public class PurchaseContract {

    private int _personID;
    private int _houseID;
    private int _contractID;
    private int _noOfInstallments;
    private int _intrestRate;

    public PurchaseContract(int personID, int houseID, int contractID, int noOfInstallments, int intrestRate) {
	_personID = personID;
	_houseID = houseID;
	_contractID = contractID;
	_noOfInstallments = noOfInstallments;
	_intrestRate = intrestRate;
    }
    
    public int getPersonID() {
	return _personID;
    }
    
    public int getHouseID() {
	return _houseID;
    }
    
    public int getContractID() {
	return _contractID;
    }
    
    public int getNumberOfInstallments() {
	return _noOfInstallments;
    }
    
    public int getIntrestRate() {
	return _intrestRate;
    }
}