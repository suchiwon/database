package Frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import DB.Query;
import DTO.ExcelMaker;
import DTO.StudentInfo;

public class MainActionListener implements ActionListener {
	MainFrame mainFrame;
	
	public MainActionListener(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton btn = (JButton) e.getSource();
		Vector data = new Vector();
		  
		if(btn.getText().equals("��û ��� ����")) {
			
			btn.setText("�˻� ���");
			mainFrame.getBt_insert_delete().setText("���� ����");
			try {
				data = GetSignClasses();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			mainFrame.JTableInfoRefresh(data);	
		} else if (btn.getText().equals("�˻� ���")) {
			
			btn.setText("��û ��� ����");
			mainFrame.getBt_insert_delete().setText("���� ��û");
			
			mainFrame.resetTextField();
			
			try {
				data = GetCourses();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			mainFrame.JTableRefresh(data);
		} else if (btn.getText().equals("�˻�")) {
			mainFrame.getBt_mode_change().setText("�˻� ���");
			try {
				data = GetCourses();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			mainFrame.JTableRefresh(data);
		} else if (btn.getText().equals("���� ��û")) {
			
			int class_id = mainFrame.getSelectedClassId();
			String course_id = mainFrame.getSelectedCourseId();
			
			try {
				if (ValidateMaxPerson(class_id) && ValidateDuplicateTime(class_id)) {
					if (ValidateDuplicateSign(class_id, course_id)) {
						String grade = ValidateReSign(class_id, course_id);
						System.out.println(grade);
						if (grade != "") {
							String message = "����� �����Դϴ�.\n ���� ����:" + grade;
							JOptionPane.showMessageDialog(null, message); 
						}
						
						InsertCredit(class_id, course_id);
						
						mainFrame.getBackDataList().add(class_id);

					}
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} else if (btn.getText().equals("���� ����")) {
			
			try {
				int class_id = mainFrame.getSelectedClassId();
				DeleteCredits(class_id);
				
				try {
					data = GetSignClasses();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				mainFrame.JTableInfoRefresh(data);
			} catch (Exception e3) {
				e3.printStackTrace();
			}
			
		} else if (btn.getText().equals("�ǵ�����")) {
			Vector backData = mainFrame.getBackDataList();
			
			for (int i = 0; i < backData.size(); i++) {
				try {
					DeleteCredits((int)backData.get(i));
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			mainFrame.getLabelInfo().setText("�α��� �� ��û�� �������� �����߽��ϴ�.");
			mainFrame.getBackDataList().clear();
		} else if (btn.getText().equals("�ð�ǥ")) {
			try {
				GetTimeTableData();
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	private void GetTimeTableData() throws ClassNotFoundException, SQLException {
		Query query = Query.getInstance();
		
		String student_id = StudentInfo.getInstance().getId();
		
		Vector data = query.GetTimeTable(student_id);
		Vector timeData = new Vector();
		
		for (int i = 0; i < data.size(); i++) {
			Vector row = (Vector) data.get(i);
			
			Vector insertRow = new Vector();
			
			insertRow.add(row.get(0));
			insertRow.add(row.get(1));
			
			timeData.add(insertRow);
		}
		
		Vector timeDataParsed = TimeParse(timeData);
		
		ExcelMaker maker = new ExcelMaker();
		
		try {
			maker.MakeTimeTableExcel(timeDataParsed, data);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String message = "�ð�ǥ�� ��������ϴ�.";
		JOptionPane.showMessageDialog(null, message); 
	}

	private boolean ValidateDuplicateTime(int class_id) throws ClassNotFoundException, SQLException {
		
		Query query = Query.getInstance();
		
		String student_id = StudentInfo.getInstance().getId();
		
		Vector timeTable = query.GetClassTime(class_id);
		
		Vector timeTableParsed = TimeParse(timeTable);
		
		Vector signTimeTable = query.GetTimeTableSign(student_id);
		
		Vector signTimeTableParsed = TimeParse(signTimeTable);
		
		for (int i = 0; i < timeTableParsed.size(); i++) {
			Vector row = (Vector) timeTableParsed.get(i);
				
			int day1 = (int) row.get(0);
			int start1 = (int) row.get(1);
			int end1 = (int) row.get(2);
				
			if (day1 >= 6) {
				continue;
			}
				
			for (int j = 0; j < signTimeTableParsed.size(); j++) {
				row = (Vector) signTimeTableParsed.get(j);
				
				int day2 = (int) row.get(0);
				int start2 = (int) row.get(1);
				int end2 = (int) row.get(2);
				
				if (day1 == day2) {
					if (end1 >= start2 && start1 < end2) {
						String message = "��û ����� ��ġ�� �ð��� �ֽ��ϴ�.";
						JOptionPane.showMessageDialog(null, message);
						return false;
					}
				}
			}
		}
		
		return true;
	}

	private Vector TimeParse(Vector timeTable) {
		
		Vector data = new Vector();

		for (int i = 0; i < timeTable.size(); i++) {
			Vector row = (Vector) timeTable.get(i);
			
			String start = (String) row.get(0);
			String end = (String) row.get(1);
			
			Vector insertRow = TimeParseRow(start, end);
			
			data.add(insertRow);
		}
		
		return data;
	}
	
	private Vector TimeParseRow(String start, String end) {
		
		Vector data = new Vector();
		
		data.add(Integer.parseInt(start.substring(9, 10)));
		
		String startTemp = start.substring(11, 13) + start.substring(14, 16);
		String endTemp = end.substring(11, 13) + end.substring(14, 16);
		
		System.out.println(startTemp + " " + endTemp);
		
		int startInt = Integer.parseInt(startTemp);
		int endInt = Integer.parseInt(endTemp);
		
		int temp = startInt - 800;
		
		int startF = (temp/100) * 2 + 1;
		
		if (temp%100 == 30) {
			startF++;
		}
		
		temp = endInt - startInt;
		
		int endF = (temp/100) * 2;
		
		if (temp%100 == 70) {
			endF++;
		}
		
		endF += startF;
		
		data.add(startF);
		data.add(endF);
		
		System.out.println(startF + " " + endF);
		
		return data;
	}

	private void DeleteCredits(int class_id) throws SQLException, ClassNotFoundException {
		
		if (class_id == 0) {
			mainFrame.getLabelInfo().setText("������ ������ ������ �ֽʽÿ�");
			return;
		}
		
		Query query = Query.getInstance();
		
		String student_id = StudentInfo.getInstance().getId();
		
		int state = query.DeleteCredits(class_id, student_id);
		
		if (state > 0) {
			mainFrame.getLabelInfo().setText("���� ���� �Ϸ�Ǿ����ϴ�.");
		} else {
			mainFrame.getLabelInfo().setText("������ ���� ������ �����߽��ϴ�.");
		}
	}

	private void InsertCredit(int class_id, String course_id) throws ClassNotFoundException, SQLException {
		
		Query query = Query.getInstance();
		
		String student_id = StudentInfo.getInstance().getId();
		
		int state = query.InsertCredits(class_id, student_id);
		
		if (state > 0) {
			mainFrame.getLabelInfo().setText("���� ��û �Ϸ�Ǿ����ϴ�.");
		} else {
			mainFrame.getLabelInfo().setText("������ ���� ���� ��û�� �����߽��ϴ�.");
		}
	}

	private String ValidateReSign(int class_id, String course_id) throws ClassNotFoundException, SQLException {
		
		Query query = Query.getInstance();
		
		String student_id = StudentInfo.getInstance().getId();
		
		String grade = query.GetResign(class_id, course_id, student_id);
		
		return grade;
	}

	private boolean ValidateDuplicateSign(int class_id, String course_id) throws ClassNotFoundException, SQLException {
		if (course_id.equals("")) {
			mainFrame.getLabelInfo().setText("������ ������ ������ �ֽʽÿ�");
			return false;
		}
		
		Query query = Query.getInstance();
		
		String student_id = StudentInfo.getInstance().getId();
		
		boolean test = query.GetSignCourse(class_id, course_id, student_id);
		
		if (test) {
			return true;
		} else {
			mainFrame.getLabelInfo().setText("�̹� ��û�� �����Դϴ�.");
			return false;
		}
	}

	private boolean ValidateMaxPerson(int class_id) throws ClassNotFoundException, SQLException {
		
		if (class_id == 0) {
			mainFrame.getLabelInfo().setText("������ ������ ������ �ֽʽÿ�");
			return false;
		}
		
		Query query = Query.getInstance();
		
		int count = query.GetSignCount(class_id);
		
		if (count < mainFrame.getMaxPerson()) {
			return true;
		} else {
			mainFrame.getLabelInfo().setText("���� �������� �ִ�ġ�� �����߽��ϴ�.");
			return false;
		}
	}

	private Vector GetCourses() throws SQLException, ClassNotFoundException {
		Vector data;
		int class_id;
		
		try {
			class_id = Integer.parseInt(mainFrame.getTf_class_id().getText());
		} catch (Exception e) {
			e.printStackTrace();
			class_id = 0;
		}
		
		String course_id = mainFrame.getTf_course_id().getText();
		String course_name = mainFrame.getTf_course_name().getText();
		String lecturer_name = mainFrame.getTf_lecturer_name().getText();
		
		Query query = Query.getInstance();
		
		data = query.GetClasses(class_id, course_id, course_name, lecturer_name);
		
		return data;
	}
	
	private Vector GetSignClasses() throws SQLException, ClassNotFoundException {
		Vector data;
		String id = StudentInfo.getInstance().getId();
		
		System.out.println(id);
		
		Query query = Query.getInstance();
		
		data = query.GetSignClasses(id);
		
		return data;
	}
}
