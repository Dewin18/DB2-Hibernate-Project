package uiTools;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import services.ContractServiceImpl;
import services.EstateServiceImpl;
import services.RegisterServiceImpl;
import uiTools.adminPasswordPanel.AdminPasswordPanelTool;
import uiTools.agentPasswordPanel.AgentPasswordPanelTool;
import uiTools.contractManager.ContractManagerTool;
import uiTools.loginEstateManager.LoginEstateManagerTool;
import uiTools.registerEstateManager.RegisterEstateManagerTool;

public class MainTool implements Observer {
    private MainToolUI _mainToolUI;
    private JFrame _window;

    // Sub tools
    private RegisterEstateManagerTool _registerEstateManagerTool;
    private LoginEstateManagerTool _loginEstateManagerTool;
    private ContractManagerTool _contractManagerTool;
    private AdminPasswordPanelTool _adminPasswordPanelTool;
    private AgentPasswordPanelTool _agentPasswordPanelTool;

    // Sub panels
    private JPanel _optionPanel;
    private JPanel _estatePanel;
    private JPanel _contractPanel;
    private JPanel _adminPasswordPanel;
    private JPanel _agentPasswordPanel;

    private JPanel _registerPanel;

    public MainTool() {
	initTools();
	setupUI();
	registerListener();
    }

    private void initTools() {
	_registerEstateManagerTool = new RegisterEstateManagerTool(new RegisterServiceImpl());
	_loginEstateManagerTool = new LoginEstateManagerTool(new EstateServiceImpl());
	_contractManagerTool = new ContractManagerTool(new ContractServiceImpl());

	_adminPasswordPanelTool = new AdminPasswordPanelTool();
	_adminPasswordPanelTool.registerNewObserver(this);

	_agentPasswordPanelTool = new AgentPasswordPanelTool();
	_agentPasswordPanelTool.registerNewObserver(this);

	initPanels();

	_mainToolUI = new MainToolUI();

	_window = _mainToolUI.getWindow();
	_optionPanel = _mainToolUI.getOptionPanel();
    }

    private void initPanels() {
	_registerPanel = _registerEstateManagerTool.getRegisterPanel();
	_estatePanel = _loginEstateManagerTool.getEstatePanel();
	_contractPanel = _contractManagerTool.getContractPanel();
	_adminPasswordPanel = _adminPasswordPanelTool.getLoginPanel();
	_agentPasswordPanel = _agentPasswordPanelTool.getLoginPanel();
    }

    private void setupUI() {
	_registerPanel.setVisible(false);
	_estatePanel.setVisible(false);
	_contractPanel.setVisible(false);
    }

    private void registerListener() {

	exitButtonListener();
	backButtonListener();
	createAccountListener();
	estateAgentLoginListener();
	manageContractListener();
    }

    private void exitButtonListener() {
	_mainToolUI.getExitButton().addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		System.exit(0);
	    }
	});
    }

    private void backButtonListener() {
	_mainToolUI.getBackButton().addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {

		_mainToolUI.getBackButton().setEnabled(false);

		resetComponentVisibility();
		removePanelsFromWindow();
		resetPasswordPanels();
	    }
	});
    }

    private void resetComponentVisibility() {
	_registerPanel.setVisible(false);
	_estatePanel.setVisible(false);
	_contractPanel.setVisible(false);
	_optionPanel.setVisible(true);

    }

    private void removePanelsFromWindow() {
	_window.remove(_registerPanel);
	_window.remove(_estatePanel);
	_window.remove(_contractPanel);
	_window.remove(_adminPasswordPanel);
	_window.remove(_agentPasswordPanel);
    }

    private void resetPasswordPanels() {
	_adminPasswordPanelTool.resetInvalidPasswordMessage();
	_agentPasswordPanelTool.resetInvalidPasswordMessage();
	_adminPasswordPanelTool.setInactive();
	_agentPasswordPanelTool.setInactive();
    }

    private void createAccountListener() {
	_mainToolUI.getCreateAccountButton().addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		_mainToolUI.getBackButton().setEnabled(true);
		_optionPanel.setVisible(false);
		_window.add(_adminPasswordPanel);
		_registerEstateManagerTool.resetAllComponents();

		_adminPasswordPanelTool.getPasswordField().setText("");
		_adminPasswordPanel.setVisible(true);
	    }
	});
    }

    private void estateAgentLoginListener() {
	_mainToolUI.getLoginButton().addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		_mainToolUI.getBackButton().setEnabled(true);
		_optionPanel.setVisible(false);
		_window.add(_agentPasswordPanel, BorderLayout.CENTER);
		
		// TODO _loginEstateManagerTool.resetMessageLabel();
		_loginEstateManagerTool.resetAllComponents();
		_agentPasswordPanelTool.getLoginField().setText("");
		_agentPasswordPanelTool.getPasswordField().setText("");
		_agentPasswordPanel.setVisible(true);
	    }
	});
    }

    private void manageContractListener() {
	_mainToolUI.getContractButton().addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		_mainToolUI.getBackButton().setEnabled(true);
		_optionPanel.setVisible(false);
		_window.add(_contractPanel, BorderLayout.CENTER);
		_contractManagerTool.resetAllComponents();
		_contractPanel.setVisible(true);
	    }
	});
    }

    @Override
    public void update() {
	if (_adminPasswordPanelTool.isActive()) {
	    showAdminLogin();
	} else if (_agentPasswordPanelTool.isActive()) {
	    showAgentLogin();
	}
    }

    private void showAdminLogin() {
	_adminPasswordPanel.setVisible(false);
	_window.add(_registerPanel, BorderLayout.CENTER);
	_registerPanel.setVisible(true);
    }

    private void showAgentLogin() {
	_agentPasswordPanel.setVisible(false);
	_window.add(_estatePanel, BorderLayout.CENTER);

	String currentUser = _agentPasswordPanelTool.getLoginName();
	_loginEstateManagerTool.setLoginName(currentUser);
	_estatePanel.setVisible(true);
    }
}
