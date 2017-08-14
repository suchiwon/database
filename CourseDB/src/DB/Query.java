package DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import DTO.ClassDTO;
import DTO.StudentInfo;

public class Query {
	
	private final static int YEAR = 2014;
	
	private static Query instance;
	
	private ResultSet rs;
	private DBConnector conn;
	
	private Query() throws ClassNotFoundException, SQLException {
		conn = DBConnector.getInstance();
	}
	
	public static Query getInstance() throws ClassNotFoundException, SQLException {
		if (instance == null) {
			instance = new Query();
		}
		
		return instance;
	}
	
	public void getStudentInfo(String id) throws SQLException {
		String query = "SELECT S.*, M.name as major_name from student S, major M WHERE "
				+ " S.student_id = ? and S.major_id = M.major_id";
		
		PreparedStatement stmt = conn.getPreparedStatement(query);
		
		stmt.setString(1, id);
		
		rs = stmt.executeQuery();
		
		if (rs.next()) {
			StudentInfo studentInfo = StudentInfo.getInstance();
			
			studentInfo.setId(rs.getString("student_id"));
			studentInfo.setName(rs.getString("name"));
			studentInfo.setSex(rs.getString("sex"));
			studentInfo.setYear(rs.getInt("year"));
			studentInfo.setMajor_id(rs.getInt("major_id"));
			studentInfo.setMajor_name(rs.getString("major_name"));
			studentInfo.setTutor_id(rs.getString("tutor_id"));
		}
	}
	
	public boolean LoginTestQuery(String name, String password) throws SQLException {
		String query = "SELECT * from student where student_id = ? "
				+ "and password = ?";
		
		PreparedStatement stmt = conn.getPreparedStatement(query);
		
		stmt.setString(1, name);
		stmt.setString(2, password);
		
		rs = stmt.executeQuery();
		
		if (rs.next()) {
			return true;
		} else {
			return false;
		}
	}
	
	public Vector GetClasses(int class_no, String course_id, String course_name, String lecturer_name) throws SQLException {
		Vector data = new Vector();
		
		int count = 1;
		
		String query = "SELECT class.*, course.name, course.credit, major.name as major_name, lecturer.name as lecturer_name from class, course, lecturer, major WHERE "
				+ "class.course_id = course.course_id and class.lecturer_id = lecturer.lecturer_id and class.major_id = major.major_id "
				+ "and class.opened = " + YEAR;
		
		if (class_no > 0) {
			query += " and class.class_no = ?";
		}
		
		if (!course_id.equals("")) {
			query += " and course.course_id = ?";
		}
		
		if (!course_name.equals("")) {
			query += " and course.name like ?";
		}
		
		if (!lecturer_name.equals("")) {
			query += " and lecturer.name like ?";
		}
		
		PreparedStatement stmt = conn.getPreparedStatement(query);
		
		if (class_no > 0) {
			stmt.setInt(count++, class_no);
		}
		
		if (!course_id.equals("")) {
			stmt.setString(count++, course_id);
		}
		
		if (!course_name.equals("")) {
			stmt.setString(count++, "%"+course_name+"%");
		}
		
		if (!lecturer_name.equals("")) {
			stmt.setString(count++, lecturer_name +"%");
		}
		
		
		rs = stmt.executeQuery();
		
		while (rs.next()) {
			Vector row = new Vector();
			
			row.add(rs.getInt("class_id"));
			row.add(rs.getString("course_id"));
			row.add(rs.getInt("class_no"));
			row.add(rs.getString("name"));
			row.add(rs.getString("major_name"));
			row.add(rs.getInt("credit"));
			row.add(rs.getString("lecturer_name"));
			row.add(rs.getInt("person_max"));
			
			data.add(row);
		}
		
		return data;
	}
	
	public Vector GetSignClasses(String id) throws SQLException {
		Vector data = new Vector();
		
		int count = 1;
		
		String query = "SELECT class.*, credits.*, major.name as major_name, course.name, course.credit from class, credits, course, major where "
				+ "class.class_id = credits.class_id and class.course_id = course.course_id and class.major_id = major.major_id and class.opened = " + YEAR;
		query += " and credits.student_id = ?";
		
		PreparedStatement stmt = conn.getPreparedStatement(query);
		
		stmt.setString(1, id);
		
		rs = stmt.executeQuery();
		
		while (rs.next()) {
			Vector row = new Vector();
			
			row.add(rs.getInt("class_id"));
			row.add(rs.getString("course_id"));
			row.add(rs.getInt("class_no"));
			row.add(rs.getString("name"));
			row.add(rs.getString("major_name"));
			row.add(rs.getInt("credit"));
			row.add(rs.getString("grade"));
			
			data.add(row);
		}
		
		return data;
	}
	
