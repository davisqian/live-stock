package connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import general.*;


public class DBUtils {

	private static final int CUSTOMER = 1;
	private static final int REP = 2;
	private static final int MANAGER = 3;

	public static UserAccount findUser(Connection conn, String userName, String password) throws SQLException {

		String sql = "SELECT L.Usr, L.Pwd, L.AccType, L.Id FROM Login L "
				+ " WHERE L.Usr = ? AND L.Pwd = ?";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, userName);
		pstm.setString(2, password);
		ResultSet rs = pstm.executeQuery();

		if (rs.next()) {
			String usr = rs.getString("Usr");
			String pwd = rs.getString("Pwd");
			int accType = rs.getInt("AccType");
			int id = rs.getInt("Id");

			if(accType == CUSTOMER)
			{
				String sql2 = "SELECT C.FirstName, C.LastName,"
							+ "C.Address, C.City, C.State,"
							+ "C.ZipCode, C.Telephone, C.Email,"
							+ "C.Rating, C.CusId FROM Customer C"
							+ "WHERE C.CusId = ?";
				PreparedStatement pstm2 = conn.prepareStatement(sql2);
				pstm2.setInt(1, id);
				ResultSet rs2 = pstm.executeQuery();
				
				if(rs2.next())
				{
					String first = rs.getString("FirstName");
					String last = rs.getString("LastName");
					String addr = rs.getString("Address");
					String city = rs.getString("City");
					String state = rs.getString("State");
					String zip = rs.getString("ZipCode");
					String phone = rs.getString("Telephone");
					String email = rs.getString("Email");
					int rating = rs.getInt("Rating");
					int cusId = rs.getInt("CusId");
					
					UserAccount user = new CustomerAccount(cusId, first,
										last, usr, pwd, addr, city, state,
										zip, phone, email, rating);
				}
			}
			else if(accType == REP)
			{

			}
			else if(accType == MANAGER)
			{

			}
			else
			{
				return null;
			}

			return user;
		}
		return null;
	}

	public static UserAccount findUser(Connection conn, String userName) throws SQLException {

		String sql = "Select a.User_Name, a.Password, a.Gender from User_Account a " + " where a.User_Name = ? ";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, userName);

		ResultSet rs = pstm.executeQuery();

		if (rs.next()) {
			String password = rs.getString("Password");
			String gender = rs.getString("Gender");
			UserAccount user = new UserAccount();
			user.setUserName(userName);
			user.setPassword(password);
			user.setGender(gender);
			return user;
		}
		return null;
	}

/*
	public static List<Product> queryProduct(Connection conn) throws SQLException {
		String sql = "Select a.Code, a.Name, a.Price from Product a ";

		PreparedStatement pstm = conn.prepareStatement(sql);

		ResultSet rs = pstm.executeQuery();
		List<Product> list = new ArrayList<Product>();
		while (rs.next()) {
			String code = rs.getString("Code");
			String name = rs.getString("Name");
			float price = rs.getFloat("Price");
			Product product = new Product();
			product.setCode(code);
			product.setName(name);
			product.setPrice(price);
			list.add(product);
		}
		return list;
	}

	public static Product findProduct(Connection conn, String code) throws SQLException {
		String sql = "Select a.Code, a.Name, a.Price from Product a where a.Code=?";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, code);

		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			String name = rs.getString("Name");
			float price = rs.getFloat("Price");
			Product product = new Product(code, name, price);
			return product;
		}
		return null;
	}

	public static void updateProduct(Connection conn, Product product) throws SQLException {
		String sql = "Update Product set Name =?, Price=? where Code=? ";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, product.getName());
		pstm.setFloat(2, product.getPrice());
		pstm.setString(3, product.getCode());
		pstm.executeUpdate();
	}

	public static void insertProduct(Connection conn, Product product) throws SQLException {
		String sql = "Insert into Product(Code, Name,Price) values (?,?,?)";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, product.getCode());
		pstm.setString(2, product.getName());
		pstm.setFloat(3, product.getPrice());

		pstm.executeUpdate();
	}

	public static void deleteProduct(Connection conn, String code) throws SQLException {
		String sql = "Delete Product where Code= ?";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, code);

		pstm.executeUpdate();
	}
*/
}