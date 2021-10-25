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

	public void write(FoodSecurityCard foodSecurityCard) {
		Row row = sheet.createRow(rowCount++);

		int colNum = 0;

		Cell cell = row.createCell(colNum++);
		cell.setCellValue(foodSecurityCard.getHeadOfHouseHold());

		cell = row.createCell(colNum++);
		cell.setCellValue(foodSecurityCard.getFatherHusbandName());

		cell = row.createCell(colNum++);
		cell.setCellValue(foodSecurityCard.getUid());

		cell = row.createCell(colNum++);
		cell.setCellValue(foodSecurityCard.getFamilySize());

		cell = row.createCell(colNum++);
		cell.setCellValue(foodSecurityCard.getAddress());

		for (FoodSecurityCardMember member : foodSecurityCard.getFamilyMembers()) {
			cell = row.createCell(colNum++);
			cell.setCellValue(member.getName());
			
			cell = row.createCell(colNum++);
			cell.setCellValue(member.getDob());
			
			cell = row.createCell(colNum++);
			cell.setCellValue(member.getUid());
		}
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
