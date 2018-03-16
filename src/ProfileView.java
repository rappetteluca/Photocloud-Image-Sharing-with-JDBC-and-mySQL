import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.JList;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileView extends JFrame implements ListSelectionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ImageSelection selectedImage;
	private String photographerName;
	private Database instance;
	private GUI parent;
	private JList<ImageSelection> list;

	/**
	 * Create the frame.
	 */
	public ProfileView(String userName, Database db, GUI g) {
		parent = g;
		instance = db;
		photographerName = new String(userName);
		setTitle("Viewing Profile: " + userName);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAddImage = new JButton("View Image");
		btnAddImage.setBounds(249, 51, 180, 23);
		btnAddImage.addActionListener(parent.actionEvents);
		contentPane.add(btnAddImage);
		
		JButton btnViewPhotographer = new JButton("View User Information");
		btnViewPhotographer.addActionListener(parent.actionEvents);
		btnViewPhotographer.setBounds(249, 119, 180, 23);
		contentPane.add(btnViewPhotographer);
		//DO SQL: MAKE THIS A METHOD. Have it produce a result set from the DB where each element is added
		//Like Below. These are image Names for a specific Photographer (given by photographerName).
		DefaultListModel<ImageSelection> model = new DefaultListModel<ImageSelection>();
		ResultSet rs = instance.showPhotographerPictures(photographerName);
		try {
			while (rs.next())
			{
				ImageSelection i = new ImageSelection(rs.getInt(2), rs.getString(3), rs.getDouble(4), rs.getString(5), rs.getString(6), photographerName);
				model.addElement(i);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		list = new JList<ImageSelection>(model);
		list.setBounds(6, 40, 234, 205);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setSelectedIndex(0);
		list.addListSelectionListener(this);
		list.setVisibleRowCount(-1);
		JScrollPane listScroller = new JScrollPane();
		listScroller.setViewportView(list);
		listScroller.setBounds(6, 40, 234, 205);
		contentPane.add(listScroller);
		list.setSelectedIndex(0);
		
		JLabel lblPhotographerListing = new JLabel("Photos by " + userName);
		lblPhotographerListing.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblPhotographerListing.setBounds(6, 11, 194, 33);
		contentPane.add(lblPhotographerListing);
		
		JButton btnViewYourProfile = new JButton("View Your Profile");
		btnViewYourProfile.setBounds(249, 85, 180, 23);
		btnViewYourProfile.addActionListener(parent.actionEvents);
		contentPane.add(btnViewYourProfile);
		
		JLabel lblCurrentlyLoggedIn = new JLabel("Currently Logged In As:");
		lblCurrentlyLoggedIn.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblCurrentlyLoggedIn.setBounds(249, 141, 146, 39);
		contentPane.add(lblCurrentlyLoggedIn);
		
		JLabel lblUsername = new JLabel(parent.getUsername());
		lblUsername.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblUsername.setBounds(269, 165, 136, 23);
		contentPane.add(lblUsername);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(parent.actionEvents);
		btnLogout.setBounds(249, 222, 180, 23);
		contentPane.add(btnLogout);
		
		JButton btnBackToMain = new JButton("Back To Main Menu");
		btnBackToMain.setBounds(249, 191, 180, 23);
		btnBackToMain.addActionListener(parent.actionEvents);
		contentPane.add(btnBackToMain);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) 
	{
		if (e.getValueIsAdjusting() == false)
		{
			if (list.getSelectedIndex() > -1)
			{
				selectedImage = list.getSelectedValue();
			}
		}
		
	}
	
	public ImageSelection getSelectedImage()
	{
		selectedImage = list.getSelectedValue();
		return selectedImage;
	}
	
	public String getPhotographerOfImage()
	{
		return photographerName;
	}
}
