package Frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

public class AdminFrame extends JFrame {

private JPanel contentPane;
	
	DefaultTableModel model;
	
	AdminActionListener adminActionListener;
	
	Vector data, col, col2;
	
	private int selectedClassId;
	private String selectedStudentId;
	
	private boolean is_student_mode;
	
	private JTable table;
	private JTextField tf_course_id;
	private JTextField tf_class_id;
	private JTextField tf_course_name;
	private JTextField tf_lecturer_name;
	
	JButton bt_search;
	JButton bt_delete;
	JButton bt_insert_course;
	JButton bt_student;
	private JLabel labelInfo;
	private JButton bt_insert_class;
	private JButton bt_analysis;
	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public AdminFrame() {
		adminActionListener = new AdminActionListener(this);
		contentPane = new JPanel();
		is_student_mode = true;
		
		setTitle("수강 관리");
        setSize(800, 600);
        setLocation(0, 120);
        getContentPane().setLayout(null);
        
        col = new Vector();
        col.add("id");
        col.add("학번");
        col.add("이름");
        col.add("비밀번호");
        col.add("성별");
        col.add("년도");
        col.add("전공");
        col.add("강사명");
        
        col2 = new Vector();
        col2.add("id");
        col2.add("과목 번호");
        col2.add("수업 번호");
        col2.add("과목명");
        col2.add("전공");
        col2.add("학점");
        col2.add("강사명");
        col2.add("최대 수강수");
        
        
        model = new DefaultTableModel(col,0);
              
        table = new JTable(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"학번", "이름", "비밀번호", "성별", "년도", "전공", "강사명"
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
                
                Integer temp = 0;
 
                try {
                	temp = (Integer)selectedRow.get(0);
                } catch (Exception e2) {
                	selectedStudentId = (String) selectedRow.get(0);
                } finally {
                }
                
                selectedClassId = temp.intValue();
                
                System.out.println(selectedClassId + " " + selectedStudentId);

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
        
        JTableStudentRefresh(data);
        
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
        
        bt_search = new JButton("\uAC15\uC758 \uAC80\uC0C9");
        bt_search.setBounds(22, 181, 97, 23);
        bt_search.addActionListener(adminActionListener);
        getContentPane().add(bt_search);
        
        bt_delete = new JButton("\uD3D0\uAC15");
        bt_delete.addActionListener(adminActionListener);
        bt_delete.setBounds(646, 181, 116, 23);
        getContentPane().add(bt_delete);
        
        bt_insert_course = new JButton("\uACFC\uBAA9 \uAC1C\uC124");
        bt_insert_course.addActionListener(adminActionListener);
        bt_insert_course.setBounds(400, 181, 124, 23);
        getContentPane().add(bt_insert_course);
        
        JLabel label_5 = new JLabel("\uC0AC\uC6A9\uC790 \uC815\uBCF4");
        label_5.setBounds(400, 10, 85, 15);
        getContentPane().add(label_5);
        
        bt_student = new JButton("\uD559\uC0DD \uBAA9\uB85D");
        bt_student.setBounds(400, 219, 124, 23);
        bt_student.addActionListener(adminActionListener);
        getContentPane().add(bt_student);
        
        labelInfo = new JLabel("");
        labelInfo.setBounds(12, 229, 360, 23);
        getContentPane().add(labelInfo);
        
        bt_insert_class = new JButton("\uAC15\uC758 \uAC1C\uC124");
        bt_insert_class.setBounds(536, 181, 97, 23);
        bt_insert_class.addActionListener(adminActionListener);
        getContentPane().add(bt_insert_class);
        
        bt_analysis = new JButton("\uD1B5\uACC4");
        bt_analysis.setBounds(536, 219, 97, 23);
        bt_analysis.addActionListener(adminActionListener);
        getContentPane().add(bt_analysis);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         
        setVisible(true);
	}
	
	public void JTableStudentRefresh(Vector data) {
		selectedClassId = 0;
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
	
	public void JTableClassRefresh(Vector data) {
		selectedClassId = 0;
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

	public int getSelectedClassId() {
		return selectedClassId;
	}

	public void setSelectedClassId(int selectedClassId) {
		this.selectedClassId = selectedClassId;
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
		return bt_delete;
	}

	public void setBt_insert_delete(JButton bt_insert_delete) {
		this.bt_delete = bt_insert_delete;
	}

	public JButton getBt_mode_change() {
		return bt_insert_course;
	}

	public void setBt_mode_change(JButton bt_mode_change) {
		this.bt_insert_course = bt_mode_change;
	}

	public JButton getBt_time_table() {
		return bt_student;
	}

	public void setBt_time_table(JButton bt_time_table) {
		this.bt_student = bt_time_table;
	}

	public boolean isIs_student_mode() {
		return is_student_mode;
	}

	public void setIs_student_mode(boolean is_student_mode) {
		this.is_student_mode = is_student_mode;
	}

}
