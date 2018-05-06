package uiTools.contractManager;

import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

public class ContractManagerUI {

    // overview
    private JPanel _overviewPanel;
    private JButton _backButton;
    private JTable _contractView;

    // selection menu
    private JPanel _contractPanel;
    private JPanel _selectionPanel;

    // North components
    private JPanel _northPanel;
    private JPanel _radioButtonPanel;
    private JPanel _contractKindPanel;

    private JLabel _manageContractsLabel;
    private JLabel _contractKindLabel;

    private String[] _contractOptions;
    private JComboBox<String> _comboBox;

    private ButtonGroup _radioButtonGroup;
    private JRadioButton _insertPerson;
    private JRadioButton _signContract;
    private JRadioButton _overview;

    // Center components

    // insert person
    private JPanel _insertPersonPanel;
    private JLabel _firstNameLabel;
    private JLabel _lastNameLabel;
    private JLabel _addressLabel;

    private JTextField _firstNameField;
    private JTextField _lastNameField;
    private JTextField _addressField;

    // sign contract
    private JPanel _signContractPanel;

    private JPanel _generalPanel;
    private JPanel _purchasePanel;
    private JPanel _tenancyPanel;

    private JTextField _contractNumberField;
    private JTextField _placeField;
    private JTextField _personIDField;

    private JTextField _apartmentIDField;
    private JTextField _durationField;
    private JTextField _addCostField;

    private JComboBox<String> _dayBox;
    private JComboBox<String> _monthBox;
    private JComboBox<String> _yearBox;

    // purchase contract
    private JTextField _houseIDField;
    private JTextField _noOfInstallmentsField;
    private JTextField _intrestRateField;

    // south components

    private JPanel _southPanel;
    private JPanel _messagePanel;
    private JPanel _buttonPanel;
    private JLabel _messageLabel;
    private JButton _submit;
    private JButton _reset;

    public ContractManagerUI() {
	initMainPanel();
	initOverviewPanel();
	initNorthComponents();
	initCenterComponents();
	initSouthComponents();
    }

    private void initMainPanel() {
	_contractPanel = new JPanel();
	_contractPanel.setLayout(new BorderLayout());
    }

    private void initOverviewPanel() {
	// for overview
	_overviewPanel = new JPanel();
	_overviewPanel.setLayout(new BorderLayout());
	_backButton = new JButton("back");
	_overviewPanel.add(_backButton, BorderLayout.NORTH);
    }

    public void createTable(String[][] data) {
	String[] title = new String[] { "Contract ID", "Contract No.", "Date", "Place" };

	_contractView = new JTable(data, title);
	_contractView.setEnabled(false);
	_overviewPanel.add(new JScrollPane(_contractView), BorderLayout.CENTER);
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

	_contractKindPanel = new JPanel();
	_contractKindPanel.setLayout(new FlowLayout());

	_radioButtonPanel = new JPanel();
	_radioButtonPanel.setLayout(new FlowLayout());

	// for selection menu
	_selectionPanel = new JPanel();
	_selectionPanel.setLayout(new BorderLayout());
    }

    private void initNorthLabels() {
	_manageContractsLabel = new JLabel("Manage Contracts");
	_manageContractsLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
	_manageContractsLabel.setForeground(Color.BLUE);
	_manageContractsLabel.setHorizontalAlignment(JLabel.CENTER);
	_manageContractsLabel.setVerticalAlignment(JLabel.CENTER);

	_contractKindLabel = new JLabel("Contract");
	_contractKindLabel.setForeground(Color.LIGHT_GRAY);
    }

    private void initComboBox() {
	_contractOptions = new String[] { "Purchase contract", "Tenancy contract" };
	_comboBox = new JComboBox<>(_contractOptions);
	_comboBox.setEnabled(false);
    }

    private void initRadioGroup() {
	_radioButtonGroup = new ButtonGroup();

	_insertPerson = new JRadioButton("insert person", true);
	_signContract = new JRadioButton("sign contract");
	_overview = new JRadioButton("contract overview");

	_radioButtonGroup.add(_insertPerson);
	_radioButtonGroup.add(_signContract);
	_radioButtonGroup.add(_overview);
    }

    private void combineNorthComponents() {

	_contractKindPanel.add(_contractKindLabel);
	_contractKindPanel.add(_comboBox);

	_radioButtonPanel.add(_insertPerson);
	_radioButtonPanel.add(_signContract);
	_radioButtonPanel.add(_overview);

	_northPanel.add(_manageContractsLabel);
	_northPanel.add(_radioButtonPanel);
	_northPanel.add(_contractKindPanel);

	_selectionPanel.add(_northPanel, BorderLayout.NORTH);
	_contractPanel.add(_selectionPanel);
    }

    private void initCenterComponents() {
	initInsertPersonComponents();
	initSignContractComponents();

	_selectionPanel.add(_insertPersonPanel, BorderLayout.CENTER);
    }

