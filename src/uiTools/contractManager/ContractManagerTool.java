package uiTools.contractManager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Date;
import java.util.List;

import javax.swing.*;

import materials.Contract;
import materials.Person;
import materials.PurchaseContract;
import materials.TenancyContract;
import services.ContractServiceIF;

public class ContractManagerTool {

    private ContractManagerUI _contractManagerUI;

    private JPanel _selectionPanel;
    private JPanel _overviewPanel;

    private JPanel _insertPersonPanel;
    private JPanel _signContractPanel;

    private JComboBox<String> _comboBox;
    private JLabel _contractKindLabel;

    private JButton _backButton;
    private JButton _submitButton;
    private JButton _resetButton;

    private JRadioButton _insertPersonButton;
    private JRadioButton _signContractButton;

    private JTextField _firstNameField;
    private JTextField _lastNameField;
    private JTextField _addressField;

    private JComboBox<String> _dayBox;
    private JComboBox<String> _monthBox;
    private JComboBox<String> _yearBox;

    private JTextField _houseIDField;
    private JTextField _apartmentIDField;
    private JTextField _contractNumberField;
    private JTextField _placeField;
    private JTextField _personIDField;
    private JTextField _noOfInstallmentsField;
    private JTextField _intrestRateField;
    private JTextField _durationField;
    private JTextField _addCostField;

    private JLabel _messageLabel;

    private JRadioButton _overviewButton;

    private ContractServiceIF _contractService;

    public ContractManagerTool(ContractServiceIF contractServiceImpl) {
	_contractManagerUI = new ContractManagerUI();
	_contractService = contractServiceImpl;

	initComponents();
	registerListener();
    }

    private void initComponents() {
	initPanels();
	initButtons();
	initComboBoxAndComboLabel();
	initInsertPersonFields();
	initStartDateComboBox();
	initGeneralSignContractFields();
	initPurchaseFields();
	initTenancyFields();

	_messageLabel = _contractManagerUI.getMessageLabel();
    }

    private void initComboBoxAndComboLabel() {
	_comboBox = _contractManagerUI.getComboBox();
	_contractKindLabel = _contractManagerUI.getContractKindLabel();
    }

    private void initPanels() {
	_selectionPanel = _contractManagerUI.getSelectionPanel();
	_overviewPanel = _contractManagerUI.getOverviewPanel();
	_insertPersonPanel = _contractManagerUI.getInsertPersonPanel();
	_signContractPanel = _contractManagerUI.getSignContractPanel();
    }

    private void initButtons() {
	_insertPersonButton = _contractManagerUI.getInsertPersonButton();
	_signContractButton = _contractManagerUI.getSignContractButton();
	_overviewButton = _contractManagerUI.getOverviewButton();
	_submitButton = _contractManagerUI.getSubmitButton();
	_resetButton = _contractManagerUI.getResetButton();
	_backButton = _contractManagerUI.getBackButton();
    }

    private void initInsertPersonFields() {
	_firstNameField = _contractManagerUI.getFirstNameField();
	_lastNameField = _contractManagerUI.getLastNameField();
	_addressField = _contractManagerUI.getAddressField();
    }

    private void initStartDateComboBox() {
	_dayBox = _contractManagerUI.getDayBox();
	_monthBox = _contractManagerUI.getMonthBox();
	_yearBox = _contractManagerUI.getYearBox();
    }

    private void initGeneralSignContractFields() {
	_contractNumberField = _contractManagerUI.getContractNoField();
	_placeField = _contractManagerUI.getPlaceField();
	_personIDField = _contractManagerUI.getPersonIDField();
    }

    private void initPurchaseFields() {
	_houseIDField = _contractManagerUI.getHouseIDField();
	_noOfInstallmentsField = _contractManagerUI.getNoOfInstallmentsField();
	_intrestRateField = _contractManagerUI.getIntrestRateFIeld();
    }

    private void initTenancyFields() {
	_apartmentIDField = _contractManagerUI.getApartmentIDField();
	_durationField = _contractManagerUI.getDurationField();
	_addCostField = _contractManagerUI.getAdditionalCostsField();
    }

    private void registerListener() {
	radioButtonListener();
	comboBoxListener();
	backButtonListener();
	resetButtonListener();
	submitButtonListener();
	txtFieldFocusListener();
    }

    private void radioButtonListener() {
	_insertPersonButton.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		_comboBox.setEnabled(false);
		_contractKindLabel.setForeground(Color.LIGHT_GRAY);
		activateInsertPanel();
		_submitButton.setText("insert");
		_contractManagerUI.clearAllTextFields();
	    }

