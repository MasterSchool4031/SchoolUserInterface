package school.client.gui;

import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginDialog {

	private JLabel labelLogin;
	private JLabel labelPass;
	private JTextField fieldLogin;
	private JPasswordField fieldPass;
	private JDialog dialogLogin;
	private JPanel panelLogin;
	private Frame parent;
	private JOptionPane options;

	public LoginDialog(Frame parent) {
		super();
		this.parent = parent;
	}

	public static boolean showLogin(Frame parent, NameCallback name,
			PasswordCallback pass) {
		boolean validInput = false;
		if (name != null || pass != null) {
			LoginDialog loginDialog = new LoginDialog(parent);
			loginDialog.getLabelLogin().setText(
					name != null ? name.getPrompt() : "");
			loginDialog.getLabelLogin().setVisible(name != null);
			loginDialog.getFieldLogin().setVisible(name != null);
			loginDialog.getLabelPass().setText(
					pass != null ? pass.getPrompt() : "");
			loginDialog.getLabelPass().setVisible(pass != null);
			loginDialog.getFieldPass().setVisible(pass != null);

			validInput = loginDialog.askLogin();
			
			if (name != null) {
				if (validInput) {
					name.setName(loginDialog.getFieldLogin().getText());
				}
				loginDialog.getFieldLogin().setText("");
			}
			if (pass != null) {
				if (validInput) {
					pass.setPassword(loginDialog.getFieldPass().getPassword());
				}
				loginDialog.getFieldPass().setText("");

			}
		}
		return validInput;

	}

	private boolean askLogin() {
		Object reponse;
		this.getDialogLogin().setVisible(true);

		reponse = this.getOptions().getValue();
		return reponse != null
				&& ((Integer) reponse).intValue() == JOptionPane.OK_OPTION;
	}

	private JDialog getDialogLogin() {
		if (this.dialogLogin == null) {
			this.dialogLogin = getOptions().createDialog(this.parent, "login");
			this.dialogLogin.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		}
		return dialogLogin;
	}

	private JOptionPane getOptions() {
		if (this.options == null) {
			this.options = new JOptionPane(this.getPanelLogin(),
					JOptionPane.QUESTION_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
		}
		return this.options;
	}

	private JPanel getPanelLogin() {

		if (this.panelLogin == null) {
			this.panelLogin = new JPanel();
			this.panelLogin.setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();

			gbc.gridx = 1;
			gbc.gridy = 1;
			gbc.gridwidth = 1;
			this.panelLogin.add(this.getLabelLogin(), gbc);
			gbc.gridy = 2;
			this.panelLogin.add(this.getLabelPass(), gbc);
			gbc.gridx = 2;
			gbc.gridy = 1;
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			this.panelLogin.add(this.getFieldLogin(), gbc);
			gbc.gridy = 2;
			this.panelLogin.add(this.getFieldPass(), gbc);
		}
		return this.panelLogin;
	}

	private JLabel getLabelLogin() {
		if (this.labelLogin == null) {
			this.labelLogin = new JLabel("login : ");
		}
		return labelLogin;
	}

	private JLabel getLabelPass() {
		if (this.labelPass == null) {
			this.labelPass = new JLabel("Mot de passe : ");
		}
		return labelPass;
	}

	private JTextField getFieldLogin() {
		if (this.fieldLogin == null) {
			this.fieldLogin = new JTextField(20);
		}
		return fieldLogin;
	}

	private JPasswordField getFieldPass() {
		if (this.fieldPass == null) {
			this.fieldPass = new JPasswordField(20);
		}
		return fieldPass;
	}
}
