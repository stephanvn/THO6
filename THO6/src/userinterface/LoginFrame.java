package userinterface;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import application_logic.LoginControl;

@SuppressWarnings("serial")
public class LoginFrame extends JFrame {
	
	private JPanel panel;
	private MyTextField txtUsername;
	private MyPasswordField txtPassword;
	private JButton btnLogin;
	private LoginControl control;
	
	public LoginFrame(LoginControl control) {
		super("Login - Businessrule generator tool");
		this.control = control;
		createGUI();
	}
	
	public void createGUI() {
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new MigLayout("", "[75px][250px][75px]", "[]10[]"));
		
		txtUsername = new MyTextField();
		txtUsername.setPlaceholder("Username");
		txtUsername.addKeyListener(onEnter);
		panel.add(txtUsername, "cell 1 0, grow");
		
		txtPassword = new MyPasswordField();
		txtPassword.setPlaceholder("Password");
		txtPassword.addKeyListener(onEnter);
		panel.add(txtPassword, "cell 1 1, grow");
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(loginButton);
		panel.add(btnLogin, "cell 1 2, grow");
		
		setSize(400,150);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	ActionListener loginButton = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(!txtUsername.getText().equals("") && !String.valueOf(txtPassword.getPassword()).equals("")) {
				if(!control.checkUser(txtUsername.getText(),String.valueOf(txtPassword.getPassword()))) {
					JOptionPane.showMessageDialog(null,
							"User not found!");
				}
				else {
					dispose();
				}
			}
			else {
				JOptionPane.showMessageDialog(null,
						"Please fill in all fields!");
			}
		}
	};
	
	KeyAdapter onEnter = new KeyAdapter() {
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_ENTER) {
				btnLogin.doClick();
			}
		}
	};

}
