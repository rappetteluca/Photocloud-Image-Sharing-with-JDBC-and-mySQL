import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserInfoPane extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String photographerName;
	private GUI parent;
	private Database instance;
	private User u;

	/**
	 * Create the frame.
	 */
	public UserInfoPane(String userName, Database db, GUI g) {
		photographerName = userName;
		parent = g;
		instance = db;
		setTitle("Viewing Profile: " + userName);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		ResultSet rs = instance.showSinglePhotographer(photographerName);
		try 
		{
			rs.next();
			u = new User(rs.getDate(3), rs.getString(1), rs.getString(2), rs.getString(4), rs.getString(5), rs.getString(7));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JLabel lblPhotographerListing = new JLabel("Info For " + userName);
		lblPhotographerListing.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblPhotographerListing.setBounds(6, 11, 194, 33);
		contentPane.add(lblPhotographerListing);
		
		JButton btnBackToMain = new JButton("Back To Main Menu");
		btnBackToMain.setBounds(249, 11, 180, 23);
		btnBackToMain.addActionListener(parent.actionEvents);
		contentPane.add(btnBackToMain);
		
		JLabel lblPhoneNumber = new JLabel("First Name:");
		lblPhoneNumber.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblPhoneNumber.setBounds(6, 79, 69, 33);
		contentPane.add(lblPhoneNumber);
		
		JLabel lblNewLabel = new JLabel("Last Name:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblNewLabel.setBounds(214, 79, 69, 33);
		contentPane.add(lblNewLabel);
		
		JLabel lblBiography = new JLabel("Biography:");
		lblBiography.setEnabled(true);
		lblBiography.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblBiography.setBounds(6, 113, 162, 33);
		contentPane.add(lblBiography);
		
		JLabel lblNewLabel_1 = new JLabel(u.getFirstName());
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(76, 79, 124, 33);
		contentPane.add(lblNewLabel_1);
		
		JLabel label = new JLabel(u.getLastName());
		label.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		label.setBounds(293, 79, 131, 33);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel(u.getBio());
		label_1.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		label_1.setBounds(85, 123, 340, 105);
		contentPane.add(label_1);
		
		JLabel lblDateJoined = new JLabel("Date Joined:");
		lblDateJoined.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblDateJoined.setBounds(6, 45, 69, 33);
		contentPane.add(lblDateJoined);
		
		JLabel label_2 = new JLabel(u.getDate());
		label_2.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		label_2.setBounds(76, 45, 124, 33);
		contentPane.add(label_2);
	}
	
	public String getPhotographerOfImage()
	{
		return photographerName;
	}
}
