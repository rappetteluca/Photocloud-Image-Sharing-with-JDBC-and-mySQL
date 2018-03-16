import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class Register extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JPasswordField passwordFieldConfirm;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextArea bioText;
	private GUI parent;

	/**
	 * Create the frame.
	 */
	public Register(GUI g) {
		parent = g;
		setTitle("Register");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel usernameLabel = new JLabel("Username:");
		usernameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		usernameLabel.setBounds(36, 11, 141, 44);
		contentPane.add(usernameLabel);
		
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		passwordLabel.setBounds(40, 47, 141, 33);
		contentPane.add(passwordLabel);
		
		usernameField = new JTextField();
		usernameField.setBounds(201, 25, 206, 20);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(201, 55, 206, 20);
		contentPane.add(passwordField);
		
		passwordFieldConfirm = new JPasswordField();
		passwordFieldConfirm.setBounds(201, 86, 206, 20);
		contentPane.add(passwordFieldConfirm);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password:");
		lblConfirmPassword.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblConfirmPassword.setBounds(36, 84, 151, 20);
		contentPane.add(lblConfirmPassword);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblFirstName.setBounds(36, 115, 73, 33);
		contentPane.add(lblFirstName);
		
		firstNameField = new JTextField();
		firstNameField.setBounds(110, 122, 91, 20);
		contentPane.add(firstNameField);
		firstNameField.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblLastName.setBounds(211, 117, 73, 33);
		contentPane.add(lblLastName);
		
		lastNameField = new JTextField();
		lastNameField.setBounds(294, 122, 113, 20);
		contentPane.add(lastNameField);
		lastNameField.setColumns(10);
		
		JLabel lblBio = new JLabel("Bio (Optional):");
		lblBio.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblBio.setBounds(36, 148, 109, 35);
		contentPane.add(lblBio);
		
		bioText = new JTextArea();
		bioText.setBounds(139, 153, 263, 70);
		contentPane.add(bioText);
		
		JButton btnConfirm = new JButton("Create User / Billing Info");
		btnConfirm.setBounds(36, 234, 191, 23);
		btnConfirm.addActionListener(parent.actionEvents);
		contentPane.add(btnConfirm);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(318, 234, 89, 23);
		btnCancel.addActionListener(parent.actionEvents);
		contentPane.add(btnCancel);
	}
	
	public String getFirstName()
	{
		return firstNameField.getText();
	}
	public String getLastName()
	{
		return lastNameField.getText();
	}
	public String getUsername()
	{
		return usernameField.getText();
	}
	public String getPassword()
	{
		return new String(passwordField.getPassword());
	}
	public String getPasswordConfirm()
	{
		return new String(passwordFieldConfirm.getPassword());
	}
	public String getBioText()
	{
		return new String(bioText.getText());
	}
}
