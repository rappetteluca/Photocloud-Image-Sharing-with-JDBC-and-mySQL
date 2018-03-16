import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class AddImagePane {
	
	protected JFrame frame;
	private JPanel contentPane;
	private JTextField titleField;
	private JTextField priceField;
	private JTextArea descriptionField;
	private String userName;
	private Database instance;
	private ImageSelection image;
	private Image container;
	private String imageFilepath;
	private File imageFile;
	private GUI parent;

	/**
	 * Create the frame.
	 */
	public AddImagePane(String photographerOfImage, String filePath, Database db, GUI g) {
		frame = new JFrame();
		userName = photographerOfImage;
		imageFilepath = filePath;
		instance = db;
		parent = g;
		frame.setTitle("Image Preview for " + userName);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		container = null;
		try 
		{
			imageFile = new File(imageFilepath);
			container = ImageIO.read(imageFile);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		JLabel ImageDisplay = new JLabel(new ImageIcon(getScaledImage(container, 245, 239)));
		ImageDisplay.setBounds(6, 11, 245, 239);
		contentPane.add(ImageDisplay);
		
		JLabel lblImageTitle = new JLabel("Image Title");
		lblImageTitle.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblImageTitle.setBounds(261, 0, 163, 20);
		contentPane.add(lblImageTitle);
		
		titleField = new JTextField();
		titleField.setBounds(261, 23, 163, 20);
		contentPane.add(titleField);
		titleField.setColumns(10);
		
		JLabel lblImagePrice = new JLabel("Image Price");
		lblImagePrice.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblImagePrice.setBounds(261, 45, 163, 20);
		contentPane.add(lblImagePrice);
		
		priceField = new JTextField();
		priceField.setBounds(261, 68, 163, 20);
		contentPane.add(priceField);
		priceField.setColumns(10);
		
		JLabel lblImageDescription = new JLabel("Image Description");
		lblImageDescription.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblImageDescription.setBounds(261, 91, 163, 20);
		contentPane.add(lblImageDescription);
		
		descriptionField = new JTextArea();
		descriptionField.setBounds(261, 114, 163, 67);
		contentPane.add(descriptionField);
		descriptionField.setColumns(10);
		descriptionField.setLineWrap(true);
		
		JButton btnUpload = new JButton("Upload");
		btnUpload.setBounds(261, 192, 163, 23);
		btnUpload.addActionListener(parent.actionEvents);
		contentPane.add(btnUpload);
		
		JButton btnNewButton = new JButton("Cancel Upload");
		btnNewButton.setBounds(261, 227, 163, 23);
		btnNewButton.addActionListener(parent.actionEvents);
		contentPane.add(btnNewButton);
	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public void setContentPane(JPanel contentPane) {
		this.contentPane = contentPane;
	}

	public JTextField getTitleField() {
		return titleField;
	}

	public void setTitleField(JTextField titleField) {
		this.titleField = titleField;
	}

	public JTextField getPriceField() {
		return priceField;
	}

	public void setPriceField(JTextField priceField) {
		this.priceField = priceField;
	}

	public JTextArea getDescriptionField() {
		return descriptionField;
	}

	public void setDescriptionField(JTextArea descriptionField) {
		this.descriptionField = descriptionField;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Database getInstance() {
		return instance;
	}

	public void setInstance(Database instance) {
		this.instance = instance;
	}

	public String getImageFilepath() {
		return imageFilepath;
	}

	public void setImageFilepath(String imageFilepath) {
		this.imageFilepath = imageFilepath;
	}

	public void setParent(GUI parent) {
		this.parent = parent;
	}

	public ImageSelection getImage() 
	{
		image = new ImageSelection(0, imageFilepath, Double.parseDouble(priceField.getText()), titleField.getText(), descriptionField.getText(), userName);
		return image;
	}

	public void setImage(ImageSelection image) {
		this.image = image;
	}
	
	private Image getScaledImage(Image srcImg, int w, int h)
	{
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();

	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();

	    return resizedImg;
	}
}
