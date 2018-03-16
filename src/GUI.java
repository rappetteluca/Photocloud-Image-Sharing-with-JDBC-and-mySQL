import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GUI
{
	public ActionListener actionEvents;
	private JFrame currentFrame;
	private Database db;
	private Login loginScreen;
	private Register registerScreen;
	private ProfileView profileScreen;
	private ViewImagePane imageDisplayScreen;
	private AddImagePane imageUploadScreen;
	private BillingPane registerBillingInfo;
	private UIPane mainUserScreen;
	private PurchaseHistory historyScreen;
	private UserInfoPane userInfoScreen;
	private String userName = "root";
	private static GUI selfReference;
	private JFileChooser upload;
	
	public static void copyFile(File fromFile, File toFile) throws IOException 
	{
		FileInputStream fromFileStream = new FileInputStream(fromFile);
		FileOutputStream toFileStream = new FileOutputStream(toFile);
		FileChannel fromChannel = fromFileStream.getChannel();
		FileChannel toChannel = toFileStream.getChannel();
		System.out.println("Old File Path: " + fromFile.getAbsolutePath());
		System.out.println("New File Path: " + toFile.getAbsolutePath());
		try {
			fromChannel.transferTo(0, fromChannel.size(), toChannel);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
			if (fromChannel != null)
				fromChannel.close();
			if (toChannel != null)
				toChannel.close();

			fromFileStream.close();
			toFileStream.close();
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() {
				try {
					selfReference = new GUI();
					selfReference.currentFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		actionEvents = 
				new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				if(arg0.getSource() instanceof JButton)
				{
					JButton event = (JButton) arg0.getSource();
					if (event.getText().equals("Register"))
					{
						registerScreen = new Register(selfReference);
						registerScreen.setVisible(true);
						currentFrame.dispose();
						loginScreen = null;
						currentFrame = registerScreen;
					}
					else if (event.getText().equals("Login"))
					{
						try {
							ResultSet rs = db.validateUser(loginScreen.getUsername());

							if(rs != null && rs.next())
							{
								if (rs.getString(1).equals(loginScreen.getPassword()))
								{
									userName = loginScreen.getUsername();
									mainUserScreen = new UIPane(selfReference, db);
									mainUserScreen.setVisible(true);
									currentFrame.dispose();
									registerScreen = null;
									currentFrame = mainUserScreen;
								}
							}
							else
							{
								JOptionPane.showMessageDialog(currentFrame, "Incorrect Username or Password. \nUsername: " + loginScreen.getUsername() +
										" Password: " + loginScreen.getPassword());
							}
						} catch (HeadlessException | SQLException e) 
						{
							e.printStackTrace();
						}
					}
					else if (event.getText().equals("Create User / Billing Info"))
					{
						User user = new User(registerScreen.getUsername(), registerScreen.getPassword(), registerScreen.getFirstName(), registerScreen.getLastName(), registerScreen.getBioText());
						try {		
							if (db.insertNewUser(user, registerScreen.getPasswordConfirm()))
							{
								JOptionPane.showMessageDialog(currentFrame, "Successful Account Creation");
								registerBillingInfo = new BillingPane(selfReference, registerScreen.getUsername());
								registerBillingInfo.setVisible(true);
								currentFrame.dispose();
								registerScreen = null;
								currentFrame = registerBillingInfo;
							}
							else
							{
								JOptionPane.showMessageDialog(currentFrame, "Error: Username may already be taken or passwords don't match.");
							}
						} catch (HeadlessException | SQLException e) 
						{
							e.printStackTrace();
						}
					}
					else if (event.getText().equals("Confirm"))
					{							
						Billing billingInfo = new Billing(registerBillingInfo.getCCAddress(), registerBillingInfo.getCCName(), registerBillingInfo.getCCNumber(), Integer.parseInt(registerBillingInfo.getCCV()), registerBillingInfo.getExpiration(), Integer.parseInt(registerBillingInfo.getCCZipCode()), registerBillingInfo.getPhoneNumber());
						if (db.updateBillingInfo(registerBillingInfo.getUsername(), billingInfo))
						{
							loginScreen = new Login(selfReference);
							loginScreen.frame.setVisible(true);
							currentFrame.dispose();
							registerScreen = null;
							registerBillingInfo = null;
							currentFrame = loginScreen.frame;
						}
						else
						{
							JOptionPane.showMessageDialog(currentFrame, "Error in Updating Billing Info. Bad Fields?");
						}
					}
					else if (event.getText().equals("Cancel"))
					{
						//Rollback user creation if billing info last screen.
						//I.E. Billing info is required to register.
						if (registerBillingInfo != null)
						{
							try {
								db.deleteUser(registerBillingInfo.getUsername());
								JOptionPane.showMessageDialog(currentFrame, "Billing information is required for sign up. \nRolled back user creation.");
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						loginScreen = new Login(selfReference);
						loginScreen.frame.setVisible(true);
						currentFrame.dispose();
						registerScreen = null;
						registerBillingInfo = null;
						currentFrame = loginScreen.frame;
					}
					else if (event.getText().equals("Add Image To Profile"))
					{
						//Incomplete
						upload =  new JFileChooser();
						String path = null;
						int returnVal = upload.showOpenDialog(currentFrame);
						while (returnVal != JFileChooser.APPROVE_OPTION)
						{
							JOptionPane.showMessageDialog(currentFrame, "You need to select an image to add to your profile.");
							returnVal = upload.showOpenDialog(currentFrame);
						}
						File f = upload.getSelectedFile();
						path = f.getAbsolutePath();
						currentFrame.dispose();
						imageUploadScreen = new AddImagePane(userName, path, db, selfReference);
						upload.invalidate();
						currentFrame.dispose();
						upload = null;
						currentFrame = imageUploadScreen.frame;
						currentFrame.setVisible(true);
					}
					else if (event.getText().equals("View Photographer"))
					{
						profileScreen = new ProfileView(mainUserScreen.getSelectedPhotographer(), db, selfReference);
						profileScreen.setVisible(true);
						currentFrame.dispose();
						mainUserScreen = null;
						currentFrame = profileScreen;
					}
					else if (event.getText().equals("View Your Profile"))
					{
						profileScreen = new ProfileView(userName, db, selfReference);
						profileScreen.setVisible(true);
						currentFrame.dispose();
						mainUserScreen = null;
						historyScreen = null;
						currentFrame = profileScreen;
					}
					else if (event.getText().equals("Logout"))
					{
						try {
							db.disconnect();
						} catch (SQLException e) {
							e.printStackTrace();
						}
						currentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						currentFrame.dispose();
						System.exit(0);
					}
					else if (event.getText().equals("View User Information"))
					{
						userInfoScreen = new UserInfoPane(profileScreen.getPhotographerOfImage(), db, selfReference);
						userInfoScreen.setVisible(true);
						currentFrame.dispose();
						profileScreen = null;
						currentFrame = userInfoScreen;
					}
					else if (event.getText().equals("View Image"))
					{
						imageDisplayScreen = new ViewImagePane(profileScreen.getSelectedImage(), profileScreen.getPhotographerOfImage(), db, selfReference);
						imageDisplayScreen.setVisible(true);
						currentFrame.dispose();
						profileScreen = null;
						currentFrame = imageDisplayScreen;
					}
					else if (event.getText().equals("Back To Main Menu"))
					{
						mainUserScreen = new UIPane(selfReference, db);
						mainUserScreen.setVisible(true);
						currentFrame.dispose();
						profileScreen = null;
						userInfoScreen = null;
						currentFrame = mainUserScreen;
					}
					else if (event.getText().equals("Main Menu"))
					{
						mainUserScreen = new UIPane(selfReference, db);
						mainUserScreen.setVisible(true);
						currentFrame.dispose();
						imageDisplayScreen = null;
						currentFrame = mainUserScreen;
					}
					else if (event.getText().equals("Cancel Upload"))
					{
						mainUserScreen = new UIPane(selfReference, db);
						mainUserScreen.setVisible(true);
						currentFrame.dispose();
						imageUploadScreen = null;
						currentFrame = mainUserScreen;
					}
					else if (event.getText().equals("Upload"))
					{
						ImageSelection i = imageUploadScreen.getImage();
						try {
							if(db.addImageFile(i))
								JOptionPane.showMessageDialog(currentFrame, "Upload Successful!");
						} catch (SQLException e) 
						{
							e.printStackTrace();
						}
						
						mainUserScreen = new UIPane(selfReference, db);
						mainUserScreen.setVisible(true);
						currentFrame.dispose();
						imageUploadScreen = null;
						currentFrame = mainUserScreen;
					}

					else if (event.getText().equals("Purchase Image"))
					{
						try 
						{
							ImageSelection toPurchase = imageDisplayScreen.getImage();
							Invoice invoice;
							invoice = new Invoice(toPurchase.getPrice(), toPurchase.getImageID(), userName, toPurchase.getPhotographerId());
							db.purchaseImage(invoice);
							JOptionPane.showMessageDialog(currentFrame, "Purchase Successful! The Image will now be automatically downloaded to your desktop.");
							download(toPurchase);
							profileScreen = new ProfileView(imageDisplayScreen.getPhotographerName(), db, selfReference);
							profileScreen.setVisible(true);
							currentFrame.dispose();
							imageDisplayScreen = null;
							currentFrame = profileScreen;
						}	
						 catch (Exception e) 
						{
							JOptionPane.showMessageDialog(currentFrame, "Purchase failed.");
							
						}
					}
					else if (event.getText().equals("View All Purchases"))
					{
						historyScreen = new PurchaseHistory(userName, selfReference, db, imageDisplayScreen.getPhotographerName());
						historyScreen.setVisible(true);
						currentFrame.dispose();
						imageDisplayScreen = null;
						currentFrame = historyScreen;

					}
					else if (event.getText().equals("Back To Photos"))
					{
						profileScreen = new ProfileView(imageDisplayScreen.getPhotographerName(), db, selfReference);
						profileScreen.setVisible(true);
						currentFrame.dispose();
						imageDisplayScreen = null;
						currentFrame = profileScreen;
					}
					else if (event.getText().equals("Back To Photographer"))
					{
						profileScreen = new ProfileView(historyScreen.getLastViewedUser(), db, selfReference);
						profileScreen.setVisible(true);
						currentFrame.dispose();
						historyScreen = null;
						currentFrame = profileScreen;
					}
					else if (event.getText().equals("View Purchase"))
					{
						try
						{
							Invoice i = historyScreen.getSelectedInvoice();
							ResultSet rs = db.getImageFromID(i.getImageId());
							StringBuilder sb = new StringBuilder();
							sb.append(i.getSalePrice());
							sb.append(" was sent to ");
							sb.append(i.getPhotographerId());
							if (rs.next())
							{
								sb.append("\nFor the image titled: ");
								sb.append(rs.getString(5));
							}
							sb.append("\nOn the day ");
							sb.append(i.getPurchaseDate());
							JOptionPane.showMessageDialog(currentFrame, sb.toString());
						}
						catch (Exception e)
						{
							e.printStackTrace();
						}
					}
				}
			}
		};


		db = new Database();
		loginScreen = new Login(this);
		currentFrame = loginScreen.frame;
		currentFrame.setVisible(true);
	};
	
	private void download(ImageSelection is)
	{
		try
		{
			StringBuilder sb = new StringBuilder();
			File inFile;
			File toFile;
			
			inFile = new File(is.getFilePath());
			String userHomeFolder = System.getProperty("user.home");
			sb.append(userHomeFolder);
			sb.append("/Desktop/");
			toFile = new File(sb.toString(), inFile.getName());
			if (!toFile.exists())
			{
				toFile.createNewFile();
			}
			
			GUI.copyFile(inFile, toFile);
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public String getUsername()
	{
		return userName;
	}
}
