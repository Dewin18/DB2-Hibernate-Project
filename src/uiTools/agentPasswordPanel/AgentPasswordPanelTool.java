package uiTools.agentPasswordPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.*;
import javax.swing.*;

import main.DB2ConnectionManager;
import uiTools.Observable;

public class AgentPasswordPanelTool extends Observable {

    private AgentPasswordPanelUI _agentPasswordPanelUI;

    private JTextField _loginField;
    private JPasswordField _passwordField;
    private JLabel _invalidPasswordLabel;
    private JPanel _agentLoginPanel;
    private String _loginName;
    private boolean _state;

    public AgentPasswordPanelTool() {
	_agentPasswordPanelUI = new AgentPasswordPanelUI();

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

	try {
	    Connection connection = DB2ConnectionManager.getInstance().getConnection();

	    String agentPassword = new String(_passwordField.getPassword());
	    _loginName = _loginField.getText();

	    String sqlSelectStatement = "SELECT * FROM estate_agent WHERE login = '" + _loginName + "' AND password ='"
		    + agentPassword + "'";

	    Statement stmt = connection.createStatement();
	    ResultSet rs = stmt.executeQuery(sqlSelectStatement);

	    if (rs.next()) {
		setActive();
		notifyObserver();
	    } else {
		_invalidPasswordLabel.setText("Invalid user");
	    }

	} catch (SQLException e) {
	    e.printStackTrace();
	}

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

    public String getLoginName() {
	return _loginName;
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
