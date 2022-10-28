package week8_Marathon;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelData {
	
	public static String[][] data(String fileName) throws IOException {

	
	XSSFWorkbook wb = new XSSFWorkbook("./TestData/"+fileName+".xlsx");
	XSSFSheet sh = wb.getSheetAt(0);
	int rowCount = sh.getLastRowNum();
	int colCount = sh.getRow(0).getLastCellNum();
	
	
	String[][] data = new String[rowCount][colCount];
	for(int i=1;i<rowCount;i++) {
		for(int j=0;j<colCount;j++) {
			String StringCellValue = sh.getRow(i).getCell(j).getStringCellValue();
			data[i-1][j] = StringCellValue;
			
		}
	}
	wb.close();
	return data;
	

}
}