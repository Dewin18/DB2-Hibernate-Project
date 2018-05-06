package uiTools.registerEstateManager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class RegisterEstateManagerUI {

    // main register panel
    private JPanel _registerPanel;

    // panel inside the register panel
    private JPanel _componentPanel;

    // panels inside the component panel
    private JPanel _radioButtonPanel;
    private JPanel _namePanel;
    private JPanel _adressPanel;
    private JPanel _loginPanel;
    private JPanel _passwordPanel;
    private JPanel _buttonPanel;
    private JPanel _labelPanel;

    private JLabel _registerLabel;
    private JLabel _nameLabel;
    private JLabel _adressLabel;
    private JLabel _loginLabel;
    private JLabel _passwordLabel;
    private JLabel _message;

    private JTextField _nameField;
    private JTextField _adressField;
    private JTextField _loginField;
    private JTextField _passwordField;

    private JButton _submit;
    private JButton _reset;

    private ButtonGroup _radioButtonGroup;

    private JRadioButton _create;
    private JRadioButton _delete;
    private JRadioButton _change;

    public RegisterEstateManagerUI() {
	initPanels();
	initRadioGroup();
	initLabels();
	initTxtFields();
	initButtons();
	combineComponents();
    }

    private void initPanels() {
	initMainRegisterPanels();
	initComponentPanels();
    }

    private void initMainRegisterPanels() {
	_registerPanel = new JPanel();
	_registerPanel.setLayout(new BorderLayout());

	_componentPanel = new JPanel();
	_componentPanel.setLayout(new BoxLayout(_componentPanel, BoxLayout.Y_AXIS));
	_componentPanel.setMaximumSize(new Dimension(200, 300));

	_radioButtonPanel = new JPanel();
	_radioButtonPanel.setLayout(new FlowLayout());
    }

    private void initComponentPanels() {
	_namePanel = new JPanel();
	_namePanel.setLayout(new FlowLayout());

	_adressPanel = new JPanel();
	_adressPanel.setLayout(new FlowLayout());

	_loginPanel = new JPanel();
	_loginPanel.setLayout(new FlowLayout());

	_passwordPanel = new JPanel();
	_passwordPanel.setLayout(new FlowLayout());

	_buttonPanel = new JPanel();
	_buttonPanel.setLayout(new FlowLayout());

	_labelPanel = new JPanel();
	_labelPanel.setLayout(new FlowLayout());
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

    private void initLabels() {
	_registerLabel = new JLabel("Manage account");
	_nameLabel = new JLabel("name");
	_adressLabel = new JLabel("adress");
	_loginLabel = new JLabel("login");
	_passwordLabel = new JLabel("password");

	_message = new JLabel("  ");
	_message.setFont(new Font("SansSerif", Font.BOLD, 15));
	_message.setForeground(Color.GREEN.darker());
    }

    private void initTxtFields() {
	_nameField = new JTextField();
	_adressField = new JTextField();
	_loginField = new JTextField();
	_passwordField = new JTextField();

	_nameField.setPreferredSize(new Dimension(110, 26));
	_adressField.setPreferredSize(new Dimension(115, 26));
	_loginField.setPreferredSize(new Dimension(105, 26));
	_passwordField.setPreferredSize(new Dimension(132, 26));
    }

    private void initButtons() {
	_submit = new JButton("sign up");
	_reset = new JButton("clear");
    }

    private void combineComponents() {
	_registerLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
	_registerLabel.setForeground(Color.BLUE);
	_registerLabel.setHorizontalAlignment(JLabel.CENTER);
	_registerLabel.setVerticalAlignment(JLabel.CENTER);
	_registerPanel.add(_registerLabel, BorderLayout.NORTH);

	_radioButtonPanel.add(_create);
	_radioButtonPanel.add(_delete);
	_radioButtonPanel.add(_change);
	_componentPanel.add(_radioButtonPanel);

	_namePanel.add(_nameLabel);
	_namePanel.add(_nameField);

	_adressPanel.add(_adressLabel);
	_adressPanel.add(_adressField);

	_loginPanel.add(_loginLabel);
	_loginPanel.add(_loginField);

	_passwordPanel.add(_passwordLabel);
	_passwordPanel.add(_passwordField);

	_buttonPanel.add(_submit);
	_buttonPanel.add(_reset);

	_labelPanel.add(_message);

	_componentPanel.add(_namePanel);
	_componentPanel.add(_adressPanel);
	_componentPanel.add(_loginPanel);
	_componentPanel.add(_passwordPanel);
	_componentPanel.add(_buttonPanel);
	_componentPanel.add(_labelPanel);

	_registerPanel.add(_componentPanel, BorderLayout.CENTER);
    }

    public JPanel getRegisterPanel() {
	return _registerPanel;
    }

    public JTextField[] getAllTextFields() {
	JTextField[] txtFields = new JTextField[] { _nameField, _adressField, _loginField, _passwordField };
	return txtFields;
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

    public JTextField getNameField() {
	return _nameField;
    }

    public JTextField getAdressField() {
	return _adressField;
    }

    public JTextField getLoginField() {
	return _loginField;
    }

    public JTextField getPasswordField() {
	return _passwordField;
    }

    public JButton getSubmitButton() {
	return _submit;
    }

    public JButton getResetButton() {
	return _reset;
    }

    public JLabel getMessageLabel() {
	return _message;
    }
}
