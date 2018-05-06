package materials;

import java.sql.*;

public class Contract {
    private int _idContract;
    private int _contractNumber;
    private Date _date;
    private String _place;
    
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

    public int getIDContract() {
	return _idContract;
    }
    
    public int getContractNumber() {
	return _contractNumber;
    }
    
    public Date getDate() {
	return _date;
    }
    
    public String getPlace() {
	return _place;
    }
}
