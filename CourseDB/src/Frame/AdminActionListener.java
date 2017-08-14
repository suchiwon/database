package Frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;

import DB.Query;
import DTO.StudentInfo;

public class AdminActionListener implements ActionListener {
	
	private AdminFrame adminFrame;
	
	public AdminActionListener(AdminFrame adminFrame) {
		this.adminFrame = adminFrame;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		Vector data = new Vector();
		
		if (btn.getText().equals("�л� ���")) {
			adminFrame.setIs_student_mode(true);
			
			try {
				data = GetStudents();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			adminFrame.JTableStudentRefresh(data);
		} else if (btn.getText().equals("���� �˻�")) {
			try {
				data = GetCourses();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			adminFrame.JTableClassRefresh(data);
		} else if (btn.getText().equals("���")) {
			new AnalysisFrame();
		} else if (btn.getText().equals("��")) {
			try {
				int class_id = adminFrame.getSelectedClassId();
				DeleteClass(class_id);
				
				try {
					data = GetCourses();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				adminFrame.JTableClassRefresh(data);
			} catch (Exception e3) {
				e3.printStackTrace();
			}
		} else if (btn.getText().equals("���� ����")) {
			new InsertCourseFrame();
		} else if (btn.getText().equals("���� ����")) {
			new ClassFrame();
		}
		
	}
	
	private void DeleteClass(int class_id) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		if (class_id == 0) {
			adminFrame.getLabelInfo().setText("������ ������ ������ �ֽʽÿ�");
			return;
		}
		
		Query query = Query.getInstance();
		
		String student_id = StudentInfo.getInstance().getId();
		
		int state = query.DeleteClass(class_id);
		
		if (state > 0) {
			adminFrame.getLabelInfo().setText("���� ���� �Ϸ�Ǿ����ϴ�.");
		} else {
			adminFrame.getLabelInfo().setText("������ ���� ������ �����߽��ϴ�.");
		}
	}
	private Vector GetStudents() throws ClassNotFoundException, SQLException {
		Vector data;
		
		Query query = Query.getInstance();
		
		data = query.GetStudents();
		
		return data;
	}
	
	private Vector GetCourses() throws SQLException, ClassNotFoundException {
		Vector data;
		int class_id;
		
		try {
			class_id = Integer.parseInt(adminFrame.getTf_class_id().getText());
		} catch (Exception e) {
			e.printStackTrace();
			class_id = 0;
		}
		
		String course_id = adminFrame.getTf_course_id().getText();
		String course_name = adminFrame.getTf_course_name().getText();
		String lecturer_name = adminFrame.getTf_lecturer_name().getText();
		
		Query query = Query.getInstance();
		
		data = query.GetClasses(class_id, course_id, course_name, lecturer_name);
		
		return data;
	}

}
