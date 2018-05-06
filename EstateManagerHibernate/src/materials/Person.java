package materials;

public class Person {
    private String _firstName;
    private String _name;
    private String _address;
    
    public Person(String firstName, String name, String address) {
	_firstName = firstName;
	_name = name;
	_address = address;
    }
    
    public String getFirstName() {
	return _firstName;
    }
    
    public String getLastName() {
	return _name;
    }
    
    public String getAddress() {
	return _address;
    }
}