	public int GetSignCount(int class_id) throws SQLException {
		
		int count = 0; 
		
		String query = "SELECT count(*) as signCount FROM credits WHERE class_id = ?";
		
		PreparedStatement stmt = conn.getPreparedStatement(query);
		
		stmt.setInt(1, class_id);
		
		rs = stmt.executeQuery();
		
		if(rs.next()) {
			count = rs.getInt("signCount");
		}
		
		return count;
	}
	
	public boolean GetSignCourse(int class_id, String course_id, String student_id) throws SQLException {
		
		Vector data = new Vector();
	/*	
		String query = "SELECT count(*) as signCount from credits where student_id = ? and course_id in "
				+ "(( SELECT distinct class.class_id FROM credits, class WHERE "
				+ "credits.course_id = class.course_id and credits.student_id = ? and "
				+ "class.course_id = ? and class.opened = "+ YEAR +"))";
	*/
		String query = "select distinct class.course_id from class, credits where class.class_id = credits.class_id " +
				"and credits.student_id = ? and class.opened = " + YEAR;
				
		PreparedStatement stmt = conn.getPreparedStatement(query);
	/*	
		stmt.setString(1, student_id);
		stmt.setString(2, student_id);
		stmt.setString(3, course_id);
	*/
		stmt.setString(1, student_id);
		stmt.setInt(2, class_id);
		
		rs = stmt.executeQuery();
		
		while (rs.next()) {
			//data.add(rs.getString("course_id"));
			if (course_id.equals(rs.getString("course_id"))) {
				return false;
			}
		}
		
		return true;
	}
	
	public String GetResign(int class_id, String course_id, String student_id) throws SQLException {
		String grade = "";
	/*	
		String query = "SELECT credits.grade from credits where student_id = ? and class_id in "
				+ "(( SELECT distinct class.class_id FROM credits, class WHERE "
				+ "credits.student_id = ? and "
				+ "class.course_id = ? and class.opened < "+ YEAR +"))";
		
		PreparedStatement stmt = conn.getPreparedStatement(query);
		
		stmt.setString(1, student_id);
		stmt.setString(2, student_id);
		stmt.setString(3, course_id);
	*/	
		String query = "select distinct class.course_id, credits.grade from class, credits where class.class_id = credits.class_id " +
				"and credits.student_id = ?";
		
		PreparedStatement stmt = conn.getPreparedStatement(query);
		
		stmt.setString(1, student_id);
		stmt.setInt(2, class_id);
		
		rs = stmt.executeQuery();
		
		while (rs.next()) {
			if (course_id.equals(rs.getString("course_id"))) {
				grade = rs.getString("grade");
				return grade;
			}
		}
		
		return grade;
	}
	
	public int GetMaxCreditId() throws SQLException {
		String query = "SELECT MAX(credits_id) + 1 as newId from credits";
		
		ResultSet rs = conn.ExecuteQuery(query);
		int newId = -1;
		
		if (rs.next()) {
			newId = rs.getInt("newId");
		}
		
		return newId;
	}
	
	public int InsertCredits(int class_id, String student_id) throws SQLException {
		String query = "INSERT INTO credits VALUES(?,?,?,null)";
		
		PreparedStatement stmt = conn.getPreparedStatement(query);
		
		int newId = GetMaxCreditId();
		
		stmt.setInt(3, class_id);
		stmt.setString(2, student_id);
		stmt.setInt(1, newId);
		
		int state = stmt.executeUpdate();
		
		return state;
	}

	public int DeleteCredits(int class_id, String student_id) throws SQLException {
		String query = "DELETE FROM credits WHERE class_id = ? and student_id = ?";
		
		PreparedStatement stmt = conn.getPreparedStatement(query);
		
		int newId = GetMaxCreditId();
		
		stmt.setInt(1, class_id);
		stmt.setString(2, student_id);
		
		int state = stmt.executeUpdate();
		
		return state;
	}

	public Vector GetStudents() throws SQLException {
		
		Vector data = new Vector();
		
		String query = "SELECT student.*, major.name as major_name, lecturer.name as lecturer_name FROM student, major, lecturer"
				+ " WHERE student.major_id = major.major_id and student.tutor_id = lecturer.lecturer_id";
		
		ResultSet rs = conn.ExecuteQuery(query);
		
		while (rs.next()) {
			Vector row = new Vector();
			
			row.add(rs.getString("student_id"));
			row.add(rs.getString("student_id"));
			row.add(rs.getString("name"));
			row.add(rs.getString("password"));
			row.add(rs.getString("sex"));
			row.add(rs.getInt("year"));
			row.add(rs.getString("major_name"));
			row.add(rs.getString("lecturer_name"));
			
			data.add(row);
		}
		
		return data;
	}

