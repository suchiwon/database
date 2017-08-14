package Frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import DB.Query;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

public class AnalysisFrame extends JFrame {

	private JPanel contentPane;
	private JPanel contentPane_1;
	private JTable table;
	
	private Vector data;
	private Vector col;

	/**
	 * Create the frame.
	 */
	public AnalysisFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane_1 = new JPanel();
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane_1);
		contentPane_1.setLayout(null);
		
		contentPane = new JPanel();
		
		col = new Vector();
		data = new Vector();
		
		setTitle("통계");
        setSize(599, 479);
        setLocation(0, 120);
        getContentPane().setLayout(null);
        
        DefaultTableModel model = new DefaultTableModel(col,0);
        
        Vector planeData = new Vector();
        
        try {
			planeData = Query.getInstance().GetAnalysis();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        ParseData(planeData);
              
        DefaultTableModel model2 = new DefaultTableModel(data,col);
        
        DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
        
        table = new JTable(model2);
        
        for (int i = 0; i < col.size(); i++) {
        	//table.getColumnModel().getColumn(i).setPreferredWidth(10);
            table.getColumnModel().getColumn(i).setCellRenderer(celAlignCenter);
            table.getColumnModel().getColumn(i).setWidth(20);
        }
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(12, 10, 559, 421);
        getContentPane().add(scrollPane);

		
		JButton btnNewButton = new JButton("\uB2EB\uAE30");
		btnNewButton.setBounds(164, 235, 97, 23);
		contentPane.add(btnNewButton);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        setVisible(true);
	}
	
	private void ParseData(Vector planeData) {
		int maxYear = 0;
		int minYear = 9999;
		
		for (int i = 0; i < planeData.size(); i++) {
			Vector row = (Vector) planeData.get(i);
			
			int temp = (int) row.get(0);
			
			if (maxYear < temp) {
				maxYear = temp;
			}
			
			if (minYear > temp) {
				minYear = temp;
			}
		}
		
		int rowSize = maxYear - minYear + 1;
		
		ArrayList<Vector> dataArr = new ArrayList<Vector>();
		ArrayList<Integer> flag = new ArrayList<Integer>();
		
		
		for (int i = 0; i < rowSize; i++) {
			flag.add(0);
			dataArr.add(new Vector());
			dataArr.get(i).add(maxYear - i);
		}
		
		Object[] flagArr = flag.toArray();
		
		col.add("년도/과목명");
		
		int idx = 0;
		
		String courseTemp = "";
		
		if (planeData.size() > 0) {
			Vector row = (Vector) planeData.get(0);
			
			courseTemp = (String) row.get(1);
			
			int temp = (int) row.get(0);
			
			flagArr[temp - minYear] = (Integer) row.get(2);
			
			col.add(courseTemp);
		}
		
		for (int i = 1; i < planeData.size(); i++) {
			Vector row = (Vector) planeData.get(i);
			
			String courseName = (String) row.get(1);
			
			if (!courseName.equals(courseTemp)) {
				courseTemp = courseName;
				
				idx++;
				
				col.add(courseName);
				
				for (int j = 0; j < rowSize; j++) {
					dataArr.get(j).add(((Integer) flagArr[j]).intValue());
					flagArr[j] = 0;
				}
				
				int temp = (int) row.get(0);
				
				flagArr[temp - minYear] = (Integer) row.get(2);
				
			} else {
				int temp = (int) row.get(0);
				
				flagArr[temp - minYear] = (Integer) row.get(2);
			}
			
			for (int j = 0; j < rowSize; j++) {
				dataArr.get(j).add(((Integer) flagArr[j]).intValue());
				flagArr[j] = 0;
			}
		}
		
		for (int i = 0; i < rowSize; i++) {
			data.add(dataArr.get(i));
		}
	}
}
