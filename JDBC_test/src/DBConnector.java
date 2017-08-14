import java.sql.*;

public class DBConnector {
	private Connection conn;
	private Statement stmt;
	
	public DBConnector() throws ClassNotFoundException, SQLException {
		Class.forName("org.mariadb.jdbc.Driver");
		
		String url = "jdbc:mariadb://127.0.0.1:3306/tester";
		String id = "root";
		String pw = "1234";
		
		conn = DriverManager.getConnection(url,id,pw);
		stmt = conn.createStatement();
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
}
