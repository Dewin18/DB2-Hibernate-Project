package uiTools.loginEstateManager;

import java.awt.*;
import javax.swing.*;

public class LoginEstateManagerUI {

    private JPanel _manageEstatePanel;

    // TODO invalid / success label underneath the button panel

    private JLabel _messageLabel;

    // North components
    private JPanel _northPanel;
    private JPanel _estatePanel;
    private JPanel _radioButtonPanel;

    private JLabel _manageEstateLabel;
    private JLabel _estate;

    private String[] _estateOptions;
    private JComboBox<String> _comboBox;

    private ButtonGroup _radioButtonGroup;
    private JRadioButton _create;
    private JRadioButton _delete;
    private JRadioButton _change;

    // Center components
    private JPanel _centerPanel;
    private JPanel _selectionPanel;
    private JPanel _buttonPanel;
    private JPanel _generalPanel;
    private JPanel _changeEstatePanel;
    private JPanel _specificHousePanel;
    private JPanel _specificApartmentPanel;

    private JLabel _generalLabel;

    // general fields
    private JLabel _cityLabel;
    private JLabel _postalCodeLabel;
    private JLabel _streetLabel;
    private JLabel _streetNumberLabel;
    private JLabel _squareAreaLabel;
    private JLabel _estateIDLabel;

    private JTextField _cityField;
    private JTextField _postalCodeField;
    private JTextField _streetField;
    private JTextField _streetNumberField;
    private JTextField _squareAreaField;
    private JTextField _estateIDField;

    // house specific
    private JLabel _specificHouseLabel;
    private JLabel _floorsLabel;
    private JLabel _priceLabel;
    private JLabel _gardenLabel;

    private JTextField _floorsField;
    private JTextField _priceField;
    private JTextField _gardenField;

    // apartment specific
    private JLabel _specificApartmentLabel;
    private JLabel _floorLabel;
    private JLabel _rentLabel;
    private JLabel _roomsLabel;
    private JLabel _balconyLabel;
    private JLabel _builtInKitchenLabel;

    private JTextField _floorField;
    private JTextField _rentField;
    private JTextField _roomsField;
    private JTextField _balconyField;
    private JTextField _builtInKitchenField;

    // south components

    private JPanel _southPanel;
    private JPanel _messagePanel;

    private JButton _submit;
    private JButton _reset;

    public LoginEstateManagerUI() {
	initMainPanel();
	initNorthComponents();
	initCenterComponents();
	initSouthComponents();
    }

    private void initMainPanel() {
	_manageEstatePanel = new JPanel();
	_manageEstatePanel.setLayout(new BorderLayout());
    }

    private void initNorthComponents() {
	initNorthPanels();
	initNorthLabels();
	initComboBox();
	initRadioGroup();
	combineNorthComponents();
    }

    private void initNorthPanels() {
	_northPanel = new JPanel();
	_northPanel.setLayout(new BoxLayout(_northPanel, BoxLayout.Y_AXIS));
	_northPanel.setPreferredSize(new Dimension(_northPanel.getWidth(), 150));

	_estatePanel = new JPanel();
	_estatePanel.setLayout(new FlowLayout());

	_radioButtonPanel = new JPanel();
	_radioButtonPanel.setLayout(new FlowLayout());
    }

    private void initNorthLabels() {
	_manageEstateLabel = new JLabel("Manage Estate");
	_manageEstateLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
	_manageEstateLabel.setForeground(Color.BLUE);
	_manageEstateLabel.setHorizontalAlignment(JLabel.CENTER);
	_manageEstateLabel.setVerticalAlignment(JLabel.CENTER);

	_estate = new JLabel("Estate");
    }

    private void initComboBox() {
	_estateOptions = new String[] { "House", "Apartment" };
	_comboBox = new JComboBox<>(_estateOptions);
    }

    private void initRadioGroup() {
	_radioButtonGroup = new ButtonGroup();

	_create = new JRadioButton("create", true);
	_delete = new JRadioButton("delete");
	_change = new JRadioButton("change");

	_radioButtonGroup.add(_create);
	_radioButtonGroup.add(_delete);
	_radioButtonGroup.add(_change);
    }

