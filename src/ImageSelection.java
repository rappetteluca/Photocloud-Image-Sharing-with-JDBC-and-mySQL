
public class ImageSelection {
	
	private int imageID;
	private String filePath;
	private double price;
	private String title;
	private String description;
	private String photographerId;
	
	public ImageSelection (int imageID, String filePath, double price, String title, String description, String photographerId){
		setImageID(imageID);
		setFilePath(filePath);
		setPrice(price);
		setTitle(title);
		setDescription(description);
		setPhotographerId(photographerId);
	}
	
	public ImageSelection (String filePath){
		this.filePath = filePath;
	}

	public int getImageID() {
		return imageID;
	}

	public void setImageID(int imageID) {
		this.imageID = imageID;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhotographerId() {
		return photographerId;
	}

	public void setPhotographerId(String photographerId) {
		this.photographerId = photographerId;
	}
	
	public String toString()
	{
		return this.title;
	}
}
