package Frame;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import DB.Query;
import DTO.ClassDTO;

public class OtherActionListener implements ActionListener {
	
	JFrame frame;
	
	public OtherActionListener(JFrame frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		
		if (btn.getText().equals("과목 개설")) {
			
			InsertCourseFrame frame = (InsertCourseFrame) this.frame;
			
			String course_id = frame.getTf_course_id().getText();
			String course_name = frame.getTf_course_name().getText();
			int credit = Integer.parseInt(frame.getCb_credit().getSelectedItem().toString());
			
			System.out.println(course_id + " " + course_id.length());
			
			if (course_id.length() == 7) {
				System.out.println("test");
				for (int i = 0; i < 3; i++) {
					char c = course_id.charAt(i);
					
					if (c >= 'A' && c <= 'Z') {
						System.out.println("testAZ");
					} else {
						String message = "과목 번호 형식이 잘못 되었습니다.";
						JOptionPane.showMessageDialog(null, message);
						return;
					}
				}
				
				for (int i = 3; i < 7; i++) {
					char c = course_id.charAt(i);
					
					if (c >= '0' && c <= '9') {
						System.out.println("test09");
					} else {
						String message = "과목 번호 형식이 잘못 되었습니다.";
						JOptionPane.showMessageDialog(null, message);
						return;
					}
				}
			} else {
				String message = "과목 번호 형식이 잘못 되었습니다.";
				JOptionPane.showMessageDialog(null, message);
				return;
			}
			
			if (course_name.equals("")) {
				String message = "과목명을 입력해 주십시오.";
				JOptionPane.showMessageDialog(null, message);
				return;
			}
			
			try {
				if (ValidateDuplicateCourseId(course_id)) {
					String message = "과목 번호가 이미 있습니다.";
					JOptionPane.showMessageDialog(null, message); 
				} else {
					int state = InsertCourse(course_id, course_name, credit);
					
					if (state > 0) {
						String message = "과목이 생성 되었습니다.";
						JOptionPane.showMessageDialog(null, message);
					} else {
						String message = "생성 실패하였습니다.";
						JOptionPane.showMessageDialog(null, message);
					}
				}
			} catch (HeadlessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			frame.dispose();
		} else if (btn.getText().equals("강의 개설")) {
			ClassFrame classFrame = (ClassFrame) frame;
			
			int class_no = 0;
				
			try {
				class_no = Integer.parseInt(classFrame.getTf_class_no().getText());
			} catch (Exception e3) {
				String message = "수업 번호 형식이 잘못 되었습니다..";
				JOptionPane.showMessageDialog(null, message);
				return;
			}
			
			try {
				if (ValidateDuplicateClassId(class_no)) {
					ClassDTO dto = new ClassDTO();
					
					dto.setClass_no(class_no);
					dto.setCourse_id(classFrame.getCb_course_id().getSelectedItem().toString());
					dto.setLecturer_id(classFrame.getCb_lecturer().getSelectedItem().toString());
					dto.setYear(Integer.parseInt(classFrame.getCb_year().getSelectedItem().toString()));
					
					int personMax = Integer.parseInt(classFrame.getTf_person_max().getText());
					
					dto.setPerson_max(personMax);
					dto.setRoom_id(Integer.parseInt(classFrame.getCb_room_id().getSelectedItem().toString()));
					
					dto.setMajor_id((int) classFrame.getMajorIdList().get(classFrame.getCb_major().getSelectedIndex()));
					
					System.out.println(dto.toString());
					
					int class_id = InsertClass(dto);
					
					if (class_id > 0) {
						
						int day = classFrame.getCb_day1().getSelectedIndex() + 1;
						int start = classFrame.getCb_start1().getSelectedIndex() + 1;
						int end = classFrame.getCb_end1().getSelectedIndex() + 1;
						
						String startTime = MakeTime(day, start, end, false);
						String endTime = MakeTime(day, start, end ,true);
						
						InsertTime(class_id, 1, startTime, endTime);
						
						if (classFrame.getCheck_able_time2().isSelected()) {
							day = classFrame.getCb_day2().getSelectedIndex() + 1;
							start = classFrame.getCb_start2().getSelectedIndex() + 1;
							end = classFrame.getCb_end2().getSelectedIndex() + 1;
							
							startTime = MakeTime(day, start, end, false);
							endTime = MakeTime(day, start, end ,true);
							
							InsertTime(class_id, 2, startTime, endTime);
						}
						
						String message = "강의 개설 성공";
						JOptionPane.showMessageDialog(null, message);
						
						classFrame.dispose();
					} else {
						String message = "개설 중 에러 발생. 입력 값 등을 확인해 주십시오.";
						JOptionPane.showMessageDialog(null, message);
						return;
					}
					
				} else {
					String message = "이미 같은 수업 번호의 수업이 개설되어있습니다.";
					JOptionPane.showMessageDialog(null, message);
					return;
				}
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
				
				String message = "개설 중 에러 발생. 입력 값 등을 확인해 주십시오.";
				JOptionPane.showMessageDialog(null, message);
				return;
			}
		} else if (btn.getText().equals("취소") || btn.getText().equals("닫기")) {
			frame.dispose();
		}
	}

	private void InsertTime(int class_id, int i, String startTime, String endTime) throws SQLException, ClassNotFoundException {
		Query query = Query.getInstance();
		
		query.InsertTime(class_id, i, startTime, endTime);
		
	}

	private int InsertClass(ClassDTO dto) throws ClassNotFoundException, SQLException {

		Query query = Query.getInstance();
		
		int class_id = query.InsertClass(dto);
		
		return class_id;
	}

	private boolean ValidateDuplicateClassId(int class_no) throws ClassNotFoundException, SQLException {
		Query query = Query.getInstance();
		
		int count = query.GetClassOpenedNow(class_no);
		
		if (count > 0) {
			return false;
		} else {
			return true;
		}
	}

	private int InsertCourse(String course_id, String course_name, int credit) throws ClassNotFoundException, SQLException {
		Query query = Query.getInstance();
		
		int state = query.InsertCourse(course_id, course_name, credit);
		
		return state;
	}

	private boolean ValidateDuplicateCourseId(String course_id) throws ClassNotFoundException, SQLException {
		
		Query query = Query.getInstance();
		
		if (query.DuplicateCourseId(course_id)) {
			return true;
		}
		
		return false;
	}
	
	private String MakeTime(int day, int start, int end, boolean isEnd) {
		
		String time = "1900-00-00T00:00:00.000Z";
		
		char[] time_arr = time.toCharArray();
		
		time_arr[9] = Integer.toString(day).charAt(0);
		
		int hour = 0;
		
		int minute;
		
		if (!isEnd) {
			hour = 8 + ((start - 1)/2);
			
			if (start%2 == 0) {
				minute = 30;
			} else {
				minute = 0;
			}
		} else {
			hour = 8 + ((start + end - 1)/2);
			
			if ((start + end)%2 == 0) {
				minute = 30;
			} else {
				minute = 0;
			}
		}
			
		if (hour >= 10) {
			time_arr[11] = Integer.toString(hour).charAt(0);
			time_arr[12] = Integer.toString(hour).charAt(1);
		} else {
			time_arr[12] = Integer.toString(hour).charAt(0);
		}
			
		if (minute == 30) {
			time_arr[14] = '3';
		}

		time = new String(time_arr);
		
		System.out.println(time);
		
		return time;
	}

}
