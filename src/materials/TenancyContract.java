package materials;

import java.sql.Date;

public class TenancyContract {
    private int _personID;
    private int _apartmentID;
    private int _contractID;
    private Date _startDate;
    private int _duration;
    private int _additionalCosts;

    public TenancyContract(int personID, int apartmentID, int contractID, Date startDate, int duration,
	    int additionalCosts) {
	_personID = personID;
	_apartmentID = apartmentID;
	_contractID = contractID;
	_startDate = startDate;
	_duration = duration;
	_additionalCosts = additionalCosts;
    }

    public int getPersonID() {
	return _personID;
    }

    public int getApartmentID() {
	return _apartmentID;
    }

    public int getContractID() {
	return _contractID;
    }

    public Date getStartDate() {
	return _startDate;
    }

    public int getDuarion() {
	return _duration;
    }

    public int getAdditionalCosts() {
	return _additionalCosts;
    }
}
