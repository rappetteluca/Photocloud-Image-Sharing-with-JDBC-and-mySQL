
public class User {
	
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String bio;
	private java.sql.Date date;
	
	public User(String username, String password, String firstName, String lastName, String bio){
		setUsername(new String(username));
		setPassword(new String (password));
		setFirstName(new String (firstName));
		setLastName(new String (lastName));
		setBio(new String (bio));
	}
	
	public User(java.sql.Date date, String username, String password, String firstName, String lastName, String bio){
		setDate(date);
		setUsername(new String(username));
		setPassword(new String (password));
		setFirstName(new String (firstName));
		setLastName(new String (lastName));
		setBio(new String (bio));
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public java.sql.Date getDateObject() {
		return date;
	}
	
	public String getDate() {
		return date.toString();
	}

	public void setDate(java.sql.Date date) {
		this.date = date;
	}

}
