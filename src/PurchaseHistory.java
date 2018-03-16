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

public class PurchaseHistory extends JFrame implements ListSelectionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String userName;
	private GUI parent;
	private JList<Invoice> list;
	private Database instance;
	private Invoice selectedInvoice;
	private String lastViewedUser;

	/**
	 * Create the frame.
	 */
	public PurchaseHistory(String user, GUI g, Database d, String lastViewed) 
	{
		userName = user;
		parent = g;
		instance = d;
		lastViewedUser = lastViewed;
		setTitle("Purchase History For " + userName);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnViewPhotographer = new JButton("View Purchase");
		btnViewPhotographer.addActionListener(parent.actionEvents);
		btnViewPhotographer.setBounds(250, 40, 180, 23);
		contentPane.add(btnViewPhotographer);
		//DO SQL: MAKE THIS A METHOD. Have the Database query for the names of All photographers and return
		//A result set. For each element of the result set add it's First and Last name as a list entry.
		//An example is shown below.
		DefaultListModel<Invoice> model = new DefaultListModel<Invoice>();
		System.out.println(userName);
		ResultSet rs = instance.getAllInvoices(userName);
		try {
			while (rs.next())
			{
				Invoice invoice = new Invoice(rs.getDate(5), rs.getDouble(6), rs.getInt(4), userName, rs.getString(2));
				model.addElement(invoice);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		list = new JList<Invoice>(model);
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
		
		JLabel lblPhotographerListing = new JLabel("Purchase History");
		lblPhotographerListing.setFont(new Font("Traditional Arabic", Font.PLAIN, 16));
		lblPhotographerListing.setBounds(6, 11, 194, 33);
		contentPane.add(lblPhotographerListing);
		
		JButton btnViewYourProfile = new JButton("View Your Profile");
		btnViewYourProfile.setBounds(250, 74, 180, 23);
		btnViewYourProfile.addActionListener(parent.actionEvents);
		contentPane.add(btnViewYourProfile);
		
		JLabel lblCurrentlyLoggedIn = new JLabel("Currently Logged In As:");
		lblCurrentlyLoggedIn.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblCurrentlyLoggedIn.setBounds(250, 158, 146, 39);
		contentPane.add(lblCurrentlyLoggedIn);
		
		JLabel lblUsername = new JLabel(parent.getUsername());
		lblUsername.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblUsername.setBounds(269, 191, 136, 23);
		contentPane.add(lblUsername);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(parent.actionEvents);
		btnLogout.setBounds(249, 222, 175, 23);
		contentPane.add(btnLogout);
		
		JButton btnNewButton = new JButton("Back To Main Menu");
		btnNewButton.setBounds(250, 108, 180, 23);
		btnNewButton.addActionListener(parent.actionEvents);
		contentPane.add(btnNewButton);
		
		JButton btnBackToPhotos = new JButton("Back To Photographer");
		btnBackToPhotos.addActionListener(parent.actionEvents);
		btnBackToPhotos.setBounds(250, 142, 180, 23);
		contentPane.add(btnBackToPhotos);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLastViewedUser() {
		return lastViewedUser;
	}

	public void setLastViewedUser(String lastViewedUser) {
		this.lastViewedUser = lastViewedUser;
	}

	public void setSelectedInvoice(Invoice selectedInvoice) {
		this.selectedInvoice = selectedInvoice;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) 
	{
		if (e.getValueIsAdjusting() == false)
		{
			if (list.getSelectedIndex() > -1)
			{
				selectedInvoice = list.getSelectedValue();
			}
		}
		
	}
	
	public Invoice getSelectedInvoice()
	{
		if (selectedInvoice == null)
		{
			selectedInvoice = list.getSelectedValue();
		}
		
		return selectedInvoice;
	}
}
