package Frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import DTO.StudentInfo;

import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	
	DefaultTableModel model;
	
	MainActionListener mainActionListener;
	
	Vector data, col, col2;
	
	Vector backDataList;
	
	private String selectedCourseId;
	private int selectedClassNo;
	private int selectedClassId;
	private int maxPerson;
	
	private JTable table;
	private JTextField tf_course_id;
	private JTextField tf_class_id;
	private JTextField tf_course_name;
	private JTextField tf_lecturer_name;
	
	JButton bt_search;
	JButton bt_insert_delete;
	JButton bt_mode_change;
	private JLabel labelInfo;
	private JLabel label_6;
	private JLabel lb_id;
	private JLabel label_7;
	private JLabel lb_name;
	private JLabel lblNewLabel;
	private JLabel lb_year;
	private JButton btn_back;
	private JLabel lblNewLabel_1;
	private JLabel lb_major;
	private JButton btnNewButton;
	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public MainFrame() {
		
		contentPane = new JPanel();
		
		setTitle("수강 신청 및 검색");
        setSize(800, 600);
        setLocation(0, 120);
        getContentPane().setLayout(null);
        
        mainActionListener = new MainActionListener(this);
        
        backDataList = new Vector();
        
        col = new Vector();
        col.add("id");
        col.add("과목 번호");
        col.add("수업 번호");
        col.add("과목명");
        col.add("전공");
        col.add("학점");
        col.add("강사명");
        col.add("최대 수강수");
        
        col2 = new Vector();
        col2.add("id");
        col2.add("과목 번호");
        col2.add("수업 번호");
        col2.add("과목명");
        col2.add("전공");
        col2.add("학점");
        col2.add("성적");
        
        model = new DefaultTableModel(col,0);
              
        table = new JTable(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"과목 번호", "수업 번호", "과목명", "전공", "학점", "강사명", "최대 신청인수"
        	}
        ));
        table.setFillsViewportHeight(true);
        
        table.addMouseListener(new java.awt.event.MouseListener() {
            public void mouseClicked(java.awt.event.MouseEvent e) {      
                int selectedIndex;      
                JTable jt = (JTable) e.getSource();   
                selectedIndex = jt.getSelectedRow();
                
                Vector selectedRow = (Vector)data.get(selectedIndex);
                
                System.out.println(selectedRow.toString());
                
                Integer temp = (Integer)selectedRow.get(0);
                
                selectedClassId = temp.intValue();
                
                System.out.println(selectedClassId);
                
                temp = (Integer)selectedRow.get(7);
                
                maxPerson = temp.intValue();
                
                selectedCourseId = (String)selectedRow.get(1);
            }

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
        });
        
        JTableRefresh(data);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(12, 252, 760, 300);
        getContentPane().add(scrollPane);
        
        JLabel label = new JLabel("\uAC80\uC0C9 \uC870\uAC74");
        label.setBounds(12, 10, 57, 15);
        getContentPane().add(label);
        
        JLabel label_1 = new JLabel("* \uC218\uC5C5 \uBC88\uD638");
        label_1.setBounds(22, 35, 71, 15);
        getContentPane().add(label_1);
        
        tf_course_id = new JTextField();
        tf_course_id.setBounds(101, 57, 116, 21);
        getContentPane().add(tf_course_id);
        tf_course_id.setColumns(10);
        
        JLabel label_3 = new JLabel("* \uD559\uC218 \uBC88\uD638");
        label_3.setBounds(22, 60, 71, 15);
        getContentPane().add(label_3);
        
        tf_class_id = new JTextField();
        tf_class_id.setBounds(101, 32, 116, 21);
        getContentPane().add(tf_class_id);
        tf_class_id.setColumns(10);
        
        JLabel label_2 = new JLabel("* \uAD50\uACFC\uBAA9\uBA85");
        label_2.setBounds(22, 88, 71, 15);
        getContentPane().add(label_2);
        
        tf_course_name = new JTextField();
        tf_course_name.setColumns(10);
        tf_course_name.setBounds(101, 85, 116, 21);
        getContentPane().add(tf_course_name);
        
        JLabel label_4 = new JLabel("* \uAD50\uAC15\uC0AC \uBA85");
        label_4.setBounds(22, 118, 71, 15);
        getContentPane().add(label_4);
        
        tf_lecturer_name = new JTextField();
        tf_lecturer_name.setColumns(10);
        tf_lecturer_name.setBounds(101, 115, 116, 21);
        getContentPane().add(tf_lecturer_name);
        
        bt_search = new JButton("\uAC80\uC0C9");
        bt_search.setBounds(12, 160, 97, 23);
        bt_search.addActionListener(mainActionListener);
        getContentPane().add(bt_search);
        
        bt_insert_delete = new JButton("\uC218\uAC15 \uC2E0\uCCAD");
        bt_insert_delete.addActionListener(mainActionListener);
        bt_insert_delete.setBounds(536, 219, 97, 23);
        getContentPane().add(bt_insert_delete);
        
        bt_mode_change = new JButton("\uC2E0\uCCAD \uBAA9\uB85D \uBCF4\uAE30");
        bt_mode_change.addActionListener(mainActionListener);
        bt_mode_change.setBounds(400, 219, 124, 23);
        getContentPane().add(bt_mode_change);
        
        JLabel label_5 = new JLabel("\uC0AC\uC6A9\uC790 \uC815\uBCF4");
        label_5.setBounds(400, 10, 85, 15);
        getContentPane().add(label_5);
        
        labelInfo = new JLabel("");
        labelInfo.setBounds(12, 229, 360, 23);
        getContentPane().add(labelInfo);
        
        label_6 = new JLabel("\uD559\uBC88");
        label_6.setBounds(444, 35, 57, 15);
        getContentPane().add(label_6);
        
        lb_id = new JLabel(StudentInfo.getInstance().getId());
        lb_id.setHorizontalAlignment(SwingConstants.RIGHT);
        lb_id.setBounds(589, 35, 142, 15);
        getContentPane().add(lb_id);
        
        label_7 = new JLabel("\uC774\uB984");
        label_7.setBounds(444, 60, 57, 15);
        getContentPane().add(label_7);
        
        lb_name = new JLabel(StudentInfo.getInstance().getName());
        lb_name.setHorizontalAlignment(SwingConstants.RIGHT);
        lb_name.setBounds(589, 60, 142, 15);
        getContentPane().add(lb_name);
        
        lblNewLabel = new JLabel("\uD559\uB144");
        lblNewLabel.setBounds(444, 88, 57, 15);
        getContentPane().add(lblNewLabel);
        
        lb_year = new JLabel(Integer.toString(StudentInfo.getInstance().getYear()) + "학년");
        lb_year.setBounds(700, 88, 57, 15);
        getContentPane().add(lb_year);
        
        btn_back = new JButton("\uB418\uB3CC\uB9AC\uAE30");
        btn_back.addActionListener(mainActionListener);
        btn_back.setBounds(645, 219, 97, 23);
        getContentPane().add(btn_back);
        
        lblNewLabel_1 = new JLabel("\uC804\uACF5");
        lblNewLabel_1.setBounds(444, 118, 57, 15);
        getContentPane().add(lblNewLabel_1);
        
        lb_major = new JLabel(StudentInfo.getInstance().getMajor_name());
        lb_major.setHorizontalAlignment(SwingConstants.RIGHT);
        lb_major.setBounds(589, 118, 142, 15);
        getContentPane().add(lb_major);
        
        btnNewButton = new JButton("\uC2DC\uAC04\uD45C");
        btnNewButton.setBounds(645, 187, 97, 23);
        btnNewButton.addActionListener(mainActionListener);
        getContentPane().add(btnNewButton);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         
        setVisible(true);
	}
	
	public void JTableRefresh(Vector data) {
		selectedClassId = 0;
		selectedCourseId = "";
		this.data = data;
		@SuppressWarnings("serial")
		DefaultTableModel model = new DefaultTableModel(data, col) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setModel(model);
        jTableSet();
	}
	
	public void JTableInfoRefresh(Vector data) {
		selectedClassId = 0;
		selectedCourseId = "";
		this.data = data;
		@SuppressWarnings("serial")
		DefaultTableModel model = new DefaultTableModel(data, col2) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setModel(model);
        jTableSet();
	}
	
	private void jTableSet() {
        // 이동과 길이조절 여러개 선택 되는 것을 방지한다
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
         
        // 컬럼 정렬에 필요한 메서드
        DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
        celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
        DefaultTableCellRenderer celAlignRight = new DefaultTableCellRenderer();
        celAlignRight.setHorizontalAlignment(JLabel.RIGHT);
        DefaultTableCellRenderer celAlignLeft = new DefaultTableCellRenderer();
        celAlignLeft.setHorizontalAlignment(JLabel.LEFT);
         
        // 컬럼별 사이즈 조절 & 정렬
        table.getColumn("id").setWidth(0);
        table.getColumn("id").setMinWidth(0);
        table.getColumn("id").setMaxWidth(0);
        
        table.getColumnModel().getColumn(1).setPreferredWidth(10);
        table.getColumnModel().getColumn(1).setCellRenderer(celAlignCenter);
        table.getColumnModel().getColumn(2).setPreferredWidth(10);
        table.getColumnModel().getColumn(2).setCellRenderer(celAlignCenter);
        table.getColumnModel().getColumn(3).setPreferredWidth(10);
        table.getColumnModel().getColumn(3).setCellRenderer(celAlignCenter);
        table.getColumnModel().getColumn(4).setPreferredWidth(10);
        table.getColumnModel().getColumn(4).setCellRenderer(celAlignCenter);
        table.getColumnModel().getColumn(5).setPreferredWidth(10);
        table.getColumnModel().getColumn(5).setCellRenderer(celAlignCenter);
        table.getColumnModel().getColumn(6).setPreferredWidth(10);
        table.getColumnModel().getColumn(6).setCellRenderer(celAlignCenter);
        
    } // jTableRefresh : 테이블 옵션 설정하는 메서드
	
	public void resetTextField() {
		tf_course_id.setText("");
		tf_class_id.setText("");
		tf_course_name.setText("");
		tf_lecturer_name.setText("");
	}

	public JTextField getTf_course_id() {
		return tf_course_id;
	}

	public void setTf_course_id(JTextField tf_course_id) {
		this.tf_course_id = tf_course_id;
	}

	public JTextField getTf_class_id() {
		return tf_class_id;
	}

	public void setTf_class_id(JTextField tf_class_id) {
		this.tf_class_id = tf_class_id;
	}

	public JTextField getTf_course_name() {
		return tf_course_name;
	}

	public void setTf_course_name(JTextField tf_course_name) {
		this.tf_course_name = tf_course_name;
	}

	public JTextField getTf_lecturer_name() {
		return tf_lecturer_name;
	}

	public void setTf_lecturer_name(JTextField tf_lecturer_name) {
		this.tf_lecturer_name = tf_lecturer_name;
	}

	public String getSelectedCourseId() {
		return selectedCourseId;
	}

	public void setSelectedCourseId(String selectedCourseId) {
		this.selectedCourseId = selectedCourseId;
	}

	public int getSelectedClassNo() {
		return selectedClassNo;
	}

	public void setSelectedClassNo(int selectedClassNo) {
		this.selectedClassNo = selectedClassNo;
	}

	public int getSelectedClassId() {
		return selectedClassId;
	}

	public void setSelectedClassId(int selectedClassId) {
		this.selectedClassId = selectedClassId;
	}

	public int getMaxPerson() {
		return maxPerson;
	}

	public void setMaxPerson(int maxPerson) {
		this.maxPerson = maxPerson;
	}

	public JLabel getLabelInfo() {
		return labelInfo;
	}

	public void setLabelInfo(JLabel labelInfo) {
		this.labelInfo = labelInfo;
	}

	public JButton getBt_search() {
		return bt_search;
	}

	public void setBt_search(JButton bt_search) {
		this.bt_search = bt_search;
	}

	public JButton getBt_insert_delete() {
		return bt_insert_delete;
	}

	public void setBt_insert_delete(JButton bt_insert_delete) {
		this.bt_insert_delete = bt_insert_delete;
	}

	public JButton getBt_mode_change() {
		return bt_mode_change;
	}

	public void setBt_mode_change(JButton bt_mode_change) {
		this.bt_mode_change = bt_mode_change;
	}


	public Vector getBackDataList() {
		return backDataList;
	}

	public void setBackDataList(Vector backDataList) {
		this.backDataList = backDataList;
	}
}
