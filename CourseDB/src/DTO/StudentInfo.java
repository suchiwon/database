package DTO;

public class StudentInfo {
	private String id;
	private String name;
	private String sex;
	private int year;
	private int major_id;
	private String major_name;
	private String tutor_id;
	
	private static StudentInfo instance;
	
	private StudentInfo() {
		
	}
	
	public static StudentInfo getInstance() {
		if (instance == null) {
			instance = new StudentInfo();
		} 
		
		return instance;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMajor_id() {
		return major_id;
	}
	public void setMajor_id(int major_id) {
		this.major_id = major_id;
	}
	public String getMajor_name() {
		return major_name;
	}
	public void setMajor_name(String major_name) {
		this.major_name = major_name;
	}
	public String getTutor_id() {
		return tutor_id;
	}
	public void setTutor_id(String tutor_id) {
		this.tutor_id = tutor_id;
	}
}