    private void initInsertPersonComponents() {
	initInsertPersonLabels();
	initInsertPersonFields();
	initInsertPersonPanel();
    }

    private void initInsertPersonPanel() {
	_insertPersonPanel = new JPanel();
	_insertPersonPanel.setLayout(new BoxLayout(_insertPersonPanel, BoxLayout.Y_AXIS));

	JPanel firstNamePanel = new JPanel();
	firstNamePanel.setLayout(new FlowLayout());
	firstNamePanel.add(_firstNameLabel);
	firstNamePanel.add(_firstNameField);

	JPanel lastNamePanel = new JPanel();
	lastNamePanel.setLayout(new FlowLayout());
	lastNamePanel.add(_lastNameLabel);
	lastNamePanel.add(_lastNameField);

	JPanel addressPanel = new JPanel();
	addressPanel.setLayout(new FlowLayout());
	addressPanel.add(_addressLabel);
	addressPanel.add(_addressField);

	_insertPersonPanel.add(firstNamePanel);
	_insertPersonPanel.add(lastNamePanel);
	_insertPersonPanel.add(addressPanel);
    }

    private void initInsertPersonLabels() {
	_firstNameLabel = new JLabel("First name");
	_lastNameLabel = new JLabel("Last name");
	_addressLabel = new JLabel("   Address");
    }

    private void initInsertPersonFields() {
	_firstNameField = new JTextField();
	_firstNameField.setPreferredSize(new Dimension(150, 26));

	_lastNameField = new JTextField();
	_lastNameField.setPreferredSize(new Dimension(150, 26));

	_addressField = new JTextField();
	_addressField.setPreferredSize(new Dimension(150, 26));
    }

    private void initSignContractComponents() {
	initSignContractPanels();
	initSignContractBoxes();
	initSignContractTextFields();
	combineSignContractComponents();
    }

    private void initSignContractPanels() {
	_signContractPanel = new JPanel();
	_signContractPanel.setLayout(new FlowLayout());

	_generalPanel = new JPanel();
	_generalPanel.setLayout(new BoxLayout(_generalPanel, BoxLayout.Y_AXIS));
	_generalPanel.setPreferredSize(new Dimension(340, 240));

	_purchasePanel = new JPanel();
	_purchasePanel.setLayout(new BoxLayout(_purchasePanel, BoxLayout.Y_AXIS));
	_purchasePanel.setPreferredSize(new Dimension(340, 240));

	_tenancyPanel = new JPanel();
	_tenancyPanel.setLayout(new BoxLayout(_tenancyPanel, BoxLayout.Y_AXIS));
	_tenancyPanel.setPreferredSize(new Dimension(340, 240));

	_signContractPanel.add(_generalPanel);
	_signContractPanel.add(_purchasePanel);
    }

    private void initSignContractBoxes() {
	_dayBox = new JComboBox<>(getCounterArray(1, 32, "DD"));
	_monthBox = new JComboBox<>(getCounterArray(1, 13, "MM"));
	_yearBox = new JComboBox<>(getCounterArray(getCurrentYear(), 2031, "YYYY"));
    }

    private String[] getCounterArray(int startValue, int endValue, String description) {

	int difference = endValue - startValue;

	String[] counterArray = new String[difference + 1];
	counterArray[0] = description;

	for (int i = 1; i <= difference; i++) {
	    counterArray[i] = String.valueOf(startValue);
	    startValue += 1;
	}
	return counterArray;
    }

    private void initSignContractTextFields() {
	initGeneralFields();
	initPurchaseSpecificFields();
	initTenancySpecificFields();
    }

    private void initGeneralFields() {
	_contractNumberField = new JTextField();
	_contractNumberField.setPreferredSize(new Dimension(150, 26));

	_placeField = new JTextField();
	_placeField.setPreferredSize(new Dimension(150, 26));

	_personIDField = new JTextField();
	_personIDField.setPreferredSize(new Dimension(150, 26));
    }

    private void initPurchaseSpecificFields() {
	_houseIDField = new JTextField();
	_houseIDField.setPreferredSize(new Dimension(150, 26));

	_noOfInstallmentsField = new JTextField();
	_noOfInstallmentsField.setPreferredSize(new Dimension(150, 26));

	_intrestRateField = new JTextField();
	_intrestRateField.setPreferredSize(new Dimension(150, 26));
    }

    private void initTenancySpecificFields() {
	// TODO Auto-generated method stub
	_apartmentIDField = new JTextField();
	_apartmentIDField.setPreferredSize(new Dimension(150, 26));

	_durationField = new JTextField();
	_durationField.setPreferredSize(new Dimension(150, 26));

	_addCostField = new JTextField();
	_addCostField.setPreferredSize(new Dimension(150, 26));
    }

