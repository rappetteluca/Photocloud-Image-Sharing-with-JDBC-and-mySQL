
public class Billing {
	
	private String billingInfo;
	private String cardHolderName;
	private String cardNumber;
	private int cvc;
	private String expiration;
	private int zipCode;
	private String phone;
	
	public Billing (String address, String cardHolderName, String cardNumber, int cvc, String expiration, int zipCode, String phoneNumber){
		setBillingAddress(new String(address));
		setCardHolderName(new String(cardHolderName));
		setCardNumber(cardNumber);
		setCVC(cvc);
		setExpiration(new String(expiration));
		setZipCode(zipCode);
		setPhoneNumber(phoneNumber);
	}

	public String getBillingAddress() {
		return billingInfo;
	}

	public void setBillingAddress(String billingInfo) {
		this.billingInfo = billingInfo;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public int getCVC() {
		return cvc;
	}

	public void setCVC(int cvc) {
		this.cvc = cvc;
	}

	public String getExpiration() {
		return expiration;
	}

	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	
	public void setPhoneNumber(String num) {
		this.phone = num;
	}
	public String getPhoneNumber()
	{
		return phone;
	}

}