	public int DeleteClass(int class_id) throws SQLException {
		
		String query = "DELETE FROM credits WHERE class_id = ?";
		
		PreparedStatement stmt = conn.getPreparedStatement(query);
		
		stmt.setInt(1, class_id);
		
		stmt.executeUpdate();
		
		query = "DELETE FROM time WHERE class_id = ?";
		
		stmt = conn.getPreparedStatement(query);
		
		stmt.setInt(1, class_id);
		
		stmt.executeUpdate();

		
		query = "DELETE FROM class WHERE class_id = ?";
		
		stmt = conn.getPreparedStatement(query);
		
		stmt.setInt(1, class_id);
		
		int state = stmt.executeUpdate();
		
		return state;
	}

	public boolean DuplicateCourseId(String course_id) throws SQLException {
		String query = "SELECT count(*) as count from course where course.course_id = ?";
		
		PreparedStatement stmt = conn.getPreparedStatement(query);
		
		stmt.setString(1, course_id);
		
		ResultSet rs = stmt.executeQuery();
		
		rs.next();
		
		int count = rs.getInt("count");
		
		return (count > 0);
	}

	public int InsertCourse(String course_id, String course_name, int credit) throws SQLException {
		String query = "INSERT INTO course VALUES(?,?,?)";
		
		PreparedStatement stmt = conn.getPreparedStatement(query);
		
		stmt.setString(1, course_id);
		stmt.setString(2, course_name);
		stmt.setInt(3, credit);
		
		int state = stmt.executeUpdate();
		
		return state;
	}
	
	public Vector GetCourseIdList() throws SQLException {
		String query = "SELECT course_id from course";
		
		Vector data = new Vector();
		
		ResultSet rs = conn.ExecuteQuery(query);
		
		while (rs.next()) {
			data.add(rs.getString("course_id"));
		}
		
		return data;
	}
	
	public Vector GetMajorList() throws SQLException {
		String query = "SELECT * from major";
		
		Vector data = new Vector();
		
		ResultSet rs = conn.ExecuteQuery(query);
		
		while (rs.next()) {
			Vector row = new Vector();
			row.add(rs.getInt("major_id"));
			row.add(rs.getString("name"));
			
			data.add(row);
		}
		
		return data;
	}
	
	public Vector GetLecturerIdList() throws SQLException {
		String query = "SELECT lecturer_id from lecturer";
		
		Vector data = new Vector();
		
		ResultSet rs = conn.ExecuteQuery(query);
		
		while (rs.next()) {
			data.add(rs.getString("lecturer_id"));
		}
		
		return data;
	}
	
	public Vector GetRoomIdList() throws SQLException {
		String query = "SELECT room_id from room";
		
		Vector data = new Vector();
		
		ResultSet rs = conn.ExecuteQuery(query);
		
		while (rs.next()) {
			data.add(rs.getInt("room_id"));
		}
		
		return data;
	}

	public int GetClassOpenedNow(int class_no) throws SQLException {
		String query = "SELECT count(*) as count from class WHERE class_no = ?";
		
		PreparedStatement stmt = conn.getPreparedStatement(query);
		
		stmt.setInt(1, class_no);
		
		ResultSet rs = stmt.executeQuery();
		
		rs.next();
		
		int count = rs.getInt("count");
		
		return count;
	}
	
	public Vector GetClassOpenedBefore(int class_no) throws SQLException {
		String query = "SELECT * from class WHERE class_no = ? and opened < " + YEAR + " order by opened desc";
		
		PreparedStatement stmt = conn.getPreparedStatement(query);
		
		stmt.setInt(1, class_no);
		
		ResultSet rs = stmt.executeQuery();
		
		Vector data = new Vector();
		
		if (rs.next()) {
			data.add(rs.getInt("class_no"));
			data.add(rs.getString("course_id"));
			data.add(rs.getInt("major_id"));
			data.add(rs.getInt("year"));
			data.add(rs.getString("lecturer_id"));
			data.add(rs.getInt("person_max"));
			data.add(rs.getInt("room_id"));
		}
		
		return data;
	}