    private void combineSignContractComponents() {
	combineGeneralComponents();
	combinePurchaseSpecificComponents();
	combineTenancySpecificComponents();
    }

    private void combineGeneralComponents() {
	JPanel generalEntryPanel = new JPanel();
	generalEntryPanel.setLayout(new FlowLayout());
	JLabel generalLabel = new JLabel("   General entries");
	generalLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
	generalEntryPanel.add(generalLabel);

	JPanel contractNumberPanel = new JPanel();
	contractNumberPanel.setLayout(new FlowLayout());
	contractNumberPanel.add(new JLabel("Contract No."));
	contractNumberPanel.add(_contractNumberField);
	contractNumberPanel.add(new JLabel("           "));

	JPanel placePanel = new JPanel();
	placePanel.setLayout(new FlowLayout());
	placePanel.add(new JLabel("Place"));
	placePanel.add(_placeField);

	JPanel personIDPanel = new JPanel();
	personIDPanel.setLayout(new FlowLayout());
	personIDPanel.add(new JLabel("Person ID"));
	personIDPanel.add(_personIDField);
	personIDPanel.add(new JLabel("      "));

	_generalPanel.add(generalEntryPanel);
	_generalPanel.add(contractNumberPanel);
	_generalPanel.add(placePanel);
	_generalPanel.add(personIDPanel);
    }

    private void combinePurchaseSpecificComponents() {
	JPanel purchaseEntryPanel = new JPanel();
	purchaseEntryPanel.setLayout(new FlowLayout());
	JLabel purchaseLabel = new JLabel("   Purchase contract entries");
	purchaseLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
	purchaseEntryPanel.add(purchaseLabel);

	JPanel houseIDPanel = new JPanel();
	houseIDPanel.setLayout(new FlowLayout());
	houseIDPanel.add(new JLabel("                  House ID"));
	houseIDPanel.add(_houseIDField);
	houseIDPanel.add(new JLabel("           "));

	JPanel installmentsPanel = new JPanel();
	installmentsPanel.setLayout(new FlowLayout());
	installmentsPanel.add(new JLabel("No. of Install."));
	installmentsPanel.add(_noOfInstallmentsField);

	JPanel intrestRatePanel = new JPanel();
	intrestRatePanel.setLayout(new FlowLayout());
	intrestRatePanel.add(new JLabel("    Intrest rate"));
	intrestRatePanel.add(_intrestRateField);

	_purchasePanel.add(purchaseEntryPanel);
	_purchasePanel.add(houseIDPanel);
	_purchasePanel.add(installmentsPanel);
	_purchasePanel.add(intrestRatePanel);
    }

    private void combineTenancySpecificComponents() {
	JPanel tenancyEntryPanel = new JPanel();
	tenancyEntryPanel.setLayout(new FlowLayout());
	JLabel tenancyLabel = new JLabel("   Tenancy contract entries");
	tenancyLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
	tenancyEntryPanel.add(tenancyLabel);

	JPanel apartmentIDPanel = new JPanel();
	apartmentIDPanel.setLayout(new FlowLayout());
	apartmentIDPanel.add(new JLabel("Apartment ID"));
	apartmentIDPanel.add(_apartmentIDField);
	apartmentIDPanel.add(new JLabel("         "));

	JPanel durationPanel = new JPanel();
	durationPanel.setLayout(new FlowLayout());
	durationPanel.add(new JLabel("Duration days"));
	durationPanel.add(_durationField);
	durationPanel.add(new JLabel("          "));

	JPanel addCostPanel = new JPanel();
	addCostPanel.setLayout(new FlowLayout());
	addCostPanel.add(new JLabel("Additional costs"));
	addCostPanel.add(_addCostField);
	addCostPanel.add(new JLabel("              "));

	JPanel startDatePanel = new JPanel();
	startDatePanel.setLayout(new FlowLayout());
	startDatePanel.add(new JLabel("Start date"));
	startDatePanel.add(_dayBox);
	startDatePanel.add(_monthBox);
	startDatePanel.add(_yearBox);

	_tenancyPanel.add(tenancyEntryPanel);
	_tenancyPanel.add(apartmentIDPanel);
	_tenancyPanel.add(durationPanel);
	_tenancyPanel.add(addCostPanel);
	_tenancyPanel.add(startDatePanel);
    }

