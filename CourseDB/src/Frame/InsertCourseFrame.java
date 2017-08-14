package Frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class InsertCourseFrame extends JFrame {

	private JPanel contentPane;
	private JTextField tf_course_id;
	private JTextField tf_course_name;
	private JComboBox cb_credit;
	
	private OtherActionListener otherActionListener;
	
	JButton bt_OK;
	/**
	 * Create the frame.
	 */
	public InsertCourseFrame() {
		otherActionListener = new OtherActionListener(this);
		
		contentPane = new JPanel();
		
		setTitle("과목 개설");
        setSize(396, 221);
        setLocation(0, 120);
        getContentPane().setLayout(null);
        
        JLabel label_1 = new JLabel("\uACFC\uBAA9 \uBC88\uD638(\uC601\uC5B4 3\uC790\uB9AC + \uC22B\uC790 4\uC790\uB9AC)");
        label_1.setBounds(22, 35, 195, 15);
        getContentPane().add(label_1);
        
        tf_course_id = new JTextField();
        tf_course_id.setBounds(229, 32, 143, 21);
        getContentPane().add(tf_course_id);
        tf_course_id.setColumns(10);
        
        JLabel label_3 = new JLabel("\uACFC\uBAA9\uBA85");
        label_3.setBounds(22, 60, 71, 15);
        getContentPane().add(label_3);
        
        tf_course_name = new JTextField();
        tf_course_name.setBounds(229, 57, 143, 21);
        getContentPane().add(tf_course_name);
        tf_course_name.setColumns(10);
        
        JLabel label_2 = new JLabel("\uD559\uC810");
        label_2.setBounds(22, 88, 71, 15);
        getContentPane().add(label_2);
        
        bt_OK = new JButton("\uACFC\uBAA9 \uAC1C\uC124");
        bt_OK.setBounds(40, 150, 97, 23);
        bt_OK.addActionListener(otherActionListener);
        getContentPane().add(bt_OK);
        
        cb_credit = new JComboBox();
        cb_credit.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6"}));
        cb_credit.setBounds(229, 88, 143, 21);
        getContentPane().add(cb_credit);
        
        JButton btnNewButton = new JButton("\uCDE8\uC18C");
        btnNewButton.setBounds(232, 150, 97, 23);
        btnNewButton.addActionListener(otherActionListener);
        getContentPane().add(btnNewButton);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         
        setVisible(true);
	}
	public JTextField getTf_course_id() {
		return tf_course_id;
	}
	public void setTf_course_id(JTextField tf_course_id) {
		this.tf_course_id = tf_course_id;
	}
	public JTextField getTf_course_name() {
		return tf_course_name;
	}
	public void setTf_course_name(JTextField tf_course_name) {
		this.tf_course_name = tf_course_name;
	}
	public JComboBox getCb_credit() {
		return cb_credit;
	}
	public void setCb_credit(JComboBox cb_credit) {
		this.cb_credit = cb_credit;
	}
	public JButton getBt_OK() {
		return bt_OK;
	}
	public void setBt_OK(JButton bt_OK) {
		this.bt_OK = bt_OK;
	}

}
