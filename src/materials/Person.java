package materials;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "person")
public class Person {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_person")
    private int _personID;
    
    @Column(name = "first_name")
    private String _firstName;
    
    @Column(name = "name")
    private String _name;
    
    @Column(name = "address")
    private String _address;
    
    public Person() {
    }
    
    public Person(String firstName, String name, String address) {
	_firstName = firstName;
	_name = name;
	_address = address;
    }
    
    public int getPersonID() {
	return _personID;
    }
    
    public void setPersonID(int personID) {
	_personID = personID;
    }
    
    public String getFirstName() {
	return _firstName;
    }
    
    public void setFirstName(String firstName) {
	_firstName = firstName;
    }
    
    public String getLastName() {
	return _name;
    }
    
    public void setLastName(String name) {
	_name = name;
    }
    
    public String getAddress() {
	return _address;
    }
    
    public void setAddress(String address) {
	_address = address;
    }
}
