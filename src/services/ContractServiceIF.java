package services;

import java.sql.ResultSet;
import java.util.List;

import materials.Contract;
import materials.Person;
import materials.PurchaseContract;
import materials.TenancyContract;

public interface ContractServiceIF {
    
    public boolean makeContract(Contract contract);
    
    public int getContractID(int contractNo);
    
    public boolean makePurchaseContract(PurchaseContract purchaseContract);
    
    public boolean makeTenancyContract(TenancyContract tenancyContract);
    
    public ResultSet getContractSet();
    
    public List<Contract> getContractList();
    
    public int getNumberOfContracts();
    
    public boolean personExist(String firstName, String name, String address);
    
    public boolean insertPerson(Person person);
    
    public boolean entryExist(String table, String column, String entry);
}
