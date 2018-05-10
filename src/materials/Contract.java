package materials;

import java.sql.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contract")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contract")
    private int _idContract;

    @Column(name = "contract_no", unique = true)
    private int _contractNumber;

    @Column(name = "date")
    private Date _date;

    @Column(name = "place")
    private String _place;

    public Contract() {
    }

    public Contract(int contractNumber, Date date, String place) {
	_contractNumber = contractNumber;
	_date = date;
	_place = place;
    }

    public Contract(int idContract, int contractNumber, Date date, String place) {
	_idContract = idContract;
	_contractNumber = contractNumber;
	_date = date;
	_place = place;
    }

    public int getContractID() {
	return _idContract;
    }

    public void setContractID(int idContract) {
	_idContract = idContract;
    }

    public int getContractNumber() {
	return _contractNumber;
    }

    public void setContractNumber(int contractNumber) {
	_contractNumber = contractNumber;
    }

    public Date getDate() {
	return _date;
    }

    public void setDate(Date date) {
	_date = date;
    }

    public String getPlace() {
	return _place;
    }

    public void setPlace(String place) {
	_place = place;
    }

    @Override
    public String toString() {
	return "Contract [_idContract=" + _idContract + ", _contractNumber=" + _contractNumber + ", _date=" + _date
		+ ", _place=" + _place + "]";
    }
}