	    private void activateInsertPanel() {
		_signContractPanel.setVisible(false);
		_selectionPanel.remove(_signContractPanel);
		_selectionPanel.add(_insertPersonPanel, BorderLayout.CENTER);
		_insertPersonPanel.setVisible(true);
	    }
	});

	_signContractButton.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		_comboBox.setEnabled(true);
		_contractKindLabel.setForeground(Color.BLACK);
		activateSignContractPanel();
		_contractManagerUI.clearAllTextFields();
		_contractManagerUI.resetStartDate();

		if (_contractManagerUI.getComboBox().getSelectedIndex() == 0) {
		    _submitButton.setText("make a purchase contract");
		} else {
		    _submitButton.setText("make a tenancy contract");
		}

	    }

	    private void activateSignContractPanel() {
		_insertPersonPanel.setVisible(false);
		_selectionPanel.remove(_insertPersonPanel);
		_selectionPanel.add(_signContractPanel, BorderLayout.CENTER);
		_signContractPanel.setVisible(true);
	    }
	});

	_overviewButton.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		_selectionPanel.setVisible(false);
		_signContractPanel.setVisible(false);
		_contractManagerUI.getContractPanel().remove(_selectionPanel);
		_contractManagerUI.getContractPanel().remove(_signContractPanel);
		_contractManagerUI.getContractPanel().add(_overviewPanel);
		initJTable();
		_overviewPanel.setVisible(true);
	    }

	    private void initJTable() {
		int entries = _contractService.getNumberOfContracts();
		String[][] data = new String[entries][4];

		List<Contract> contracts = _contractService.getContractList();

		int index = 0;
		for (Contract contract : contracts) {
		    String idContract = String.valueOf(contract.getIDContract());
		    String contractNo = String.valueOf(contract.getContractNumber());
		    String date = contract.getDate().toString();
		    String place = contract.getPlace();

		    data[index][0] = idContract;
		    data[index][1] = contractNo;
		    data[index][2] = date;
		    data[index][3] = place;
		    index++;
		}
		_contractManagerUI.createTable(data);
		contracts = null;
	    }
	});
    }

    private void comboBoxListener() {
	_comboBox.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		if (_comboBox.getSelectedIndex() == 0) {
		    _submitButton.setText("make a purchase contract");
		    _contractManagerUI.changeTenancyToPurchasePanel();
		} else {
		    _submitButton.setText("make a tenancy contract");
		    _contractManagerUI.changePurchaseToTenancyPanel();
		}
	    }
	});
    }

    private void backButtonListener() {
	_backButton.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		_overviewPanel.setVisible(false);
		_contractManagerUI.getContractPanel().remove(_overviewPanel);

		_insertPersonButton.setSelected(true);
		_contractManagerUI.getContractPanel().add(_selectionPanel);
		_selectionPanel.add(_insertPersonPanel);
		_selectionPanel.setVisible(true);
		_insertPersonPanel.setVisible(true);
		_comboBox.setEnabled(false);
		_contractKindLabel.setForeground(Color.LIGHT_GRAY);
		_submitButton.setText("insert");
	    }
	});
    }

    public void activateInsertPersonMode() {

    }

    private void resetButtonListener() {
	_resetButton.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		_contractManagerUI.clearAllTextFields();
	    }
	});
    }

    private void submitButtonListener() {
	_submitButton.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		if (_insertPersonButton.isSelected()) {
		    handleInsert();
		} else if (_signContractButton.isSelected()) {
		    handleSignContract();
		}
	    }
	});

    }

    private void txtFieldFocusListener() {
	JTextField[] allFields = _contractManagerUI.getAllTextFields();

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

    private void handleInsert() {
	if (personFieldsNotNull()) {
	    String firstName = _firstNameField.getText();
	    String name = _lastNameField.getText();
	    String address = _addressField.getText();

	    boolean personExist = _contractService.personExist(firstName, name, address);

	    if (!personExist) {
		_contractService.insertPerson(new Person(firstName, name, address));
		_contractManagerUI.clearAllTextFields();
	    }
	}
    }

    private boolean personFieldsNotNull() {
	String firstName = _firstNameField.getText();
	String lastName = _lastNameField.getText();
	String address = _addressField.getText();

	return !firstName.isEmpty() && !lastName.isEmpty() && !address.isEmpty();
    }

    private void handleSignContract() {
	if (_comboBox.getSelectedItem().equals("Purchase contract")) {
	    handleSubmitPurchaseContract();
	} else if (_comboBox.getSelectedItem().equals("Tenancy contract")) {

	    handleSubmitTenancyContract();
	}

    }

    private void handleSubmitPurchaseContract() {

	JTextField[] generalFields = _contractManagerUI.getAllGeneralFields();
	JTextField[] purchaseFields = _contractManagerUI.getAllPurchaseFields();

	if (fieldsNotNull(generalFields) && fieldsNotNull(purchaseFields)) {

	    boolean contractExist = _contractService.entryExist("contract", "contract_no",
		    _contractNumberField.getText());
	    boolean personExist = _contractService.entryExist("person", "id_person", _personIDField.getText());
	    boolean houseExist = _contractService.entryExist("house", "id_house", _houseIDField.getText());

	    if (!contractExist) {
		if (personExist && houseExist) {
		    makePurchaseContract();
		} else
		    setMessageText(Color.RED, "failed. Person / house not exist");
	    } else
		setMessageText(Color.RED, "failed. Contract already exist");
	} else
	    setMessageText(Color.RED, "Invalid input");
    }

    private void makePurchaseContract() {
	int contract_no = Integer.parseInt(_contractNumberField.getText());
	Date date = getCurrentDateInSQLFormat();
	String place = _placeField.getText();

	Contract newContract = new Contract(contract_no, date, place);
	_contractService.makeContract(newContract);

	int newContractID = _contractService.getContractID(newContract.getContractNumber());

	if (newContractID != -1) {

	    int personID = Integer.parseInt(_personIDField.getText());
	    int houseID = Integer.parseInt(_houseIDField.getText());
	    int noOfInstallments = Integer.parseInt(_noOfInstallmentsField.getText());
	    int intrestRate = Integer.parseInt(_intrestRateField.getText());

	    PurchaseContract newPurchaseContract = new PurchaseContract(personID, houseID, newContractID,
		    noOfInstallments, intrestRate);

	    _contractService.makePurchaseContract(newPurchaseContract);
	    setMessageText(Color.GREEN.darker(), "success. Purchase contract created");
	    _contractManagerUI.clearAllTextFields();
	}
    }

    private Date getCurrentDateInSQLFormat() {
	long time = System.currentTimeMillis();
	return new Date(time);
    }

    private void handleSubmitTenancyContract() {
	JTextField[] generalFields = _contractManagerUI.getAllGeneralFields();
	JTextField[] tenancyFields = _contractManagerUI.getAllTenancyFields();

	if (fieldsNotNull(generalFields) && fieldsNotNull(tenancyFields) && startDateSelected()) {

	    boolean contractExist = _contractService.entryExist("contract", "contract_no",
		    _contractNumberField.getText());
	    boolean personExist = _contractService.entryExist("person", "id_person", _personIDField.getText());
	    boolean apartmentExist = _contractService.entryExist("apartment", "id_apartment",
		    _apartmentIDField.getText());

	    if (!contractExist) {
		if (personExist && apartmentExist) {
		    makeTenancyContract();
		} else
		    setMessageText(Color.RED, "failed. Person / apartment not exist");
	    } else
		setMessageText(Color.RED, "failed. Contract already exist");
	} else
	    setMessageText(Color.RED, "Invalid input");
    }

    private void makeTenancyContract() {

	int contract_no = Integer.parseInt(_contractNumberField.getText());
	Date date = getCurrentDateInSQLFormat();
	String place = _placeField.getText();

	Contract newContract = new Contract(contract_no, date, place);
	_contractService.makeContract(newContract);

	int newContractID = _contractService.getContractID(newContract.getContractNumber());

	if (newContractID != -1) {

	    int personID = Integer.parseInt(_personIDField.getText());
	    int apartmentID = Integer.parseInt(_apartmentIDField.getText());
	    Date startDate = getSelectedDate();
	    int duration = Integer.parseInt(_durationField.getText());
	    int additionalCosts = Integer.parseInt(_addCostField.getText());

	    TenancyContract newTenancyContract = new TenancyContract(personID, apartmentID, newContractID, startDate,
		    duration, additionalCosts);

	    _contractService.makeTenancyContract(newTenancyContract);

	    setMessageText(Color.GREEN.darker(), "success. Tenancy contract created");
	    _contractManagerUI.clearAllTextFields();
	}
    }

    @SuppressWarnings("deprecation")
    private Date getSelectedDate() {
	int day = Integer.parseInt(_dayBox.getSelectedItem().toString());
	int month = Integer.parseInt(_monthBox.getSelectedItem().toString());
	int year = Integer.parseInt(_yearBox.getSelectedItem().toString());

	return new Date(year, month, day);
    }

    private boolean fieldsNotNull(JTextField[] fields) {
	for (JTextField jTextField : fields) {
	    if (jTextField.getText().isEmpty())
		return false;
	}
	return true;
    }

    private boolean startDateSelected() {
	String day = _dayBox.getSelectedItem().toString();
	String month = _monthBox.getSelectedItem().toString();
	String year = _yearBox.getSelectedItem().toString();

	return !day.equals("DD") && !month.equals("MM") && !year.equals("YYYY");
    }

    public JPanel getContractPanel() {
	return _contractManagerUI.getContractPanel();
    }

    public JPanel getOverviewPanel() {
	return _contractManagerUI.getOverviewPanel();
    }

    public void resetAllComponents() {
	_contractManagerUI.clearAllTextFields();
	_contractManagerUI.resetStartDate();
	_comboBox.setSelectedIndex(0);
	_comboBox.setEnabled(false);
	_insertPersonButton.setSelected(true);
	_contractKindLabel.setBackground(Color.LIGHT_GRAY);
	_messageLabel.setText(" ");
	_submitButton.setText("insert");

	_signContractPanel.setVisible(false);
	_selectionPanel.remove(_signContractPanel);
	_selectionPanel.add(_insertPersonPanel);
	_insertPersonPanel.setVisible(true);
    }

    private void setMessageText(Color color, String text) {
	_messageLabel.setForeground(color);
	_messageLabel.setText(text);
    }

}
