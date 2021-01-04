package email;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelSheetTransfer {

	public static String[] send(String file) throws IOException {
		// TODO Auto-generated method stub
		File excelFile = new File(file);
		FileInputStream fis = new FileInputStream(excelFile);
		
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		int rowNum = sheet.getLastRowNum() + 1;
		XSSFRow r = sheet.getRow(0);
		int columnNum = r.getLastCellNum();
		
		String excelArray[] = new String[rowNum * columnNum];
		
		Iterator<Row> rowIt = sheet.iterator();
		
		int i = 0;
		
		while (rowIt.hasNext()) {
			Row row = rowIt.next();
			
			Iterator<Cell> cellIterator = row.cellIterator();
			
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				excelArray[i] = cell.toString();
				i++;
				
			}
			
		}
		
		return excelArray;
		
	}
	

}
