package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	
	public static final String TEST_DATA_SHEET="./src/test/resources/TestData/TestData.xlsx";
	private static Workbook book;
	private static Sheet sheet;
	//RegistrationSheet
	public static Object[][] readTestData(String sheetname)  // as we are returning data as we do in data providers
	{
		Object data[][]= null;
		try {
			FileInputStream ip=new FileInputStream(TEST_DATA_SHEET);  // to establish connection with excel file
			
				book=WorkbookFactory.create(ip);
				sheet=book.getSheet(sheetname);
				data=new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
				int i1 =sheet.getLastRowNum();
				for(int i=0;i<sheet.getLastRowNum();i++)
				{
					for(int j=0;j<sheet.getRow(0).getLastCellNum();j++)
					{
						data[i][j]=sheet.getRow(i+1).getCell(j).toString();   // Java will convert to String
					}
				}
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InvalidFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return data;
	}
}