    private void combineNorthComponents() {

	_estatePanel.add(_estate);
	_estatePanel.add(_comboBox);

	_radioButtonPanel.add(_create);
	_radioButtonPanel.add(_delete);
	_radioButtonPanel.add(_change);

	_northPanel.add(_manageEstateLabel);
	_northPanel.add(_estatePanel);
	_northPanel.add(_radioButtonPanel);

	_manageEstatePanel.add(_northPanel, BorderLayout.NORTH);
    }

    private void initCenterComponents() {
	initCenterPanels();
	initCenterLabels();
	initCenterFields();
	initButtons();
	combineComponents();
    }

    private void initCenterPanels() {
	_centerPanel = new JPanel();
	_centerPanel.setLayout(new BoxLayout(_centerPanel, BoxLayout.Y_AXIS));

	_selectionPanel = new JPanel();
	_selectionPanel.setLayout(new FlowLayout());

	_generalPanel = new JPanel();
	_generalPanel.setLayout(new BoxLayout(_generalPanel, BoxLayout.Y_AXIS));

	_specificHousePanel = new JPanel();
	_specificHousePanel.setLayout(new BoxLayout(_specificHousePanel, BoxLayout.Y_AXIS));

	_specificApartmentPanel = new JPanel();
	_specificApartmentPanel.setLayout(new BoxLayout(_specificApartmentPanel, BoxLayout.Y_AXIS));

	_changeEstatePanel = new JPanel();
	_changeEstatePanel.setLayout(new BoxLayout(_changeEstatePanel, BoxLayout.Y_AXIS));
    }

    private void initCenterLabels() {
	_generalLabel = new JLabel("             General entries");
	_generalLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
	_specificHouseLabel = new JLabel("           House specific entries");
	_specificHouseLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
	_specificApartmentLabel = new JLabel("            Apartment specific entries");
	_specificApartmentLabel.setFont(new Font("SansSerif", Font.BOLD, 15));

	_cityLabel = new JLabel("            City");
	_postalCodeLabel = new JLabel("Postal Code");
	_streetLabel = new JLabel("         Street");
	_streetNumberLabel = new JLabel("   Street No.");
	_squareAreaLabel = new JLabel("Square Area");
	_estateIDLabel = new JLabel("    Estate ID");

	_floorsLabel = new JLabel(" Floors");
	_priceLabel = new JLabel("   Price");
	_gardenLabel = new JLabel("Garden");

	_floorLabel = new JLabel("                  Floor");
	_rentLabel = new JLabel("                   Rent");
	_roomsLabel = new JLabel("               Rooms");
	_balconyLabel = new JLabel("              Balcony");
	_builtInKitchenLabel = new JLabel("             Kitchen");
    }

    public JPanel getLoginPanel() {
	return _manageEstatePanel;
    }

    private void initCenterFields() {
	_cityField = new JTextField();
	_postalCodeField = new JTextField();
	_streetField = new JTextField();
	_streetNumberField = new JTextField();
	_squareAreaField = new JTextField();
	_estateIDField = new JTextField();
	_estateIDField.setBackground(Color.LIGHT_GRAY);
	_estateIDField.setEditable(false);

	_floorsField = new JTextField();
	_priceField = new JTextField();
	_gardenField = new JTextField();

	_floorField = new JTextField();
	_rentField = new JTextField();
	_roomsField = new JTextField();
	_balconyField = new JTextField();
	_builtInKitchenField = new JTextField();

	setFieldSize();
    }

    private void setFieldSize() {
	JTextField[] allTextFields = getAllTextFields();

	for (JTextField jTextField : allTextFields) {
	    jTextField.setPreferredSize(new Dimension(150, 26));
	}
    }

    private void initButtons() {
	_submit = new JButton("register");
	_reset = new JButton("reset");
    }

    private void combineComponents() {
	combineGeneralPanel();
	combineSpecificHousePanel();
	combineSpecificApartmentPanel();

	_manageEstatePanel.add(_generalPanel, BorderLayout.WEST);

	_changeEstatePanel.add(_specificHousePanel);
	_manageEstatePanel.add(_changeEstatePanel, BorderLayout.EAST);
    }

