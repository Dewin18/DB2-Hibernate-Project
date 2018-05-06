package uiTools.adminPasswordPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import uiTools.Observable;

public class AdminPasswordPanelTool extends Observable {

    private final String _adminPassword = "123456";
    private AdminPasswordPanelUI _passwordPanelUI;
    private JPasswordField _passwordField;
    private JLabel _invalidPasswordLabel;
    private JPanel _loginPanel;
    private boolean _state;

    public AdminPasswordPanelTool() {
	_passwordPanelUI = new AdminPasswordPanelUI();

	initComponents();
	registerListener();
    }

    private void initComponents() {
	_loginPanel = _passwordPanelUI.getLoginPanel();
	_passwordField = _passwordPanelUI.getPasswordField();
	_invalidPasswordLabel = _passwordPanelUI.getInvalidPasswordLabel();
	_state = false;
    }

    private void registerListener() {
	loginButtonListener();
	fieldListener();
    }

    private void loginButtonListener() {
	_passwordPanelUI.getLoginButton().addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		String passText = new String(_passwordField.getPassword());

		if (passText.equals(_adminPassword)) {
		    setActive();
		    notifyObserver();
		    resetInvalidPasswordMessage();
		} else {
		    _invalidPasswordLabel.setText("Invalid password");
		}

	    }
	});
    }

    private void fieldListener() {
	_passwordField.addFocusListener(new FocusListener() {
	    @Override
	    public void focusLost(FocusEvent e) {
	    }

	    @Override
	    public void focusGained(FocusEvent e) {
		resetInvalidPasswordMessage();
	    }
	});
    }

    public void resetInvalidPasswordMessage() {
	_invalidPasswordLabel.setText(" ");
    }

    public JPanel getLoginPanel() {
	return _loginPanel;
    }

    public JPasswordField getPasswordField() {
	return _passwordField;
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
