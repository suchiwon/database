package DB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnector {
	
	private static DBConnector instance;
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pStmt;
	
	private DBConnector() throws ClassNotFoundException, SQLException {
		Class.forName("org.mariadb.jdbc.Driver");
		
		String url = "jdbc:mariadb://127.0.0.1:3306/sugang";
		String id = "root";
		String pw = "test";
		
		conn = DriverManager.getConnection(url,id,pw);
		stmt = conn.createStatement();
	}
	
	public static DBConnector getInstance() throws ClassNotFoundException, SQLException {
		if (instance == null) {
			instance = new DBConnector();
		}
		
		return instance;
	}
	
	public ResultSet ExecuteQuery(String query) throws SQLException{
		ResultSet resultSet = stmt.executeQuery(query);
		
		return resultSet;
	}
	
	
	public void close() {
		try {
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Statement getStmt() {
		return this.stmt;
	}
	
	public PreparedStatement getPreparedStatement(String query) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement(query);
		
		return stmt;
	}
}