    private void combineGeneralPanel() {

	JPanel p1 = getFlowPanel();
	JPanel p2 = getFlowPanel();
	JPanel p3 = getFlowPanel();
	JPanel p4 = getFlowPanel();
	JPanel p5 = getFlowPanel();
	JPanel p6 = getFlowPanel();

	p1.add(_cityLabel);
	p1.add(_cityField);

	p2.add(_postalCodeLabel);
	p2.add(_postalCodeField);

	p3.add(_streetLabel);
	p3.add(_streetField);

	p4.add(_streetNumberLabel);
	p4.add(_streetNumberField);

	p5.add(_squareAreaLabel);
	p5.add(_squareAreaField);

	p6.add(_estateIDLabel);
	p6.add(_estateIDField);

	_generalPanel.add(_generalLabel);
	_generalPanel.add(p1);
	_generalPanel.add(p2);
	_generalPanel.add(p3);
	_generalPanel.add(p4);
	_generalPanel.add(p5);
	_generalPanel.add(p6);
    }

    private void combineSpecificHousePanel() {

	JPanel p1 = getFlowPanel();
	JPanel p2 = getFlowPanel();
	JPanel p3 = getFlowPanel();
	JPanel p4 = getFlowPanel();
	JPanel p5 = getFlowPanel();
	JPanel p6 = getFlowPanel();
	JPanel p7 = getFlowPanel();

	p1.add(_floorsLabel);
	p1.add(_floorsField);

	p2.add(_priceLabel);
	p2.add(_priceField);

	p3.add(_gardenLabel);
	p3.add(_gardenField);

	p4.add(new JLabel(" "));
	p4.add(new JLabel(" "));

	p5.add(new JLabel(" "));
	p5.add(new JLabel(" "));

	p6.add(new JLabel(" "));
	p6.add(new JLabel(" "));

	p7.add(new JLabel(" "));
	p7.add(new JLabel(" "));

	_specificHousePanel.add(_specificHouseLabel);
	_specificHousePanel.add(p1);
	_specificHousePanel.add(p2);
	_specificHousePanel.add(p3);
	_specificHousePanel.add(p4);
	_specificHousePanel.add(p5);
	_specificHousePanel.add(p6);
	_specificHousePanel.add(p7);
    }

    private void combineSpecificApartmentPanel() {

	JPanel p1 = getFlowPanel();
	JPanel p2 = getFlowPanel();
	JPanel p3 = getFlowPanel();
	JPanel p4 = getFlowPanel();
	JPanel p5 = getFlowPanel();

	p1.add(_floorLabel);
	p1.add(_floorField);

	p2.add(_rentLabel);
	p2.add(_rentField);

	p3.add(_roomsLabel);
	p3.add(_roomsField);

	p4.add(_balconyLabel);
	p4.add(_balconyField);

	p5.add(_builtInKitchenLabel);
	p5.add(_builtInKitchenField);

	_specificApartmentPanel.add(_specificApartmentLabel);
	_specificApartmentPanel.add(p1);
	_specificApartmentPanel.add(p2);
	_specificApartmentPanel.add(p3);
	_specificApartmentPanel.add(p4);
	_specificApartmentPanel.add(p5);
    }

    private void initSouthComponents() {
	initSouthPanels();
	initSouthLabels();
	combineSouthComponents();
    }

    private void initSouthPanels() {
	_southPanel = new JPanel();
	_southPanel.setLayout(new BoxLayout(_southPanel, BoxLayout.Y_AXIS));

	_buttonPanel = new JPanel();
	_buttonPanel.setLayout(new FlowLayout());

	_messagePanel = new JPanel();
    }

    private void initSouthLabels() {
	_messageLabel = new JLabel(" ");
	_messageLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
    }

    private void combineSouthComponents() {

	_messagePanel.add(_messageLabel);

	_buttonPanel.add(_submit);
	_buttonPanel.add(_reset);

	_southPanel.add(_buttonPanel);
	_southPanel.add(_messagePanel);

	_manageEstatePanel.add(_southPanel, BorderLayout.SOUTH);
    }

    public void changeHouseToApartmentPanel() {
	_specificHousePanel.setVisible(false);
	_changeEstatePanel.remove(_specificHousePanel);
	_changeEstatePanel.add(_specificApartmentPanel);
	_specificApartmentPanel.setVisible(true);
    }

