package com.aqua.office.poi.excel;

import java.io.IOException;
import java.text.DecimalFormat;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestXlsx {

	public static void main(String... strings) {
		String filePath = "d:\\FAPackaging_TS.xlsx";
		TestXlsx testXlsx = new TestXlsx();
		testXlsx.parse(filePath);
	}

	private void parse(String filePath) {
		try {
			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(filePath);
			XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
			int rows = sheet.getPhysicalNumberOfRows();
			if (rows > 0) {
				int i = 0;
				for (i = sheet.getFirstRowNum(); i < sheet
						.getPhysicalNumberOfRows(); i++) {
					XSSFRow row = sheet.getRow(i);
					if (row != null) {
						for (int j = row.getFirstCellNum(); j < row
								.getPhysicalNumberOfCells(); j++) {
							XSSFCell cell = row.getCell(j);
							System.out.println(getCellValue(cell));
						}
					}
				}
				System.out.println("rowNumber="+i);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getCellValue(XSSFCell cell) {
		String value = "";
		DecimalFormat format = new DecimalFormat("##.00");
		if (cell != null) {
			XSSFComment cellComment = cell.getCellComment();
			String cellCommentValue = "";
			if (cellComment != null) {
				cellCommentValue = cellComment.getString().toString();
			}
			String cellValue = "";
			switch (cell.getCellType()) {
			case XSSFCell.CELL_TYPE_NUMERIC:
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					cellValue = HSSFDateUtil.getJavaDate(
							cell.getNumericCellValue()).toString();
				} else {
					cellValue = String.valueOf(cell.getNumericCellValue());
					if (!cellValue.equals("NaN")) {
						double d = cell.getNumericCellValue();
						cellValue = format.format(d);
						if (cellValue.startsWith(".")) {
							cellValue = "0" + cellValue;
						}
					}
				}
				break;
			case XSSFCell.CELL_TYPE_STRING:
				cellValue = cell.getRichStringCellValue().toString();
				break;
			case XSSFCell.CELL_TYPE_FORMULA:
				cellValue = String.valueOf(cell.getNumericCellValue());
				if (cellValue.equals("NaN")) {
					cellValue = cell.getRichStringCellValue().toString();
				} else {
					double d = cell.getNumericCellValue();
					cellValue = format.format(d);
					if (cellValue.startsWith(".")) {
						cellValue = "0" + cellValue;
					}
				}
				break;
			case XSSFCell.CELL_TYPE_BOOLEAN:
				cellValue = " " + cell.getBooleanCellValue();
				break;
			case XSSFCell.CELL_TYPE_BLANK:
				cellValue = "";
				break;
			case XSSFCell.CELL_TYPE_ERROR:
				cellValue = "";
				break;
			default:
				cellValue = cell.getRichStringCellValue().toString();
			}
			value = cellValue + " " + cellCommentValue;
		}
		return value;
	}

}
