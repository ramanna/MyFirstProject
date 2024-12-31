package com.ltim.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException {
		//String path = ".\\TestData\\LoginDDData.xlsx";
		String path = System.getProperty("user.dir") + "\\TestData\\LoginDDData.xlsx";
		ExcelUtility excelUtility = new ExcelUtility(path);
		int intRowCount = excelUtility.getRowCount("Sheet1");
		int intColCount = excelUtility.getCellCount("Sheet1", 1);

		String[][] data = new String[intRowCount][intColCount];
		for (int intRow = 1; intRow <= intRowCount; intRow++) {
			for (int intCol = 0; intCol < intColCount; intCol++) {
				data[intRow-1][intCol] = excelUtility.getCellData("Sheet1", intRow, intCol);
			}
		}
		return data;
	}
}// end class
