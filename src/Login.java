import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public	class Login {

	public JFrame frame;
	private JPanel loginPane;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private GUI parent;

	/**
	 * Create the frame.
	 */
	public Login(GUI g) {
		parent = g;
		frame = new JFrame();
		frame.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 450, 300);
		loginPane = new JPanel();
		loginPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(loginPane);
		loginPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username: ");
		lblUsername.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblUsername.setBounds(112, 73, 110, 30);
		loginPane.add(lblUsername);
		
		JLabel lblWelcome = new JLabel("Welcome!");
		lblWelcome.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblWelcome.setBounds(147, 11, 139, 30);
		loginPane.add(lblWelcome);
		
		JLabel lblPleaseLoginOr = new JLabel("Please Login Or Register");
		lblPleaseLoginOr.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblPleaseLoginOr.setBounds(112, 34, 210, 30);
		loginPane.add(lblPleaseLoginOr);
		
		usernameField = new JTextField();
		usernameField.setBounds(236, 79, 104, 23);
		loginPane.add(usernameField);
		usernameField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblPassword.setBounds(113, 124, 110, 30);
		loginPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(236, 130, 104, 23);
		loginPane.add(passwordField);
		passwordField.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(parent.actionEvents);
		btnLogin.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnLogin.setBounds(112, 197, 89, 23);
		loginPane.add(btnLogin);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(parent.actionEvents);
		btnRegister.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnRegister.setBounds(233, 198, 89, 23);
		loginPane.add(btnRegister);
		frame.setTitle("Login to Photo Cloud");
		
	}
	
	public String getUsername()
	{
		return usernameField.getText();
	}
	
	public String getPassword()
	{
		return new String(passwordField.getPassword());
	}
}

