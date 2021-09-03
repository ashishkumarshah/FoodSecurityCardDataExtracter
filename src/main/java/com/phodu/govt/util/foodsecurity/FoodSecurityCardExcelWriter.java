package com.phodu.govt.util.foodsecurity;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FoodSecurityCardExcelWriter {
	private int rowCount = 0;
	private String outputFileName;
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;

	public FoodSecurityCardExcelWriter(String outputFileName) {
		this.outputFileName = outputFileName;
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet("Venkat Rao Best");
	}

	public void write(FoodSecurityCard foodSecurityCards) {
		Row row = sheet.createRow(rowCount++);

		int colNum = 0;

		Cell cell = row.createCell(colNum++);
		cell.setCellValue(foodSecurityCards.getHeadOfHouseHold());

		cell = row.createCell(colNum++);
		cell.setCellValue(foodSecurityCards.getFatherHusbandName());

		cell = row.createCell(colNum++);
		cell.setCellValue(foodSecurityCards.getUid());

		cell = row.createCell(colNum++);
		cell.setCellValue(foodSecurityCards.getFamilySize());

		cell = row.createCell(colNum++);
		cell.setCellValue(foodSecurityCards.getAddress());
	}

	public void close() {
		try {
			FileOutputStream outputStream = new FileOutputStream(outputFileName);
			workbook.write(outputStream);
			workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
