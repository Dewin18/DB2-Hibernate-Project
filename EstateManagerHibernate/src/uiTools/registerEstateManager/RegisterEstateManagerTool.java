package uiTools.registerEstateManager;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import materials.EstateAgent;
import services.RegisterServiceIF;

public class RegisterEstateManagerTool {

    private JRadioButton _create;
    private JRadioButton _delete;
    private JRadioButton _change;

    private JTextField _nameField;
    private JTextField _addressField;
    private JTextField _loginField;
    private JTextField _passwordField;

    private JButton _submitButton;
    private JButton _resetButton;

    private JLabel _messageLabel;

    private RegisterEstateManagerUI _registerEstateManagerUI;
    private RegisterServiceIF _registerSerivce;

    public RegisterEstateManagerTool(RegisterServiceIF registerSerivce) {
	_registerEstateManagerUI = new RegisterEstateManagerUI();
	_registerSerivce = registerSerivce;
	
	initUIComponents();
	registerListener();
    }

    private void initUIComponents() {
	initRadioButtons();
	initTxtFields();
	initButtons();
	initLabels();
    }

    private void initRadioButtons() {
	_create = _registerEstateManagerUI.getCreateButton();
	_delete = _registerEstateManagerUI.getDeleteButton();
	_change = _registerEstateManagerUI.getChangeButton();
    }

    private void initTxtFields() {
	_nameField = _registerEstateManagerUI.getNameField();
	_addressField = _registerEstateManagerUI.getAdressField();
	_loginField = _registerEstateManagerUI.getLoginField();
	_passwordField = _registerEstateManagerUI.getPasswordField();
    }

    private void initButtons() {
	_submitButton = _registerEstateManagerUI.getSubmitButton();
	_resetButton = _registerEstateManagerUI.getResetButton();
    }

    private void initLabels() {
	_messageLabel = _registerEstateManagerUI.getMessageLabel();
    }

    private void registerListener() {
	radioButtonListener();
	submitButtonListener();
	resetButtonListener();
	txtFieldFocusListener();
    }

    private void radioButtonListener() {
	_create.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		_submitButton.setText("sign up");

		_nameField.setEditable(true);
		_addressField.setEditable(true);
		_passwordField.setEditable(true);

		_nameField.setBackground(Color.WHITE);
		_addressField.setBackground(Color.WHITE);
		_passwordField.setBackground(Color.WHITE);
	    }
	});

	_delete.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		_submitButton.setText("delete");

		_nameField.setEditable(false);
		_addressField.setEditable(false);
		_passwordField.setEditable(false);

		_nameField.setText("");
		_addressField.setText("");
		_passwordField.setText("");

		_nameField.setBackground(Color.LIGHT_GRAY);
		_addressField.setBackground(Color.LIGHT_GRAY);
		_passwordField.setBackground(Color.LIGHT_GRAY);
	    }
	});

	_change.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		_submitButton.setText("change");

		_nameField.setEditable(true);
		_addressField.setEditable(true);
		_passwordField.setEditable(true);

		_nameField.setBackground(Color.WHITE);
		_addressField.setBackground(Color.WHITE);
		_passwordField.setBackground(Color.WHITE);
	    }
	});
    }

    private void submitButtonListener() {
	_submitButton.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {

		String currentMode = _submitButton.getText();

		if (currentMode.equals("sign up")) {
		    handleSubmit();
		} else if (currentMode.equals("delete")) {
		    handleDelete();
		} else {
		    handleUpdate();
		}
	    }
	});
    }

    private void handleSubmit() {
	if (allTextFieldsNotNull()) {
	    String name = _nameField.getText();
	    String address = _addressField.getText();
	    String login = _loginField.getText();
	    String password = _passwordField.getText();
	    
	    EstateAgent newEstateAgent = new EstateAgent(name, address, login, password);
	    _registerSerivce.registerEstateAgent(newEstateAgent);
	    newEstateAgent = null;
	    resetAllTxtFields();
	} 
    }

    private void handleDelete() {
	if (loginNotNull()) {
	    String loginName = _loginField.getText();
	    boolean deleted = _registerSerivce.deleteEstateAgent(loginName);
	    
	    if(deleted) {
		System.out.println("DELETED");
	    }
	    else {
		System.out.println("NOT DELETED");
	    }
	    resetAllTxtFields();
	} 
    }

    private void handleUpdate() {
	
	if (allTextFieldsNotNull()) {
	    
	    String name = _nameField.getText();
	    String address = _addressField.getText();
	    String login = _loginField.getText();
	    String password = _passwordField.getText();
	    
	    _registerSerivce.updateEstateAgent(name, address, login, password);
	    resetAllTxtFields();
	}
    }

    private boolean allTextFieldsNotNull() {
	JTextField[] allFields = _registerEstateManagerUI.getAllTextFields();

	for (JTextField jTextField : allFields) {
	    if (jTextField.isEnabled() && (jTextField.getText().length() < 1))
		return false;
	}
	return true;
    }

    private boolean loginNotNull() {
	return !_loginField.getText().isEmpty();
    }

    private void resetButtonListener() {
	_resetButton.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		resetAllTxtFields();
	    }
	});
    }

    private void txtFieldFocusListener() {
	JTextField[] allFields = _registerEstateManagerUI.getAllTextFields();

	for (JTextField jTextField : allFields) {
	    jTextField.addFocusListener(new FocusListener() {

		@Override
		public void focusLost(FocusEvent e) {
		}

		@Override
		public void focusGained(FocusEvent e) {
		    _messageLabel.setText(" ");
		}
	    });
	}
    }

    private void resetAllTxtFields() {
	JTextField[] allFields = _registerEstateManagerUI.getAllTextFields();

	for (JTextField jTextField : allFields) {
	    jTextField.setText("");
	}
    }

    public JPanel getRegisterPanel() {
	return _registerEstateManagerUI.getRegisterPanel();
    }

    public void resetAllComponents() {
	resetAllTxtFields();
	_create.setSelected(true);
	_submitButton.setText("sign up");
	_messageLabel.setText(" ");
	
	JTextField[] allFields = _registerEstateManagerUI.getAllTextFields();
	
	for (JTextField jTextField : allFields) {
	    jTextField.setEditable(true);
	    jTextField.setBackground(Color.WHITE);
	}
    }
}
