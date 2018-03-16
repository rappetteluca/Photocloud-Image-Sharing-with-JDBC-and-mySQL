import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class BillingPane extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField phoneNumberField;
	private JTextField ccNumberField;
	private JTextField ccAddressField;
	private JTextField ccNameField;
	private JTextField ccZipCodeField; //**SQL DO: Create an additional attribute for this field.**
	private String userName;
	private GUI parent;
	private JTextField ccvField;
	private JTextField expField;

	/**
	 * Create the frame.
	 */
	public BillingPane(GUI g, String username) {
		parent = g;
		userName = username;
		setTitle("Billing info for new user: " + userName);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel streetAddrLabel = new JLabel("Expiration Date:");
		streetAddrLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		streetAddrLabel.setBounds(36, 146, 113, 33);
		contentPane.add(streetAddrLabel);
		
		JLabel phoneNumLabel = new JLabel("Phone Number:");
		phoneNumLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		phoneNumLabel.setBounds(36, 11, 141, 33);
		contentPane.add(phoneNumLabel);
		
		phoneNumberField = new JTextField();
		phoneNumberField.setBounds(201, 18, 206, 23);
		contentPane.add(phoneNumberField);
		
		ccNumberField = new JTextField();
		ccNumberField.setBounds(201, 52, 206, 23);
		contentPane.add(ccNumberField);
		
		JLabel lblCCNumber = new JLabel("Credit Card #:");
		lblCCNumber.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblCCNumber.setBounds(36, 51, 151, 20);
		contentPane.add(lblCCNumber);
		
		JLabel lblCCAddr = new JLabel("Credit Card Address:");
		lblCCAddr.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblCCAddr.setBounds(36, 82, 141, 19);
		contentPane.add(lblCCAddr);
		ccAddressField = new JTextField();
		ccAddressField.setBounds(201, 86, 206, 23);
		contentPane.add(ccAddressField);
		
		JLabel lblCCName = new JLabel("Name on Card:");
		lblCCName.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblCCName.setBounds(36, 112, 113, 33);
		contentPane.add(lblCCName);
		
		ccNameField = new JTextField();
		ccNameField.setBounds(201, 120, 206, 23);
		contentPane.add(ccNameField);
		ccNameField.setColumns(10);
		
		JLabel lblZipCode = new JLabel("Zip Code:");
		lblZipCode.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblZipCode.setBounds(36, 183, 73, 33);
		contentPane.add(lblZipCode);
		
		ccZipCodeField = new JTextField();
		ccZipCodeField.setBounds(119, 190, 113, 23);
		contentPane.add(ccZipCodeField);
		ccZipCodeField.setColumns(10);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.setBounds(10, 234, 89, 23);
		btnConfirm.addActionListener(parent.actionEvents);
		contentPane.add(btnConfirm);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(335, 234, 89, 23);
		btnCancel.addActionListener(parent.actionEvents);
		contentPane.add(btnCancel);
		
		JLabel lblCcv = new JLabel("CCV:");
		lblCcv.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblCcv.setBounds(242, 194, 46, 14);
		contentPane.add(lblCcv);
		
		ccvField = new JTextField();
		ccvField.setBounds(288, 191, 86, 23);
		contentPane.add(ccvField);
		ccvField.setColumns(10);
		
		expField = new JTextField();
		expField.setBounds(201, 151, 206, 23);
		contentPane.add(expField);
		expField.setColumns(10);
	}
	
	public String getCCName()
	{
		return ccNameField.getText();
	}
	public String getCCZipCode()
	{
		return ccZipCodeField.getText();
	}
	public String getExpiration()
	{
		return expField.getText();
	}
	public String getPhoneNumber()
	{
		return phoneNumberField.getText();
	}
	public String getCCNumber()
	{
		return ccNumberField.getText();
	}
	public String getCCAddress()
	{
		return ccAddressField.getText();
	}
	public String getUsername()
	{
		return userName;
	}
	public String getCCV()
	{
		return ccvField.getText();
	}
}
