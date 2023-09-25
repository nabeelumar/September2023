package e2eTesting;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.formula.functions.Rows;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelSampleMainTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		ArrayList<String> al = getData("/Users/Nabeel/Documents/Boy Names.xlsx", "TestData", "TestCaseName", "Green");
		for (int i = 0; i < al.size(); i++) {

			System.out.println(al.get(i));
		}

	
	}
	public static ArrayList<String> getData(String workbookPath,String workSheetName, String colName, String testCaseName) throws IOException{
	
	ArrayList<String> a = new ArrayList<String>();
	
	FileInputStream fis = new FileInputStream(workbookPath) ;
	XSSFWorkbook wb = new XSSFWorkbook(fis);
	int sheetcount = wb.getNumberOfSheets();
	for(int i=0; i<sheetcount;i++) {
		
		if(wb.getSheetName(i).equalsIgnoreCase(workSheetName)) {
			
			XSSFSheet sheet = wb.getSheetAt(i);
			Iterator<Row> row = sheet.iterator();
			Row firstRow = row.next();
			
			Iterator<Cell> cell = firstRow.cellIterator();
			int k=0;
			int col =0;
			while(cell.hasNext()) {
				Cell ce = cell.next();
				if(ce.getStringCellValue().equalsIgnoreCase(colName)) {
					
					col=k;
					break;
				}
				k++;	
			}
			
			while(row.hasNext()) {
				
				Row r = row.next();
				if(r.getCell(col).getStringCellValue().equalsIgnoreCase(testCaseName)) {
					
					Iterator<Cell> cellValues = r.cellIterator();
					while(cellValues.hasNext()) {
						
						Cell cellValue = cellValues.next();
						if(cellValue.getCellType()==CellType.STRING) {
							
							a.add(cellValue.getStringCellValue());
						}
						else {
							
							String val =NumberToTextConverter.toText(cellValue.getNumericCellValue());
							a.add(val);
						}
					}
				}
			}
		
	}
	
	
	
	
	}
	
		return a;

}
}