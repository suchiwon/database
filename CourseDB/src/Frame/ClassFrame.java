package Frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import DB.Query;

public class ClassFrame extends JFrame {
	
	private JPanel contentPane;
	private JTextField tf_class_no;
	private JTextField tf_person_max;
	private JComboBox cb_course_id;
	private JComboBox cb_major;
	private JComboBox cb_lecturer;
	private JComboBox cb_room_id;
	private JComboBox cb_year;
	
	private JCheckBox check_able_time2;
	private JComboBox cb_day1;
	private JComboBox cb_start1;   
	private JComboBox cb_end1;   
	private JComboBox cb_day2;    
	private JComboBox cb_start2;  
	private JComboBox cb_end2;
    
	private Vector majorIdList;
	private Vector majorNameList;
    
	private Vector time_list;
	
	private OtherActionListener otherActionListener;
	
	JButton bt_OK;

	/**
	 * Create the frame.
	 */
	public ClassFrame() {
		otherActionListener = new OtherActionListener(this);
		
		contentPane = new JPanel();
		
		majorIdList = new Vector();
		majorNameList = new Vector();
		time_list = new Vector();
		
		for (int i = 1; i <= 26; i++ ) {
			time_list.add(i);
		}
		
		setTitle("과목 개설");
        setSize(525, 297);
        setLocation(0, 120);
        contentPane.setLayout(null);
        getContentPane().setLayout(null);
        
        JLabel label_1 = new JLabel("\uAC15\uC758 \uBC88\uD638");
        label_1.setBounds(22, 10, 195, 15);
        getContentPane().add(label_1);
        
        tf_class_no = new JTextField();
        tf_class_no.setBounds(103, 7, 143, 21);
        getContentPane().add(tf_class_no);
        tf_class_no.setColumns(10);
        
        JLabel label_3 = new JLabel("\uACFC\uBAA9\uBC88\uD638");
        label_3.setBounds(22, 38, 71, 15);
        getContentPane().add(label_3);
        
        tf_person_max = new JTextField();
        tf_person_max.setBounds(334, 35, 143, 21);
        getContentPane().add(tf_person_max);
        tf_person_max.setColumns(10);
        
        JLabel label_2 = new JLabel("\uC804\uACF5");
        label_2.setBounds(22, 63, 71, 15);
        getContentPane().add(label_2);
        
        bt_OK = new JButton("\uAC15\uC758 \uAC1C\uC124");
        bt_OK.setBounds(25, 203, 97, 23);
        bt_OK.addActionListener(otherActionListener);
        getContentPane().add(bt_OK);
        
        cb_year = new JComboBox();
        cb_year.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4"}));
        cb_year.setBounds(103, 88, 143, 21);
        getContentPane().add(cb_year);
        
        JButton btnNewButton = new JButton("\uCDE8\uC18C");
        btnNewButton.setBounds(134, 203, 97, 23);
        btnNewButton.addActionListener(otherActionListener);
        getContentPane().add(btnNewButton);
        
        Vector courseIdList = new Vector();
        
        try {
			courseIdList = Query.getInstance().GetCourseIdList();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        cb_course_id = new JComboBox();
        cb_course_id.setModel(new DefaultComboBoxModel(courseIdList));
        cb_course_id.setBounds(103, 35, 143, 21);
        getContentPane().add(cb_course_id);
        
        Vector majorList = new Vector();
        
        try {
			majorList = Query.getInstance().GetMajorList();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        for (int i = 0; i < majorList.size(); i++) {
        	Vector row = (Vector) majorList.get(i);
        	
        	majorIdList.add(row.get(0));
        	majorNameList.add(row.get(1));
        }
        
        cb_major = new JComboBox();
        cb_major.setModel(new DefaultComboBoxModel(majorNameList));
        cb_major.setBounds(103, 60, 143, 21);
        getContentPane().add(cb_major);
        
        JLabel lblNewLabel = new JLabel("\uD559\uB144");
        lblNewLabel.setBounds(22, 91, 57, 15);
        getContentPane().add(lblNewLabel);
        
        JLabel label = new JLabel("\uB2F4\uB2F9 \uAD50\uC218");
        label.setBounds(22, 125, 57, 15);
        getContentPane().add(label);
        
        Vector lecturerList = new Vector();
        try {
			lecturerList = Query.getInstance().GetLecturerIdList();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        cb_lecturer = new JComboBox();
        cb_lecturer.setModel(new DefaultComboBoxModel(lecturerList));
        cb_lecturer.setBounds(103, 119, 143, 21);
        getContentPane().add(cb_lecturer);
        
        JLabel lblNewLabel_1 = new JLabel("\uC218\uAC15\uC790 \uC218");
        lblNewLabel_1.setBounds(267, 38, 57, 15);
        getContentPane().add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("\uAC15\uC758\uC2E4 \uBC88\uD638");
        lblNewLabel_2.setBounds(257, 69, 72, 15);
        getContentPane().add(lblNewLabel_2);
        
        Vector roomList = new Vector();
        
        try {
			roomList = Query.getInstance().GetRoomIdList();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        cb_room_id = new JComboBox();
        cb_room_id.setModel(new DefaultComboBoxModel(roomList));
        cb_room_id.setBounds(334, 66, 143, 21);
        getContentPane().add(cb_room_id);
        
        JLabel lblNewLabel_3 = new JLabel("\uC218\uC5C5 \uC2DC\uAC04");
        lblNewLabel_3.setBounds(258, 94, 57, 15);
        getContentPane().add(lblNewLabel_3);
        
        check_able_time2 = new JCheckBox("\uC2DC\uAC042");
        check_able_time2.setBounds(257, 159, 63, 23);
        getContentPane().add(check_able_time2);
        
        cb_day1 = new JComboBox();
        cb_day1.setModel(new DefaultComboBoxModel(new String[] {"\uC6D4", "\uD654", "\uC218", "\uBAA9", "\uAE08", "\uD1A0", "\uC77C"}));
        cb_day1.setBounds(322, 122, 43, 21);
        getContentPane().add(cb_day1);
        
        JLabel label_4 = new JLabel("\uC2DC\uAC041");
        label_4.setBounds(278, 125, 57, 15);
        getContentPane().add(label_4);
        
        JLabel lblNewLabel_4 = new JLabel("\uC694\uC77C");
        lblNewLabel_4.setBounds(327, 94, 57, 15);
        getContentPane().add(lblNewLabel_4);
        
        JLabel lblNewLabel_5 = new JLabel("\uC2DC\uC791 \uAD50\uC2DC");
        lblNewLabel_5.setBounds(371, 94, 57, 15);
        getContentPane().add(lblNewLabel_5);
        
        JLabel lblNewLabel_6 = new JLabel("\uAE30\uAC04");
        lblNewLabel_6.setBounds(434, 94, 43, 15);
        getContentPane().add(lblNewLabel_6);
        
        cb_start1 = new JComboBox();
        cb_start1.setModel(new DefaultComboBoxModel(time_list));
        cb_start1.setBounds(377, 122, 43, 21);
        getContentPane().add(cb_start1);
        
        cb_end1 = new JComboBox();
        cb_end1.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6"}));
        cb_end1.setBounds(434, 122, 43, 21);
        getContentPane().add(cb_end1);
        
        cb_day2 = new JComboBox();
        cb_day2.setModel(new DefaultComboBoxModel(new String[] {"\uC6D4", "\uD654", "\uC218", "\uBAA9", "\uAE08", "\uD1A0", "\uC77C"}));
        cb_day2.setBounds(322, 160, 43, 21);
        getContentPane().add(cb_day2);
        
        cb_end2 = new JComboBox();
        cb_end2.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6"}));
        cb_end2.setBounds(434, 160, 43, 20);
        getContentPane().add(cb_end2);
        
        cb_start2 = new JComboBox();
        cb_start2.setModel(new DefaultComboBoxModel(time_list));
        cb_start2.setBounds(371, 160, 41, 21);
        getContentPane().add(cb_start2);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         
        setVisible(true);
	}

	public JTextField getTf_class_id() {
		return tf_class_no;
	}

	public void setTf_class_id(JTextField tf_class_id) {
		this.tf_class_no = tf_class_id;
	}

	public JTextField getTf_person_max() {
		return tf_person_max;
	}

	public void setTf_person_max(JTextField tf_person_max) {
		this.tf_person_max = tf_person_max;
	}

	public JComboBox getCb_course_id() {
		return cb_course_id;
	}

	public void setCb_course_id(JComboBox cb_course_id) {
		this.cb_course_id = cb_course_id;
	}

	public JComboBox getCb_major() {
		return cb_major;
	}

	public void setCb_major(JComboBox cb_major) {
		this.cb_major = cb_major;
	}

	public JComboBox getCb_lecturer() {
		return cb_lecturer;
	}

	public void setCb_lecturer(JComboBox cb_lecturer) {
		this.cb_lecturer = cb_lecturer;
	}

	public JComboBox getCb_room_id() {
		return cb_room_id;
	}

	public void setCb_room_id(JComboBox cb_room_id) {
		this.cb_room_id = cb_room_id;
	}

	public JComboBox getCb_year() {
		return cb_year;
	}

	public void setCb_year(JComboBox cb_year) {
		this.cb_year = cb_year;
	}

	public JCheckBox getCheck_able_time2() {
		return check_able_time2;
	}

	public void setCheck_able_time2(JCheckBox check_able_time2) {
		this.check_able_time2 = check_able_time2;
	}

	public JComboBox getCb_day1() {
		return cb_day1;
	}

	public void setCb_day1(JComboBox cb_day1) {
		this.cb_day1 = cb_day1;
	}

	public JComboBox getCb_start1() {
		return cb_start1;
	}

	public void setCb_start1(JComboBox cb_start1) {
		this.cb_start1 = cb_start1;
	}

	public JComboBox getCb_end1() {
		return cb_end1;
	}

	public void setCb_end1(JComboBox cb_end1) {
		this.cb_end1 = cb_end1;
	}

	public JComboBox getCb_day2() {
		return cb_day2;
	}

	public void setCb_day2(JComboBox cb_day2) {
		this.cb_day2 = cb_day2;
	}

	public JComboBox getCb_start2() {
		return cb_start2;
	}

	public void setCb_start2(JComboBox cb_start2) {
		this.cb_start2 = cb_start2;
	}

	public JComboBox getCb_end2() {
		return cb_end2;
	}

	public void setCb_end2(JComboBox cb_end2) {
		this.cb_end2 = cb_end2;
	}

	public Vector getMajorIdList() {
		return majorIdList;
	}

	public void setMajorIdList(Vector majorIdList) {
		this.majorIdList = majorIdList;
	}

	public Vector getMajorNameList() {
		return majorNameList;
	}

	public void setMajorNameList(Vector majorNameList) {
		this.majorNameList = majorNameList;
	}

	public Vector getTime_list() {
		return time_list;
	}

	public void setTime_list(Vector time_list) {
		this.time_list = time_list;
	}

	public OtherActionListener getOtherActionListener() {
		return otherActionListener;
	}

	public void setOtherActionListener(OtherActionListener otherActionListener) {
		this.otherActionListener = otherActionListener;
	}

	public JTextField getTf_class_no() {
		return tf_class_no;
	}

	public void setTf_class_no(JTextField tf_class_no) {
		this.tf_class_no = tf_class_no;
	}

}
