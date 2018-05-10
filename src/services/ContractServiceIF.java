package services;

import java.util.List;

import materials.Contract;
import materials.Person;
import materials.PurchaseContract;
import materials.TenancyContract;

public interface ContractServiceIF {
    
    public List<Contract> getContractList();
    
    // inserts
    public boolean insertContract(Contract contract);
    
    public boolean insertPurchaseContract(PurchaseContract purchaseContract);
    
    public boolean insertTenancyContract(TenancyContract tenancyContract);
    
    public boolean insertPerson(Person person);
    
    // queries
    public boolean personExist(String firstName, String name, String address);
    
    public boolean personExist(String personID);
    
    public boolean contractExist(String contractID);
    
    public boolean houseExist(String houseID);
    
    public boolean apartmentExist(String apartmentID);
}
