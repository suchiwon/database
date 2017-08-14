package DTO;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelMaker {
	public void MakeTimeTableExcel(Vector timeData, Vector data) throws IOException {
		HSSFWorkbook workbook=new HSSFWorkbook();
		HSSFSheet sheet=workbook.createSheet("��Ʈ��");
		HSSFRow row=null;
		HSSFCell cell=null;
		
		for (int i = 0; i < 33; i++) {
			row = sheet.createRow(i);
			
			for (int j = 0; j < 6; j++) {
				cell = row.createCell(j);
			}
		}
		
		row = sheet.getRow(0);
		
		cell = row.getCell(1);
		cell.setCellValue("��");
		cell = row.getCell(2);
		cell.setCellValue("ȭ");
		cell = row.getCell(3);
		cell.setCellValue("��");
		cell = row.getCell(4);
		cell.setCellValue("��");
		cell = row.getCell(5);
		cell.setCellValue("��");
		
			
		
		for (int i = 1; i < 33; i++) {
			row = sheet.getRow(i);
			
			cell = row.getCell(0);
			
			cell.setCellValue(i + "����");
		}
		
		for (int i = 0; i < data.size(); i++) {
			Vector timeRow = (Vector) timeData.get(i);
			Vector dataRow = (Vector) data.get(i);
			
			int day = (int) timeRow.get(0);
			int start = (int) timeRow.get(1);
			int end = (int) timeRow.get(2);
			
			start += 23;
			end += 23;
			
			if (start > 0 && end < 33) {
				
				for (int k = start; k <= end; k++) {
					row = sheet.getRow(k);
					
					if (day <= 5) {
						cell = row.getCell(day);
						
						String val = Integer.toString((int) dataRow.get(2)) + " / ";
						val += dataRow.get(3) + " / ";
						val += Integer.toString((int) dataRow.get(4)) + " / ";
						val += dataRow.get(5);
						
						cell.setCellValue(val);
					}								
				}
			}
		}
		
		FileOutputStream fileoutputstream=new FileOutputStream("timetable.xls");
		//������ ����
		workbook.write(fileoutputstream);
		//�ʼ��� �ݾ��־���� 
		fileoutputstream.close();
	}
}
