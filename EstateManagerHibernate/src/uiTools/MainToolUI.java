package uiTools;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainToolUI {

    private JFrame _window;
    private JPanel _optionPanel;
    private JPanel _backAndExitPanel;

    private JButton _manageAccount;
    private JButton _login;
    private JButton _contract;
    private JButton _exit;
    private JButton _back;

    public MainToolUI() {

	initWindow();
	initOptionPanel();
	initBackAndExitPanel();
	initButtons();
	combineComponents();
	showWindow();
    }

    private void initWindow() {
	_window = new JFrame("Estate Management");
	_window.setPreferredSize(new Dimension(700, 530));
	_window.setLayout(new BorderLayout());
	_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initOptionPanel() {
	_optionPanel = new JPanel();
	_optionPanel.setLayout(new FlowLayout());
    }

    private void initBackAndExitPanel() {
	_backAndExitPanel = new JPanel();
	_backAndExitPanel.setLayout(new FlowLayout());
	_backAndExitPanel.setBackground(Color.GRAY);
    }

    private void initButtons() {
	_manageAccount = new JButton("Create/ manage account");
	_login = new JButton("Estate agent login");
	_contract = new JButton("Manage contracts");
	_back = new JButton("Back");
	_back.setEnabled(false);
	_exit = new JButton("Exit");
    }

    private void combineComponents() {
	_optionPanel.add(_manageAccount);
	_optionPanel.add(_login);
	_optionPanel.add(_contract);

	_backAndExitPanel.add(_back);
	_backAndExitPanel.add(_exit);

	_window.add(_optionPanel, BorderLayout.CENTER);
	_window.add(_backAndExitPanel, BorderLayout.SOUTH);
    }

    private void showWindow() {
	_window.pack();
	_window.setLocationRelativeTo(null);
	_window.setVisible(true);
    }

    public JFrame getWindow() {
	return _window;
    }

    public JPanel getOptionPanel() {
	return _optionPanel;
    }

    public JButton getCreateAccountButton() {
	return _manageAccount;
    }

    public JButton getLoginButton() {
	return _login;
    }

    public JButton getContractButton() {
	return _contract;
    }

    public JButton getBackButton() {
  	return _back;
      }
    
    public JButton getExitButton() {
	return _exit;
    }
}
