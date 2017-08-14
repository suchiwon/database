import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBC_test {

	public static void main(String[] args) {
		
		DBConnector DBconnector = null;
		ResultSet resultSet;
		
		try {
			DBconnector = new DBConnector();
		} catch (Exception e) {
			e.getMessage();
		}
		
		
		String query1 = "SELECT * from building where rooms >= 20";
		
		String query2_1 = "INSERT into student values('2011003155','서치원','999')";
		String query2_2 = "SELECT * from student where id = '2011003155'";
		
		String query3_1 = "DELETE from course where credit = 3";
		String query3_2 = "SELECT name from course where credit = 3";
		
		String query4_1 = "UPDATE building SET name = '정보통신관' where name = 'IT / BT'";
		String query4_2 = "SELECT * from building where name = '정보통신관'";
		
		String query5 = "select distinct r.building_id, b.name " + 
						"from rooms r, building b " + 
						"where r.building_id = b.id and r.occupancy >= 100";
		
		try {
			System.out.println("-----query1-----");
			resultSet = DBconnector.ExecuteQuery(query1);
			
			while (resultSet.next()) {
				String id = resultSet.getString("id");
				String name = resultSet.getString("name");
				String admin = resultSet.getString("admin");
				String rooms = resultSet.getString("rooms");
				
				System.out.println("아이디 : " + id + " | 건뮬명 : " + name + " | 관리자 : " + admin + " | 방 수 : " + rooms + "<br>");
			}
			
			System.out.println("-----query2-----");
			resultSet = DBconnector.ExecuteQuery(query2_1);
			
			if (DBconnector.getStmt().getUpdateCount() > 0) {
				System.out.println("insert success");
			} else {
				System.out.println("insert failure");
			}
			
			resultSet = DBconnector.ExecuteQuery(query2_2);
			
			if (resultSet.next()) {
				String id = resultSet.getString("id");
				String name = resultSet.getString("name");
				String major_id = resultSet.getString("major_id");
				
				System.out.println("아이디: " + id + " | 이름: " + name + " | 학과 번호: " + major_id);
			} else {
				System.out.println("insert failure");
			}
			
			System.out.println("-----query3-----");
			
			resultSet = DBconnector.ExecuteQuery(query3_1);
			
			if (DBconnector.getStmt().getUpdateCount() > 0) {
				System.out.println("delete success");
			} else {
				System.out.println("delete failure");
			}
			
			resultSet = DBconnector.ExecuteQuery(query3_2);
			
			if (resultSet.next() == true) {
				System.out.println("delete failure");
			} else {
				System.out.println("courses that credit is 3 are all deleted");
			}
			
			System.out.println("-----query4-----");
			
			resultSet = DBconnector.ExecuteQuery(query4_1);
			
			if (DBconnector.getStmt().getUpdateCount() > 0) {
				System.out.println("update success");
			} else {
				System.out.println("update failure");
			}
			
			resultSet = DBconnector.ExecuteQuery(query4_2);
			
			if (resultSet.next()) {
				String id = resultSet.getString("id");
				String name = resultSet.getString("name");
				String admin = resultSet.getString("admin");
				String rooms = resultSet.getString("rooms");
				
				System.out.println("아이디 : " + id + " | 건뮬명 : " + name + " | 관리자 : " + admin + " | 방 수 : " + rooms + "<br>");
			}
			
			System.out.println("-----query5-----");
			resultSet = DBconnector.ExecuteQuery(query5);
			
			while (resultSet.next()) {
				String building_id = resultSet.getString("building_id");
				String name = resultSet.getString("name");
				
				System.out.println("아이디 : " + building_id + " | 건뮬명 : " + name + "<br>");
			}
			
			
			resultSet.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBconnector.close();
		}
	}
}
