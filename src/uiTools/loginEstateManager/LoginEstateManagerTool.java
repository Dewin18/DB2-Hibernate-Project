package uiTools.loginEstateManager;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.*;
import materials.Apartment;
import materials.Estate;
import materials.EstateAgent;
import materials.House;
import services.EstateServiceIF;

public class LoginEstateManagerTool {

    private JComboBox<String> _comboBox;

    private JLabel _messageLabel;

    private JRadioButton _create;
    private JRadioButton _delete;
    private JRadioButton _change;

    private JTextField _cityField;
    private JTextField _postalCodeField;
    private JTextField _streetField;
    private JTextField _streetNumberField;
    private JTextField _squareAreaField;

    private JTextField _floorsField;
    private JTextField _priceField;
    private JTextField _gardenField;

    private JTextField _floorField;
    private JTextField _rentField;
    private JTextField _roomsField;
    private JTextField _balconyField;
    private JTextField _kitchenField;

    private JTextField _estateIDField;

    private JButton _submitButton;
    private JButton _resetButton;

    private LoginEstateManagerUI _loginEstateManagerUI;
    private EstateServiceIF _estateService;

    private EstateAgent _loggedInAgent;

    public LoginEstateManagerTool(EstateServiceIF estateServiceImpl) {
	_loginEstateManagerUI = new LoginEstateManagerUI();
	_estateService = estateServiceImpl;

	initComponents();
	registerListener();
    }

    private void initComponents() {
	_comboBox = _loginEstateManagerUI.getComboBox();
	_submitButton = _loginEstateManagerUI.getSubmitButton();
	_resetButton = _loginEstateManagerUI.getResetButton();
	_create = _loginEstateManagerUI.getCreateButton();
	_delete = _loginEstateManagerUI.getDeleteButton();
	_change = _loginEstateManagerUI.getChangeButton();
	_estateIDField = _loginEstateManagerUI.getEstateIDField();
	_messageLabel = _loginEstateManagerUI.getMessageLabel();

	// general fields init
	_cityField = _loginEstateManagerUI.getCityField();
	_postalCodeField = _loginEstateManagerUI.getPostalCodeField();
	_streetField = _loginEstateManagerUI.getStreetField();
	_streetNumberField = _loginEstateManagerUI.getStreetNumberField();
	_squareAreaField = _loginEstateManagerUI.getSquareAreaField();

	// house fields init
	_floorsField = _loginEstateManagerUI.getFloorsField();
	_priceField = _loginEstateManagerUI.getPriceField();
	_gardenField = _loginEstateManagerUI.getGardenField();

	// apartment fields init
	_floorField = _loginEstateManagerUI.getFloorField();
	_rentField = _loginEstateManagerUI.getRentField();
	_roomsField = _loginEstateManagerUI.getRoomsField();
	_balconyField = _loginEstateManagerUI.getBalconyField();
	_kitchenField = _loginEstateManagerUI.getKitchenField();
    }

    private void registerListener() {
	comboBoxListener();
	radioButtonListener();
	submitButtonListener();
	resetButtonListener();
	txtFieldListener();
    }

