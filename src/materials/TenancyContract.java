package materials;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tenancy_contract")
public class TenancyContract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tenancy_contract")
    private int _tenancyContractID;

    @Column(name = "fk_id_person")
    private int _personID;

    @Column(name = "fk_id_apartment")
    private int _apartmentID;

    @Column(name = "fk_id_contract")
    private int _contractID;

    @Column(name = "start_date")
    private Date _startDate;

    @Column(name = "duration_days")
    private int _duration;

    @Column(name = "additional_costs")
    private int _additionalCosts;

    public TenancyContract() {
    }

    public TenancyContract(int personID, int apartmentID, int contractID, Date startDate, int duration,
	    int additionalCosts) {
	_personID = personID;
	_apartmentID = apartmentID;
	_contractID = contractID;
	_startDate = startDate;
	_duration = duration;
	_additionalCosts = additionalCosts;
    }

    public int getTenancyContractID() {
	return _tenancyContractID;
    }

    public void setTenancyContractID(int tenancyContractID) {
	_tenancyContractID = tenancyContractID;
    }

    public int getPersonID() {
	return _personID;
    }

    public void setPersonID(int personID) {
	_personID = personID;
    }

    public int getApartmentID() {
	return _apartmentID;
    }

    public void setApartmentID(int apartmentID) {
	_apartmentID = apartmentID;
    }

    public int getContractID() {
	return _contractID;
    }

    public void setContractID(int contractID) {
	_contractID = contractID;
    }

    public Date getStartDate() {
	return _startDate;
    }

    public void setStartDate(Date startDate) {
	_startDate = startDate;
    }

    public int getDuarion() {
	return _duration;
    }

    public void setDuration(int duration) {
	_duration = duration;
    }

    public int getAdditionalCosts() {
	return _additionalCosts;
    }

    public void setAdditionalCosts(int additionalCosts) {
	_additionalCosts = additionalCosts;
    }

    @Override
    public String toString() {
	return "TenancyContract [_tenancyContractID=" + _tenancyContractID + ", _personID=" + _personID
		+ ", _apartmentID=" + _apartmentID + ", _contractID=" + _contractID + ", _startDate=" + _startDate
		+ ", _duration=" + _duration + ", _additionalCosts=" + _additionalCosts + "]";
    }

}
