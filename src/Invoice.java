

public class Invoice {
	private int invoiceID;
	private java.sql.Date date;
	private double salePrice;
	private String customerId;
	private String photographerId;
	private String imageFilePath;
	private int imageId;
	
	public Invoice (double salePrice, int imageId, String customerId, String photographerId)
	{
		setSalePrice(salePrice);
		setImageId(imageId);
		setCustomerId(customerId);
		setPhotographerId(photographerId);
	}

	public Invoice(java.sql.Date date, double price, int imageId, String customerId, String photographerId) 
	{
		setPurchaseDate(date);
		setSalePrice(price);
		setPhotographerId(photographerId);
		setCustomerId(customerId);
		setImageId(imageId);
		
	}
	public int getInvoiceID() 
	{
		return invoiceID;
	}

	public void setInvoiceID(int invoiceID) {
		
		this.invoiceID = invoiceID;
	}

	public String getPurchaseDate() {
		return date.toString();
	}

	public void setPurchaseDate(java.sql.Date purchaseDate) {
		this.date = purchaseDate;
	}

	public double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getPhotographerId() {
		return photographerId;
	}

	public void setPhotographerId(String photographerId) {
		this.photographerId = photographerId;
	}

	public String getImageFilePath() {
		return imageFilePath;
	}

	public void setImageFilePath(String imageFilePath) {
		this.imageFilePath = imageFilePath;
	}
	public int getImageId() {
		return imageId;
	}
	public void setImageId(int imageId) {
		this.imageId = imageId;
	}
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(salePrice);
		sb.append(" sent on ");
		sb.append(getPurchaseDate());
		return sb.toString();
	}
}