    private void comboBoxListener() {

	_comboBox.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		if (_comboBox.getSelectedIndex() == 0) {
		    _loginEstateManagerUI.changeApartmentToHousePanel();
		} else {
		    _loginEstateManagerUI.changeHouseToApartmentPanel();
		}
	    }
	});
    }

    private void radioButtonListener() {
	_create.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		_submitButton.setText("register");
		_estateIDField.setText("");
		setTextFieldsEditable(true);
		disableEstateIDField();
	    }
	});

	_delete.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		_submitButton.setText("delete");
		setTextFieldsEditable(false);
		enableEstateIDField();
	    }
	});

	_change.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		_submitButton.setText("change");
		_estateIDField.setText("");
		setTextFieldsEditable(true);
		enableEstateIDField();
	    }
	});
    }

    private void submitButtonListener() {
	_submitButton.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		if (_comboBox.getSelectedItem().equals("House")) {
		    handleHouseOperations();
		} else if (_comboBox.getSelectedItem().equals("Apartment")) {
		    handleApartmentOperations();
		}
	    }
	});
    }

    private void resetButtonListener() {
	_resetButton.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		resetAllTxtFields();
	    }
	});
    }

    private void txtFieldListener() {
	JTextField[] allFields = _loginEstateManagerUI.getAllTextFields();

	for (JTextField jTextField : allFields) {
	    jTextField.addFocusListener(new FocusAdapter() {
		@Override
		public void focusGained(FocusEvent e) {
		    _messageLabel.setText(" ");
		}
	    });
	}
    }

    private void handleHouseOperations() {
	String currentMode = _submitButton.getText();

	if (currentMode.equals("register")) {
	    handleHouseSubmit();
	} else if (currentMode.equals("delete")) {
	    handleDelete();
	} else {
	    handleHouseUpdate();
	}
    }

    private void handleHouseSubmit() {

	if (inputNotNull(getEstateInputs()) && inputNotNull(getHouseInputs())) {

	    int estateAgentID = _loggedInAgent.getId();

	    String city = _cityField.getText();
	    String postalCode = _postalCodeField.getText();
	    String street = _streetField.getText();
	    String number = _streetNumberField.getText();
	    String squareArea = _squareAreaField.getText();

	    int floors = Integer.parseInt(_floorsField.getText());
	    double price = Double.parseDouble(_priceField.getText());
	    int garden = Integer.parseInt(_gardenField.getText());

	    boolean estateAndHouseIsManaged = _estateService.estateAndHouseIsManaged(estateAgentID, city, postalCode,
		    street, number, squareArea, floors, price, garden);

	    if (!estateAndHouseIsManaged) {

		Estate newEstate = new Estate(estateAgentID, city, postalCode, street, number, squareArea);
		_estateService.insertNewEstate(newEstate);

		int estateID = newEstate.getEstateID();

		House newHouse = new House(estateID, floors, price, garden);
		_estateService.insertNewHouse(newHouse);
	    }
	}
    }

    private void handleHouseUpdate() {

	if (inputNotNull(getEstateInputs()) && inputNotNull(getHouseInputs())) {

	    String[] estateInput = getEstateInputs();
	    String[] houseInput = getHouseInputs();
	    String estateID = _estateIDField.getText();

	    _estateService.updateEstate(estateID, estateInput);
	    _estateService.updateHouse(estateID, houseInput);
	}
    }

    private void handleApartmentOperations() {
	String currentMode = _submitButton.getText();

	if (currentMode.equals("register")) {
	    handleApartmentSubmit();
	} else if (currentMode.equals("delete")) {
	    handleDelete();
	} else {
	    handleApartmentUpdate();
	}
    }

    private void handleApartmentSubmit() {

	if (inputNotNull(getEstateInputs()) && inputNotNull(getApartmentInputs())) {

	    int estateAgentID = _loggedInAgent.getId();

	    String city = _cityField.getText();
	    String postalCode = _postalCodeField.getText();
	    String street = _streetField.getText();
	    String number = _streetNumberField.getText();
	    String squareArea = _squareAreaField.getText();

	    int floor = Integer.parseInt(_floorField.getText());
	    double rent = Double.parseDouble(_rentField.getText());
	    int rooms = Integer.parseInt(_roomsField.getText());
	    int balcony = Integer.parseInt(_balconyField.getText());
	    int kitchen = Integer.parseInt(_kitchenField.getText());

	    boolean estateAndApartmentIsManaged = _estateService.estateAndApartmentIsManaged(estateAgentID, city,
		    postalCode, street, number, squareArea, floor, rent, rooms, balcony, kitchen);

	    if (!estateAndApartmentIsManaged) {
		Estate newEstate = new Estate(estateAgentID, city, postalCode, street, number, squareArea);
		_estateService.insertNewEstate(newEstate);
		int estateID = newEstate.getEstateID();
		Apartment newApartment = new Apartment(estateID, floor, rent, rooms, balcony, kitchen);
		_estateService.insertNewApartment(newApartment);
	    }
	}
    }

    private void handleApartmentUpdate() {

	if (inputNotNull(getEstateInputs()) && inputNotNull(getApartmentInputs())) {

	    String[] estateInput = getEstateInputs();
	    String[] apartmentInput = getApartmentInputs();
	    String estateID = _estateIDField.getText();

	    _estateService.updateEstate(estateID, estateInput);
	    _estateService.updateApartment(estateID, apartmentInput);
	}
    }

    private void handleDelete() {

	if (estateIDFieldNotNull()) {

	    String estateID = _estateIDField.getText();
	    boolean estateAgentManageEstate = _estateService.estateAgentManagesEstate(_loggedInAgent, estateID);

	    if (estateAgentManageEstate) {
		_estateService.deleteEstate(estateID);
	    }
	}
    }

    private boolean estateIDFieldNotNull() {
	return !_estateIDField.getText().isEmpty();
    }

    public JPanel getEstatePanel() {
	return _loginEstateManagerUI.getLoginPanel();
    }

    public void setEstateAgent(EstateAgent loggedInAgent) {
	_loggedInAgent = loggedInAgent;
    }

    private void setTextFieldsEditable(boolean editable) {

	JTextField[] allFields = _loginEstateManagerUI.getAllTextFieldsWithoutIDField();

	for (JTextField jTextField : allFields) {
	    if (editable) {
		jTextField.setEditable(true);
		jTextField.setBackground(Color.WHITE);
	    } else {
		jTextField.setEditable(false);
		jTextField.setBackground(Color.LIGHT_GRAY);
		jTextField.setText("");
	    }
	}
    }

    private void enableEstateIDField() {
	_estateIDField.setEditable(true);
	_estateIDField.setBackground(Color.WHITE);
    }

    private void disableEstateIDField() {
	_estateIDField.setEditable(false);
	_estateIDField.setBackground(Color.LIGHT_GRAY);
    }

    private boolean inputNotNull(String[] inputs) {
	for (String string : inputs) {
	    if(string.equals(""))
		return false;
	}
	return true;
    }

    private void resetAllTxtFields() {
	JTextField[] allFields = _loginEstateManagerUI.getAllTextFields();

	for (JTextField jTextField : allFields) {
	    jTextField.setText("");
	}
    }

    public void resetAllComponents() {
	resetAllTxtFields();
	_create.setSelected(true);
	_submitButton.setText("register");
	setTextFieldsEditable(true);
	disableEstateIDField();
	_comboBox.setSelectedIndex(0);
	_loginEstateManagerUI.changeApartmentToHousePanel();
    }

    public String[] getEstateInputs() {
	return new String[] {_cityField.getText(), 
			     _postalCodeField.getText(), 
			     _streetField.getText(),
			     _streetNumberField.getText(), 
			     _squareAreaField.getText()};
    }

    public String[] getHouseInputs() {
	return new String[] {_floorsField.getText(), 
			     _priceField.getText(), 
			     _gardenField.getText()};
    }

    public String[] getApartmentInputs() {
	return new String[] {_floorField.getText(), 
			     _rentField.getText(), 
			     _roomsField.getText(),
			     _balconyField.getText(), 
			     _kitchenField.getText()};
    }
    
    // private void setMessageText(Color color, String text) {
    // _messageLabel.setForeground(color);
    // _messageLabel.setText(text);
    // }
}
