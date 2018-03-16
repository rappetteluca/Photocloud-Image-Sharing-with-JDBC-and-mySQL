import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ViewImagePane extends JFrame 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String photographerName;
	private ImageSelection image;
	private String imageFilepath;
	private GUI parent;

	/**
	 * Create the frame.
	 */
	public ViewImagePane(ImageSelection selectedImage, String photographerOfImage, Database db, GUI g) 
	{
		try
		{
			setTitle("Image Preview for " + selectedImage.getTitle());
			image = selectedImage;
			photographerName = photographerOfImage;
			parent = g;
			imageFilepath = image.getFilePath();
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 450, 300);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			Image image = ImageIO.read(new File(imageFilepath));
			JLabel ImageDisplay = new JLabel(new ImageIcon(getScaledImage(image, 267, 223)));
			ImageDisplay.setBounds(10, 20, 267, 223);
			contentPane.add(ImageDisplay);
			
			JButton btnPurchaseImage = new JButton("Purchase Image");
			btnPurchaseImage.setBounds(287, 102, 146, 23);
			btnPurchaseImage.addActionListener(parent.actionEvents);
			contentPane.add(btnPurchaseImage);
			
			JButton btnBackToPhotographer = new JButton("Back To Photos");
			btnBackToPhotographer.setBounds(287, 136, 146, 23);
			btnBackToPhotographer.addActionListener(parent.actionEvents);
			contentPane.add(btnBackToPhotographer);
			
			JButton btnNewButton = new JButton("Main Menu");
			btnNewButton.setBounds(287, 238, 146, 23);
			btnNewButton.addActionListener(parent.actionEvents);
			contentPane.add(btnNewButton);
			
			JButton btnLogout = new JButton("Logout");
			btnLogout.setBounds(287, 204, 146, 23);
			btnLogout.addActionListener(parent.actionEvents);
			contentPane.add(btnLogout);
			
			JButton btnViewAllPurchases = new JButton("View All Purchases");
			btnViewAllPurchases.setBounds(287, 170, 146, 23);
			btnViewAllPurchases.addActionListener(parent.actionEvents);
			contentPane.add(btnViewAllPurchases);
			
			JLabel lblPrice = new JLabel("Price:");
			lblPrice.setFont(new Font("Times New Roman", Font.BOLD, 13));
			lblPrice.setBounds(287, 0, 46, 25);
			contentPane.add(lblPrice);
			
			JLabel lblDescription = new JLabel("Description:");
			lblDescription.setFont(new Font("Times New Roman", Font.BOLD, 13));
			lblDescription.setBounds(287, 20, 75, 25);
			contentPane.add(lblDescription);
			
			StringBuilder sb = new StringBuilder();
			sb.append("<html>");
			sb.append(this.image.getDescription());
			sb.append("</html>");
			JLabel description = new JLabel(sb.toString());
			description.setBounds(287, 40, 146, 57);
			contentPane.add(description);
			
			sb = new StringBuilder();
			sb.append("$");
			sb.append(new Double(this.image.getPrice()).toString());
			JLabel price = new JLabel(sb.toString());
			price.setBounds(334, 5, 99, 14);
			contentPane.add(price);
			sb = null;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public String getPhotographerName()
	{
		return photographerName;
	}
	
	public ImageSelection getImage()
	{
		return image;
	}
	
	public String getFilePath()
	{
		return imageFilepath;
	}
	
	private Image getScaledImage(Image srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();

	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();

	    return resizedImg;
	}
}
