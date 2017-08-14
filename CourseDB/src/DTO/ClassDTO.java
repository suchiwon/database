package DTO;

public class ClassDTO {
	int class_no;
	String course_id;
	int major_id;
	int year;
	String lecturer_id;
	int person_max;
	int opened;
	int room_id;
	
	public int getClass_no() {
		return class_no;
	}
	public void setClass_no(int class_no) {
		this.class_no = class_no;
	}
	public String getCourse_id() {
		return course_id;
	}
	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}
	public int getMajor_id() {
		return major_id;
	}
	public void setMajor_id(int major_id) {
		this.major_id = major_id;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getLecturer_id() {
		return lecturer_id;
	}
	public void setLecturer_id(String lecturer_id) {
		this.lecturer_id = lecturer_id;
	}
	public int getPerson_max() {
		return person_max;
	}
	public void setPerson_max(int person_max) {
		this.person_max = person_max;
	}
	public int getOpened() {
		return opened;
	}
	public void setOpened(int opened) {
		this.opened = opened;
	}
	public int getRoom_id() {
		return room_id;
	}
	public void setRoom_id(int room_id) {
		this.room_id = room_id;
	}
	@Override
	public String toString() {
		return "ClassDTO [class_no=" + class_no + ", course_id=" + course_id + ", major_id=" + major_id + ", year="
				+ year + ", lecturer_id=" + lecturer_id + ", person_max=" + person_max + ", opened=" + opened
				+ ", room_id=" + room_id + "]";
	}
}
