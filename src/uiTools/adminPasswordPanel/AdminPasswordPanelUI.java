package uiTools.adminPasswordPanel;

import java.awt.*;
import javax.swing.*;

public class AdminPasswordPanelUI {

    private JPasswordField _passwordField;

    private JPanel _loginPanel;
    private JPanel _accessPanel;
    
    private JLabel _invalidPasswort;
    private JLabel _passwordLabel;

    private JButton _loginButton;

    public AdminPasswordPanelUI() {
	initPanels();
	initLabels();
	initLoginButton();
	initPasswordField();
	combineComponents();
    }

    private void initLabels() {
	_passwordLabel = new JLabel("Enter admin password");
	
	_invalidPasswort = new JLabel(" ");
	_invalidPasswort.setFont(new Font("SansSerif", Font.BOLD, 15));
	_invalidPasswort.setForeground(Color.RED);
    }

    private void initLoginButton() {
	_loginButton = new JButton("verify");
    }

    private void initPasswordField() {
	_passwordField = new JPasswordField(15);
	_passwordField.setMaximumSize(new Dimension(270, 26));
    }

    private void initPanels() {
	_accessPanel = new JPanel();
	
	_loginPanel = new JPanel();
	_loginPanel.setLayout(new BoxLayout(_loginPanel, BoxLayout.Y_AXIS));
	_loginPanel.setPreferredSize(new Dimension(150, 200));
    }

    private void combineComponents() {
	
	_loginPanel.add(Box.createVerticalStrut(100));
	_loginPanel.add(_passwordLabel);
	_loginPanel.add(_passwordField);
	_loginPanel.add(_loginButton);
	_loginPanel.add(_invalidPasswort);
	
	_accessPanel.add(_loginPanel);
    }

    public JPanel getLoginPanel() {
	return _accessPanel;
    }

    public JButton getLoginButton() {
	return _loginButton;
    }

    public JPasswordField getPasswordField() {
	return _passwordField;
    }
    
    public JLabel getInvalidPasswordLabel() {
	return _invalidPasswort;
    }
}
