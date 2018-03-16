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

public class UIPane extends JFrame implements ListSelectionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String selectedPhotographer;
	private GUI parent;
	private JList<String> list;
	private Database instance;

	/**
	 * Create the frame.
	 */
	public UIPane(GUI g, Database d) {
		parent = g;
		instance = d;
		setTitle("Photo Cloud Main Screen");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAddImage = new JButton("Add Image To Profile");
		btnAddImage.setBounds(249, 51, 180, 23);
		btnAddImage.addActionListener(parent.actionEvents);
		contentPane.add(btnAddImage);
		
		JButton btnViewPhotographer = new JButton("View Photographer");
		btnViewPhotographer.addActionListener(parent.actionEvents);
		btnViewPhotographer.setBounds(249, 119, 180, 23);
		contentPane.add(btnViewPhotographer);
		//DO SQL: MAKE THIS A METHOD. Have the Database query for the names of All photographers and return
		//A result set. For each element of the result set add it's First and Last name as a list entry.
		//An example is shown below.
		DefaultListModel<String> model = new DefaultListModel<String>();
		ResultSet rs = instance.showPhotographers();
		try {
			while (rs.next())
			{
				model.addElement(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		list = new JList<String>(model);
		list.setBounds(6, 40, 234, 205);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.addListSelectionListener(this);
		list.setSelectedIndex(0);
		list.setVisibleRowCount(-1);
		JScrollPane listScroller = new JScrollPane();
		listScroller.setViewportView(list);
		listScroller.setBounds(6, 40, 234, 205);
		contentPane.add(listScroller);
		
		JLabel lblPhotographerListing = new JLabel("Photographer Listing");
		lblPhotographerListing.setFont(new Font("Traditional Arabic", Font.PLAIN, 16));
		lblPhotographerListing.setBounds(6, 11, 194, 33);
		contentPane.add(lblPhotographerListing);
		
		JButton btnViewYourProfile = new JButton("View Your Profile");
		btnViewYourProfile.setBounds(249, 85, 180, 23);
		btnViewYourProfile.addActionListener(parent.actionEvents);
		contentPane.add(btnViewYourProfile);
		
		JLabel lblCurrentlyLoggedIn = new JLabel("Currently Logged In As:");
		lblCurrentlyLoggedIn.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblCurrentlyLoggedIn.setBounds(259, 153, 146, 39);
		contentPane.add(lblCurrentlyLoggedIn);
		
		JLabel lblUsername = new JLabel(parent.getUsername());
		lblUsername.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblUsername.setBounds(269, 184, 136, 23);
		contentPane.add(lblUsername);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(parent.actionEvents);
		btnLogout.setBounds(249, 222, 175, 23);
		contentPane.add(btnLogout);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) 
	{
		if (e.getValueIsAdjusting() == false)
		{
			if (list.getSelectedIndex() > -1)
			{
				selectedPhotographer = list.getSelectedValue();
			}
		}
		
	}
	
	public String getSelectedPhotographer()
	{
		return selectedPhotographer;
	}
}