    private void initSouthComponents() {
	initSouthPanels();
	initSouthLabels();
	initSouthButtons();
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

    private void initSouthButtons() {
	_submit = new JButton("insert");
	_reset = new JButton("reset");
    }

    private void combineSouthComponents() {

	_messagePanel.add(_messageLabel);

	_buttonPanel.add(_submit);
	_buttonPanel.add(_reset);

	_southPanel.add(_buttonPanel);
	_southPanel.add(_messagePanel);

	_selectionPanel.add(_southPanel, BorderLayout.SOUTH);
    }

    public JRadioButton getInsertPersonButton() {
	return _insertPerson;
    }

    public JRadioButton getSignContractButton() {
	return _signContract;
    }

    public JRadioButton getOverviewButton() {
	return _overview;
    }

    public JPanel getSelectionPanel() {
	return _selectionPanel;
    }

    public JPanel getOverviewPanel() {
	return _overviewPanel;
    }

    public JButton getBackButton() {
	return _backButton;
    }

    public JPanel getContractPanel() {
	return _contractPanel;
    }

    public JComboBox<String> getComboBox() {
	return _comboBox;
    }

    public JLabel getContractKindLabel() {
	return _contractKindLabel;
    }

    public JPanel getInsertPersonPanel() {
	return _insertPersonPanel;
    }

    public JPanel getSignContractPanel() {
	return _signContractPanel;
    }

    public JButton getSubmitButton() {
	return _submit;
    }

    public JButton getResetButton() {
	return _reset;
    }

    public JTextField getFirstNameField() {
	return _firstNameField;
    }

    public JTextField getLastNameField() {
	return _lastNameField;
    }

    public JTextField getAddressField() {
	return _addressField;
    }
    
    public JTextField getContractNoField() {
	return _contractNumberField;
    }
    
    public JTextField getPlaceField() {
	return _placeField;
    }
    
    public JTextField getPersonIDField() {
	return _personIDField;
    }
    
    public JTextField getNoOfInstallmentsField() {
	return _noOfInstallmentsField;
    }
    
    public JTextField getIntrestRateFIeld() {
	return _intrestRateField;
    }
    
    public JTextField getDurationField() {
	return _durationField;
    }
    
    public JTextField getAdditionalCostsField() {
	return _addCostField;
    }
    
    public JTextField getHouseIDField() {
	return _houseIDField;
    }
    
    public JTextField getApartmentIDField() {
	return _apartmentIDField;
    }
    
    public JLabel getMessageLabel() {
	return _messageLabel;
    }

    public JComboBox<String> getDayBox() {
	return _dayBox;
    }

    public JComboBox<String> getMonthBox() {
	return _monthBox;
    }

    public JComboBox<String> getYearBox() {
	return _yearBox;
    }

    public void changeTenancyToPurchasePanel() {
	_tenancyPanel.setVisible(false);
	_signContractPanel.remove(_tenancyPanel);
	_signContractPanel.add(_purchasePanel);
	_purchasePanel.setVisible(true);
    }

    public void changePurchaseToTenancyPanel() {
	_purchasePanel.setVisible(false);
	_signContractPanel.remove(_purchasePanel);
	_signContractPanel.add(_tenancyPanel);
	_tenancyPanel.setVisible(true);
    }

    public int getCurrentDay() {
	DateFormat dateFormat = new SimpleDateFormat("dd");
	Date date = new Date();
	return Integer.parseInt(dateFormat.format(date));
    }

    public int getCurrentMonth() {
	DateFormat dateFormat = new SimpleDateFormat("MM");
	Date date = new Date();
	return Integer.parseInt(dateFormat.format(date));
    }

    public int getCurrentYear() {
	DateFormat dateFormat = new SimpleDateFormat("yyyy");
	Date date = new Date();
	return Integer.parseInt(dateFormat.format(date));
    }

    public void clearAllTextFields() {
	JTextField[] allFields = new JTextField[] { _firstNameField, _lastNameField, _addressField,
		_contractNumberField, _placeField, _personIDField, _houseIDField, _apartmentIDField,
		_noOfInstallmentsField, _intrestRateField, _durationField, _addCostField };

	for (JTextField jTextField : allFields) {
	    jTextField.setText("");
	}
    }

    public JTextField[] getAllTextFields() {
	JTextField[] allFields = new JTextField[] { _firstNameField, _lastNameField, _addressField,
		_contractNumberField, _placeField, _personIDField, _houseIDField, _apartmentIDField,
		_noOfInstallmentsField, _intrestRateField, _durationField, _addCostField };

	return allFields;
    }

    public JTextField[] getAllGeneralFields() {
	JTextField[] generalFields = new JTextField[] { _contractNumberField, _placeField, _personIDField };

	return generalFields;
    }

    public JTextField[] getAllPurchaseFields() {
	JTextField[] purchaseFields = new JTextField[] { _houseIDField, _noOfInstallmentsField, _intrestRateField };

	return purchaseFields;
    }

    public JTextField[] getAllTenancyFields() {
	JTextField[] tenancyFields = new JTextField[] { _apartmentIDField, _durationField, _addCostField };

	return tenancyFields;
    }

    public void resetStartDate() {
	_dayBox.setSelectedIndex(0);
	_monthBox.setSelectedIndex(0);
	_yearBox.setSelectedIndex(0);
    }
}
