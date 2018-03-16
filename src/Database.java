import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Database {

	// set up our database connection
	private String url = "jdbc:mysql://localhost:3306/photocloud?verifyServerCertificate=false&useSSL=true";
	
	private Connection connection;
	
	public Database() 
	{
		try {
			connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public boolean deleteFromPortfolio(String filePath) throws SQLException {
		try{
			String deleteStmt = "DELETE FROM Image WHERE filePath LIKE ?";
			PreparedStatement delete = connection.prepareStatement(deleteStmt);
			delete.setString(1, filePath);
			delete.executeUpdate();
			return true;
		} catch (SQLException e) {
			return false;
		}
		
	}
	
	public boolean addImageFile(ImageSelection pic) throws SQLException {

		try{	
			String insertStmt = "INSERT INTO Image (FilePath, Price, Title, Description, Username) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement insert = connection.prepareStatement(insertStmt);
			
			insert.setString(1, pic.getFilePath());
			insert.setDouble(2, pic.getPrice());
			insert.setString(3, pic.getTitle());
			insert.setString(4, pic.getDescription());
			insert.setString(5, pic.getPhotographerId());

			insert.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean insertNewUser(User prof, String confirmPassword) throws SQLException {
		if(!confirmPassword.equals(prof.getPassword())){
			return false;
		}
		try{	
			String insertStmt = "INSERT INTO User (Username, Password"
					+ ", FirstName, LastName, Biography) "
					+ "VALUES (?,?,?,?,?)";
			PreparedStatement insert = connection.prepareStatement(insertStmt);

			insert.setString(1, prof.getUsername());
			insert.setString(2, prof.getPassword());
			insert.setString(3, prof.getFirstName());
			insert.setString(4, prof.getLastName());
			if(prof.getBio() == null)
			{
				insert.setString(5, new String(""));
			}
			else
				insert.setString(5, prof.getBio());

			insert.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean updateBillingInfo(String username, Billing info)
	{
		
		try{	
			String updateStatement = "UPDATE User " + "SET BillingAddress = ?, " +
		"CardholdersName = ?, CreditCard = ?, CCV = ?, ZipCode = ?, ExpirationDate = ?, Phone = ?" +
					" WHERE Username LIKE ?;";
			PreparedStatement insert = connection.prepareStatement(updateStatement);

			insert.setString(1, info.getBillingAddress());
			insert.setString(2, info.getCardHolderName());
			insert.setString(3, info.getCardNumber());
			insert.setInt(4, info.getCVC());
			insert.setInt(5, info.getZipCode());
			insert.setString(6, info.getExpiration());
			insert.setString(7, info.getPhoneNumber());
			insert.setString(8, username);

			insert.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public ResultSet showPhotographerPictures(String username) {
		PreparedStatement queryStmt;
		ResultSet rs = null;
		String query = "SELECT * FROM Image NATURAL JOIN User WHERE Username LIKE ?;";
		try {
			queryStmt = connection.prepareStatement(query);
			queryStmt.setString(1, username);
		} catch (SQLException e) {
			return null;
		}
		try {
			rs = queryStmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return rs;
	}
	
	public ResultSet showSinglePhotographer(String username) {
		PreparedStatement queryStmt;
		ResultSet rs = null;
		String query = "SELECT * FROM User WHERE Username LIKE ?;";
		try {
			queryStmt = connection.prepareStatement(query);
			queryStmt.setString(1, username);
		} catch (SQLException e) {
			return null;
		}
		try {
			rs = queryStmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return rs;
	}
	
	public ResultSet showPhotographers() {
		//Could be filtered for only users who have posted a photo.
		PreparedStatement queryStmt;
		ResultSet rs = null;
		String query = "SELECT Username FROM User;";
		try {
			queryStmt = connection.prepareStatement(query);
		} catch (SQLException e) {
			return null;
		}
		try {
			rs = queryStmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return rs;
	}
	
	public ResultSet validateUser(String username){
		PreparedStatement queryStmt;
		ResultSet rs = null;
		String query = "SELECT Password FROM User WHERE Username LIKE ?;";
		try {
			queryStmt = connection.prepareStatement(query);
			queryStmt.setString(1, username);
			rs = queryStmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();;
		}
		return rs;
	}
	
	public boolean purchaseImage(Invoice invoice){
		try{	
			String insertStmt = "INSERT INTO Invoice (Username, PhotographerID"
					+ ", SalePrice, ImageID) "
					+ "VALUES (?,?,?,?)";
			PreparedStatement insert = connection.prepareStatement(insertStmt);

			insert.setDouble(3, invoice.getSalePrice());
			insert.setString(1, invoice.getCustomerId());
			insert.setString(2, invoice.getPhotographerId());
			insert.setInt(4, invoice.getImageId());

			insert.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public ResultSet getAllInvoices(String username){
		PreparedStatement queryStmt;
		ResultSet rs = null;
		String query = "SELECT * FROM Invoice JOIN Image ON Invoice.ImageID = Image.ImageID WHERE Invoice.Username LIKE ?;";
		try {
			queryStmt = connection.prepareStatement(query);
			queryStmt.setString(1, username);
			rs = queryStmt.executeQuery();
			System.out.println(rs.getFetchSize());
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return rs;
	}
	public ResultSet getImage(String filePath) {
		PreparedStatement queryStmt;
		ResultSet rs = null;
		String query = "SELECT * FROM Image WHERE FilePath LIKE ?;";
		try {
			queryStmt = connection.prepareStatement(query);
			queryStmt.setString(1, filePath);
		} catch (SQLException e) {
			return null;
		}
		try {
			rs = queryStmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return rs;
	}
	
	public ResultSet getImageFromID(int id) {
		PreparedStatement queryStmt;
		ResultSet rs = null;
		String query = "SELECT * FROM Image WHERE ImageId = ?;";
		try {
			queryStmt = connection.prepareStatement(query);
			queryStmt.setInt(1, id);
		} catch (SQLException e) {
			return null;
		}
		try {
			rs = queryStmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return rs;
	}

	
	public void connect() throws SQLException {
		connection = DriverManager.getConnection(url, "username", "password");
	}
	
	public void disconnect() throws SQLException {
		connection.close();
	}

	public boolean deleteUser(String userName) throws SQLException {
		try{
			String deleteStmt = "DELETE FROM User WHERE Username LIKE ?";
			PreparedStatement delete = connection.prepareStatement(deleteStmt);
			delete.setString(1, userName);
			delete.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}

	

}