	public int InsertClass(ClassDTO dto) throws SQLException {
		
		String query = "SELECT max(class_id) + 1 as maxId from class";
		
		ResultSet rs = conn.ExecuteQuery(query);
		
		rs.next();
		
		int maxId = rs.getInt("maxId");

		query = "INSERT INTO class values(?,?,?,?,?,?,?,?,?)";
		
		PreparedStatement stmt = conn.getPreparedStatement(query); 
		
		stmt.setInt(1, maxId);
		stmt.setInt(2, dto.getClass_no());
		stmt.setString(3, dto.getCourse_id());
		stmt.setInt(4, dto.getMajor_id());
		stmt.setInt(5, dto.getYear());
		stmt.setString(6, dto.getLecturer_id());
		stmt.setInt(7, dto.getPerson_max());
		stmt.setInt(8, YEAR);
		stmt.setInt(9, dto.getRoom_id());
		
		int state = stmt.executeUpdate();
		
		if (state > 0) {
			return maxId;
		} else 
		{
			return -1;
		}
	}

	public void InsertTime(int class_id, int i, String startTime, String endTime) throws SQLException {

		String query = "SELECT max(time_id) + 1 as maxId from time";
		
		ResultSet rs = conn.ExecuteQuery(query);
		
		rs.next();
		
		int maxId = rs.getInt("maxId");
				
		query = "INSERT INTO time VALUES(?,?,?,?,?)";
		
		PreparedStatement stmt = conn.getPreparedStatement(query); 
		
		stmt.setInt(1, maxId);
		stmt.setInt(2, class_id);
		stmt.setInt(3, i);
		stmt.setString(4, startTime);
		stmt.setString(5, endTime);

		stmt.executeUpdate();
	}
	
	public Vector GetClassTime(int class_id) throws SQLException {
		String query = "SELECT time.begin, time.end FROM time, class "
				+ "WHERE time.class_id = class.class_id and class.class_id = ?";
		
		Vector data = new Vector();
		
		PreparedStatement stmt = conn.getPreparedStatement(query); 
		
		stmt.setInt(1, class_id);
		
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			Vector row = new Vector();
			
			row.add(rs.getString("begin"));
			row.add(rs.getString("end"));
			
			data.add(row);
		}
		
		return data;
	}
	
	public Vector GetTimeTableSign(String student_id) throws SQLException {
		String query = "SELECT time.begin, time.end FROM time, class, credits "
				+ "WHERE time.class_id = class.class_id and credits.class_id = class.class_id and "
				+ "credits.student_id = ? and class.opened = " + YEAR;
		
		Vector data = new Vector();
		
		PreparedStatement stmt = conn.getPreparedStatement(query); 
		
		stmt.setString(1, student_id);
		
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			Vector row = new Vector();
			
			row.add(rs.getString("begin"));
			row.add(rs.getString("end"));
			
			data.add(row);
		}
		
		return data;
	}
	
	public Vector GetTimeTable(String student_id) throws SQLException {
		String query = "SELECT time.begin, time.end, class.class_id, course.name, class.room_id, lecturer.name as lecturer_name FROM time, class, credits, course, lecturer" 
				 + " WHERE time.class_id = class.class_id and credits.class_id = class.class_id and "
				+ " class.course_id = course.course_id and class.lecturer_id = lecturer.lecturer_id and "
				+ " credits.student_id = 2011003155 and class.opened = " + YEAR;
		
		Vector data = new Vector();
		
		PreparedStatement stmt = conn.getPreparedStatement(query); 
		
		stmt.setString(1, student_id);
		
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			Vector row = new Vector();
			
			row.add(rs.getString("begin"));
			row.add(rs.getString("end"));
			row.add(rs.getInt("class_id"));
			row.add(rs.getString("name"));
			row.add(rs.getInt("room_id"));
			row.add(rs.getString("lecturer_name"));
			
			data.add(row);
		}
		
		return data;
	}
	
	public Vector GetAnalysis() throws SQLException {
		String query = "SELECT class.opened, course.name as course_name, count(credits.class_id) as count from class, credits, course " + 
		"where class.class_id = credits.class_id and class.course_id = course.course_id group by course.name, opened " + 
		"order by course.name, class.opened desc";
		
		ResultSet rs = conn.ExecuteQuery(query);
		
		Vector temp = new Vector();
		
		while (rs.next()) {
			Vector row = new Vector();
			
			row.add(rs.getInt("opened"));
			row.add(rs.getString("course_name"));
			row.add(rs.getInt("count"));
			
			temp.add(row);
		}
		
		return temp;
	}
}
