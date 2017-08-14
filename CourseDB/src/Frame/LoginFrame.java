package Frame;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.TextField;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import DB.DBConnector;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private LoginActionListener loginActionListener;
	
	private JTextField tfName;
	private JTextField tfPassword;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		
		loginActionListener = new LoginActionListener(this);
		
		setTitle("로그인");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JButton btn;

		contentPane = new JPanel();
		contentPane.setLayout(new GridLayout(3, 2, 5, 10));
		  
		contentPane.add(new JLabel(" 아이디"));
		
		tfName = new JTextField();
		contentPane.add(tfName);
		
		contentPane.add(new JLabel(" 비밀번호"));
		
		tfPassword = new JTextField();
		contentPane.add(tfPassword);
		
		btn = new JButton("로그인");
		btn.addActionListener(loginActionListener);
		contentPane.add(btn);
		
		btn = new JButton("취소");
		btn.addActionListener(loginActionListener);
		contentPane.add(btn);

		setSize(300, 150);

		setContentPane(contentPane);
	}

	public JTextField getTfName() {
		return tfName;
	}

	public void setTfName(JTextField tfName) {
		this.tfName = tfName;
	}

	public JTextField getTfPassword() {
		return tfPassword;
	}

	public void setTfPassword(JTextField tfPassword) {
		this.tfPassword = tfPassword;
	}
	
	

}
