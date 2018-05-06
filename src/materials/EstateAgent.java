package materials;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="estate_agent")
public class EstateAgent {
    

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_estate_agent")
    private int id;
    
    @Column(name="name")
    private String _name;
    
    @Column(name="address")
    private String _address;

    @Column(name="login", unique = true)
    private String _login;

    @Column(name="password")
    private String _password;
    
    public EstateAgent() {
    }
    
    public EstateAgent(String name, String address, String login, String password) {
	_name = name;
	_address = address;
	_login = login;
	_password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public String getAddress() {
        return _address;
    }

    public void setAddress(String address) {
        _address = address;
    }

    public String getLogin() {
        return _login;
    }

    public void setLogin(String login) {
        _login = login;
    }

    public String getPassword() {
        return _password;
    }

    public void setPassword(String password) {
        _password = password;
    }

    @Override
    public String toString() {
	return "EstateAgent [id=" + id + ", _name=" + _name + ", _address=" + _address + ", _login=" + _login
		+ ", _password=" + _password + "]";
    }
    
}
