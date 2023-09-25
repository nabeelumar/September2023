package e2eTesting;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderExcelSample {
	
	DataFormatter formatter = new DataFormatter();
	
	@Test(dataProvider="getData")
	public void test(String name, String testname, String id) {
		
		System.out.println(name+ " : "+testname+" : "+id );
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException{
		
		FileInputStream fis = new FileInputStream("/Users/Nabeel/Documents/Boy Names.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheetAt(1);
		int rowCount = sh.getPhysicalNumberOfRows();
		XSSFRow  row = sh.getRow(0);
		
		int colCount = row.getLastCellNum();
		Object[][] data = new Object[rowCount-1][colCount];
		
		for(int i=0;i<rowCount-1;i++) {
			
			row = sh.getRow(i+1);
			for(int j=0; j<colCount;j++) {
				
				XSSFCell cell = row.getCell(j);
				data[i][j] = formatter.formatCellValue(cell);
				
				
			}
			
		}
		
		
		return data;


}

}