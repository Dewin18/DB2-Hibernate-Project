package uiTools.agentPasswordPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;

import javax.swing.*;

import org.hibernate.Session;

import main.HibernateConnector;
import materials.EstateAgent;
import uiTools.Observable;

public class AgentPasswordPanelTool extends Observable {

    private AgentPasswordPanelUI _agentPasswordPanelUI;
    private HibernateConnector _hibernateConnection;

    private JTextField _loginField;
    private JPasswordField _passwordField;
    private JLabel _invalidPasswordLabel;
    private JPanel _agentLoginPanel;
    private EstateAgent _loggedInAgent;
    private boolean _state;

    public AgentPasswordPanelTool(HibernateConnector hibernateConnection) {
	_agentPasswordPanelUI = new AgentPasswordPanelUI();
	_hibernateConnection = hibernateConnection;

	initComponents();
	registerListener();
    }

    private void initComponents() {
	_loginField = _agentPasswordPanelUI.getLoginField();
	_passwordField = _agentPasswordPanelUI.getPasswordField();
	_invalidPasswordLabel = _agentPasswordPanelUI.getInvalidPasswordLabel();
	_agentLoginPanel = _agentPasswordPanelUI.getLoginPanel();
	_state = false;
    }

    private void registerListener() {
	loginButtonListener();
	txtFieldListener();
    }

    private void loginButtonListener() {
	_agentPasswordPanelUI.getLoginButton().addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		checkLogin();
	    }
	});
    }

    private void txtFieldListener() {
	JTextField[] allTextFields = new JTextField[] { _loginField, _passwordField };

	for (JTextField jTextField : allTextFields) {
	    jTextField.addFocusListener(new FocusListener() {

		@Override
		public void focusLost(FocusEvent e) {
		}

		@Override
		public void focusGained(FocusEvent e) {
		    resetInvalidPasswordMessage();
		}
	    });
	}
    }

    private void checkLogin() {

	String password = new String(_passwordField.getPassword());
	String loginName = _loginField.getText();

	Session session = _hibernateConnection.getNewSession();

	session.beginTransaction();
	@SuppressWarnings("unchecked")
	List<EstateAgent> agents =
	session.createQuery("from EstateAgent a "
			  + "where a._login = '" + loginName + "' "
			  + "and a._password = '" + password + "'")
			  .getResultList();
	
	session.getTransaction().commit();
	session.close();

	if (!agents.isEmpty()) {
	    _loggedInAgent = agents.get(0);
	    setActive();
	    notifyObserver();
	} else
	    _invalidPasswordLabel.setText("Invalid user");
    }

    public EstateAgent getLoggedInAgent() {
	return _loggedInAgent;
    }

    public void resetInvalidPasswordMessage() {
	_invalidPasswordLabel.setText(" ");
    }

    public JTextField getLoginField() {
	return _loginField;
    }

    public JPasswordField getPasswordField() {
	return _passwordField;
    }

    public JPanel getLoginPanel() {
	return _agentLoginPanel;
    }

    public boolean isActive() {
	return _state;
    }

    public void setActive() {
	_state = true;
    }

    public void setInactive() {
	_state = false;
    }
}
