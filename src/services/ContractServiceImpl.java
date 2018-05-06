package services;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import main.DB2ConnectionManager;
import materials.Contract;
import materials.Person;
import materials.PurchaseContract;
import materials.TenancyContract;

public class ContractServiceImpl implements ContractServiceIF {

    private Connection connection;
    private Statement stmt;

    public ContractServiceImpl() {
	try {
	    connection = DB2ConnectionManager.getInstance().getConnection();
	    stmt = connection.createStatement();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    @Override
    public boolean makeContract(Contract contract) {
	try {
	    String sqlMakeContract = "INSERT INTO contract(contract_no, date, place) " + "VALUES (?, ?, ?)";

	    PreparedStatement prepStmt = connection.prepareStatement(sqlMakeContract);

	    prepStmt.setInt(1, contract.getContractNumber());
	    prepStmt.setDate(2, contract.getDate());
	    prepStmt.setString(3, contract.getPlace());
	    prepStmt.executeUpdate();
	    return true;
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return false;
    }

    @Override
    public boolean makePurchaseContract(PurchaseContract purchaseContract) {

	try {
	    String sqlMakePurchaseContract = "INSERT INTO purchase_contract(fk_id_person, fk_id_house, fk_id_contract, no_of_installments, intrest_rate) "
		    + "VALUES(?, ?, ?, ? ,?)";

	    PreparedStatement purchaseStmt = connection.prepareStatement(sqlMakePurchaseContract);

	    purchaseStmt.setInt(1, purchaseContract.getPersonID());
	    purchaseStmt.setInt(2, purchaseContract.getHouseID());
	    purchaseStmt.setInt(3, purchaseContract.getContractID());
	    purchaseStmt.setInt(4, purchaseContract.getNumberOfInstallments());
	    purchaseStmt.setInt(5, purchaseContract.getIntrestRate());
	    purchaseStmt.executeUpdate();
	    return true;
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return false;
    }

    @Override
    public boolean makeTenancyContract(TenancyContract tenancyContract) {

	try {
	    String sqlMakeTenancyContract = "INSERT INTO tenancy_contract(fk_id_person, fk_id_apartment, fk_id_contract, start_date, duration_days, additional_costs) "
		    + "VALUES(?, ?, ?, ? ,?, ?)";

	    PreparedStatement purchaseStmt = connection.prepareStatement(sqlMakeTenancyContract);

	    purchaseStmt.setInt(1, tenancyContract.getPersonID());
	    purchaseStmt.setInt(2, tenancyContract.getApartmentID());
	    purchaseStmt.setInt(3, tenancyContract.getContractID());
	    purchaseStmt.setDate(4, tenancyContract.getStartDate());
	    purchaseStmt.setInt(5, tenancyContract.getDuarion());
	    purchaseStmt.setInt(6, tenancyContract.getAdditionalCosts());
	    purchaseStmt.executeUpdate();
	    return true;
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return false;
    }

    @Override
    public int getNumberOfContracts() {
	try {
	    String sqlCountContracts = "SELECT COUNT(*) " + "FROM contract";

	    ResultSet resultSet = stmt.executeQuery(sqlCountContracts);
	    resultSet.next();
	    return resultSet.getInt(1);

	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return -1;
    }

    @Override
    public ResultSet getContractSet() {
	try {
	    String sqlSelectContracts = "SELECT * " + "FROM contract";

	    ResultSet resultContracts = stmt.executeQuery(sqlSelectContracts);
	    return resultContracts;
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return null;
    }

    @Override
    public List<Contract> getContractList() {
	try {
	    List<Contract> contracts = new ArrayList<>();

	    String sqlSelectContracts = "SELECT * " + "FROM contract";

	    ResultSet resultContracts = stmt.executeQuery(sqlSelectContracts);

	    while (resultContracts.next()) {

		int idContract = resultContracts.getInt("id_contract");
		int contractNo = resultContracts.getInt("contract_no");
		Date date = resultContracts.getDate("date");
		String place = resultContracts.getString("place");

		contracts.add(new Contract(idContract, contractNo, date, place));
	    }
	    return contracts;
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return null;
    }

    @Override
    public boolean personExist(String firstName, String name, String address) {
	try {
	    String sqlSelectPerson = "SELECT * " + "FROM person " + "WHERE first_name ='" + firstName + "' "
		    + "AND name ='" + name + "' " + "AND address ='" + address + "'";

	    ResultSet resultPerson = stmt.executeQuery(sqlSelectPerson);
	    if (resultPerson.next())
		return true;
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return false;
    }

    @Override
    public boolean insertPerson(Person person) {
	try {
	    String sqlInsertPerson = "INSERT INTO person(first_name, name, address) " + "VALUES (?, ?, ?)";

	    PreparedStatement prepStmt = connection.prepareStatement(sqlInsertPerson);

	    prepStmt.setString(1, person.getFirstName());
	    prepStmt.setString(2, person.getLastName());
	    prepStmt.setString(3, person.getAddress());
	    prepStmt.executeUpdate();
	    return true;
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return false;
    }

    @Override
    public boolean entryExist(String table, String column, String entry) {
	try {
	    String sqlSelectContract = "SELECT * " + "FROM " + table + " " + "WHERE " + column + " = '" + entry + "'";

	    ResultSet resultSet = stmt.executeQuery(sqlSelectContract);
	    boolean entryExist = resultSet.next() ? true : false;
	    return entryExist;
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return false;
    }

    @Override
    public int getContractID(int contractNo) {
	try {
	    String sqlSelectContract = "SELECT * " + "FROM contract " + "WHERE contract_no = '" + contractNo + "'";

	    ResultSet resultSet = stmt.executeQuery(sqlSelectContract);

	    if (resultSet.next())
		return resultSet.getInt("id_contract");
	    return -1;

	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return -1;
    }
}
