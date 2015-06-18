package BaseClasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Constants.AppConstants;

public class BaseDAO {
	protected Connection conn = null;
	private String url = AppConstants.DATABASE.DB_URL;
	private String user = AppConstants.DATABASE.DB_USER;
	private String pass = AppConstants.DATABASE.DB_PASS;

	public void startConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException classNotFound) {
			classNotFound.printStackTrace();
		} catch (SQLException sqlError) {
			sqlError.printStackTrace();
		}
	}
	
	public void finishConnection(){
		//データベース切断
		if(conn != null){
			try{
				conn.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}	
	}
}
