package uiTools.loginEstateManager;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.*;
import materials.Apartment;
import materials.Estate;
import materials.House;
import services.EstateServiceIF;

public class LoginEstateManagerTool {

    private String _loginName;

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
    private JTextField[] _generalFields;
    private JTextField[] _generalAndHouseFields;
    private JTextField[] _generalAndApartmentFields;

    private JButton _submitButton;
    private JButton _resetButton;

    private LoginEstateManagerUI _loginEstateManagerUI;
    private EstateServiceIF _estateService;

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
	_generalAndHouseFields = _loginEstateManagerUI.getGeneralAndHouseSpecificTextFields();
	_generalAndApartmentFields = _loginEstateManagerUI.getGeneralAndApartmentSpecificTextFields();
	_generalFields = _loginEstateManagerUI.getAllGeneralTextFields();

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
		_loginEstateManagerUI.resetTextFields();
	    }
	});
    }

    private void txtFieldListener() {
	JTextField[] allFields = _loginEstateManagerUI.getAllTextFields();

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

	if (allFieldsNotNull(_generalAndHouseFields)) {

	    int estateAgentID = _estateService.getEstateAgentIDFromLoginName(_loginName);
	    if (estateAgentID != -1) {

		String city = _cityField.getText();
		String postalCode = _postalCodeField.getText();
		String street = _streetField.getText();
		String number = _streetNumberField.getText();
		String squareArea = _squareAreaField.getText();

		int floors = Integer.parseInt(_floorsField.getText());
		double price = Double.parseDouble(_priceField.getText());
		int garden = Integer.parseInt(_gardenField.getText());

		boolean estateAndHouseIsManaged = _estateService.estateAndHouseIsManaged(estateAgentID, city,
			postalCode, street, number, squareArea, floors, price, garden);

		if (!estateAndHouseIsManaged) {

		    Estate newEstate = new Estate(estateAgentID, city, postalCode, street, number, squareArea);
		    _estateService.insertNewEstate(newEstate);
		    int estateID = _estateService.getEstateID(newEstate);

		    House newHouse = new House(estateID, floors, price, garden);
		    _estateService.insertNewHouse(newHouse);
		}
	    }
	}
    }

    private void handleHouseUpdate() {

	if (estateIDFieldNotNull()) {

	    String estateID = _estateIDField.getText();
	    boolean estateAgentManageHouse = _estateService.estateAgentManagesHouse(_loginName, estateID);

	    if (estateAgentManageHouse) {
		// update estate
		String[] generalColumnNames = new String[] { "city", "postal_code", "street", "street_number",
			"square_area" };
		JTextField[] allGeneralFields = _generalFields;

		int counter = 0;
		for (JTextField jTextField : allGeneralFields) {
		    if (!jTextField.getText().isEmpty()) {
			String currentColumn = generalColumnNames[counter];
			_estateService.updateGeneralEntry(currentColumn, estateID, jTextField.getText());
		    }
		    counter++;
		}

		// update house
		String[] houseColumnNames = new String[] { "floors", "price", "garden" };
		JTextField[] allHouseFields = new JTextField[] { _floorsField, _priceField, _gardenField };

		int counter2 = 0;
		for (JTextField jTextField : allHouseFields) {
		    if (!jTextField.getText().isEmpty()) {
			String currentColumn = houseColumnNames[counter2];
			_estateService.updateHouseEntry(currentColumn, estateID, jTextField.getText());
		    }
		    counter2++;
		}
	    }
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

	if (allFieldsNotNull(_generalAndApartmentFields)) {

	    int estateAgentID = _estateService.getEstateAgentIDFromLoginName(_loginName);

	    if (estateAgentID != -1) {
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
		    int estateID = _estateService.getEstateID(newEstate);

		    Apartment newApartment = new Apartment(estateID, floor, rent, rooms, balcony, kitchen);
		    _estateService.insertNewApartment(newApartment);
		}
	    }
	}
    }

    private void handleApartmentUpdate() {

	if (estateIDFieldNotNull()) {

	    String estateID = _estateIDField.getText();

	    boolean estateAgentManageApartment = _estateService.estateAgentManagesApartment(_loginName, estateID);

	    if (estateAgentManageApartment) {
		// update estate
		String[] generalColumnNames = new String[] { "city", "postal_code", "street", "street_number",
			"square_area" };
		JTextField[] allGeneralFields = _generalFields;

		int counter = 0;
		for (JTextField jTextField : allGeneralFields) {
		    if (!jTextField.getText().isEmpty()) {
			String currentColumn = generalColumnNames[counter];
			_estateService.updateGeneralEntry(currentColumn, estateID, jTextField.getText());
		    }
		    counter++;
		}

		// update apartment
		String[] apartmentColumnNames = new String[] { "floor", "rent", "rooms", "balcony",
			"built_in_kitchen" };
		JTextField[] allApartmentFields = new JTextField[] { _floorField, _rentField, _roomsField,
			_balconyField, _kitchenField };

		int counter2 = 0;
		for (JTextField jTextField : allApartmentFields) {
		    if (!jTextField.getText().isEmpty()) {
			String currentColumn = apartmentColumnNames[counter2];
			_estateService.updateApartmentEntry(currentColumn, estateID, jTextField.getText());
		    }
		    counter2++;
		}
	    }
	}
    }

    private void handleDelete() {

	if (estateIDFieldNotNull()) {

	    String estateID = _estateIDField.getText();
	    boolean estateAgentManageEstate = _estateService.estateAgentManagesEstate(_loginName, estateID);

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

    public void setLoginName(String loginName) {
	_loginName = loginName;
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

//    private void setMessageText(Color color, String text) {
//	_messageLabel.setForeground(color);
//	_messageLabel.setText(text);
//    }

    private boolean allFieldsNotNull(JTextField[] fields) {
	for (JTextField jTextField : fields) {
	    if (jTextField.getText().isEmpty())
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
}
