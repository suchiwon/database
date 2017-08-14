package Frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import DB.DBConnector;
import DB.Query;

public class LoginActionListener implements ActionListener {
	
	LoginFrame loginFrame;
	
	LoginActionListener(LoginFrame loginFrame) {
		this.loginFrame = loginFrame;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		  
		if(btn.getText().equals("로그인")) {
			
		   String name = loginFrame.getTfName().getText();
		   String password = loginFrame.getTfPassword().getText();
		   
		    if (name.equals("admin")) {
		    	new AdminFrame();
		    	
		    	loginFrame.dispose();
		    	
		    	return;
		    }
		   
		   try {
			if (LoginTest(name, password)) {
				   getStudentInfo(name);
				
				   new MainFrame();
				   
				   loginFrame.dispose();
			   } else {
				   String message = "로그인에 실패하였습니다.";
				   JOptionPane.showMessageDialog(null, message); 
			   }
		   } catch (ClassNotFoundException e1) {
			   e1.printStackTrace();
		   } catch (SQLException e1) {
			   e1.printStackTrace();
		   }	
		   //btn.setText(str);
		   
		} else {
			
		   loginFrame.dispose();
		   
		}
	}
	
	private boolean LoginTest(String name, String password) throws ClassNotFoundException, SQLException {	
		Query query = Query.getInstance();
		
		boolean loginSuccess = query.LoginTestQuery(name, password);
		
		return loginSuccess;
	}
	
	private void getStudentInfo(String id) throws ClassNotFoundException, SQLException {
		
		Query query = Query.getInstance();
		
		query.getStudentInfo(id);
	}

}