    public void changeApartmentToHousePanel() {
	_specificApartmentPanel.setVisible(false);
	_changeEstatePanel.remove(_specificApartmentPanel);
	_changeEstatePanel.add(_specificHousePanel);
	_specificHousePanel.setVisible(true);
    }

    private JPanel getFlowPanel() {
	JPanel panel = new JPanel();
	panel.setLayout(new FlowLayout());
	return panel;
    }

    public JTextField getEstateIDField() {
	return _estateIDField;
    }

    public JTextField[] getAllTextFieldsWithoutIDField() {
	JTextField[] allTextFields = new JTextField[] { _cityField, _postalCodeField, _streetField, _streetNumberField,
		_squareAreaField, _floorsField, _priceField, _gardenField, _floorField, _rentField, _roomsField,
		_balconyField, _builtInKitchenField };

	return allTextFields;
    }

    public JTextField[] getAllTextFields() {
	JTextField[] allTextFields = new JTextField[] { _cityField, _postalCodeField, _streetField, _streetNumberField,
		_squareAreaField, _estateIDField, _floorsField, _priceField, _gardenField, _floorField, _rentField,
		_roomsField, _balconyField, _builtInKitchenField };

	return allTextFields;
    }

    public JTextField[] getGeneralAndHouseSpecificTextFields() {
	JTextField[] houseSpecificTextFields = new JTextField[] { _cityField, _postalCodeField, _streetField,
		_streetNumberField, _squareAreaField, _floorsField, _priceField, _gardenField };

	return houseSpecificTextFields;
    }

    public JTextField[] getGeneralAndApartmentSpecificTextFields() {
	JTextField[] apartmentSpecificTextFields = new JTextField[] { _cityField, _postalCodeField, _streetField,
		_streetNumberField, _squareAreaField, _floorField, _rentField, _roomsField, _balconyField,
		_builtInKitchenField };

	return apartmentSpecificTextFields;
    }

    public JTextField[] allHouseSpecificTextFields() {
	JTextField[] houseSpecificTextFields = new JTextField[] { _floorsField, _priceField, _gardenField };

	return houseSpecificTextFields;
    }

    public JTextField[] allApartmentSpecificTextFields() {
	JTextField[] apartmentSpecificTextFields = new JTextField[] { _floorField, _rentField, _roomsField,
		_balconyField, _builtInKitchenField };

	return apartmentSpecificTextFields;
    }

    public JTextField[] getAllGeneralTextFields() {
	JTextField[] generalTextFields = new JTextField[] { _cityField, _postalCodeField, _streetField,
		_streetNumberField, _squareAreaField };

	return generalTextFields;
    }

    public void resetTextFields() {
	JTextField[] allTextFields = getAllTextFields();

	for (JTextField jTextField : allTextFields) {
	    jTextField.setText("");
	}
    }

    public JComboBox<String> getComboBox() {
	return _comboBox;
    }

    public JButton getSubmitButton() {
	return _submit;
    }

    public JButton getResetButton() {
	return _reset;
    }

    public JRadioButton getCreateButton() {
	return _create;
    }

    public JRadioButton getDeleteButton() {
	return _delete;
    }

    public JRadioButton getChangeButton() {
	return _change;
    }

    public JLabel getMessageLabel() {
	return _messageLabel;
    }

    public JTextField getCityField() {
	return _cityField;
    }

    public JTextField getPostalCodeField() {
	return _postalCodeField;
    }

    public JTextField getStreetField() {
	return _streetField;
    }

    public JTextField getStreetNumberField() {
	return _streetNumberField;
    }

    public JTextField getSquareAreaField() {
	return _squareAreaField;
    }

    public JTextField getFloorsField() {
	return _floorsField;
    }

    public JTextField getPriceField() {
	return _priceField;
    }

    public JTextField getGardenField() {
	return _gardenField;
    }

    public JTextField getFloorField() {
	return _floorField;
    }

    public JTextField getRentField() {
	return _rentField;
    }
    
    public JTextField getRoomsField() {
	return _roomsField;
    }
    
    public JTextField getBalconyField() {
	return _balconyField;
    }
    
    public JTextField getKitchenField() {
	return _builtInKitchenField;
    }
}
