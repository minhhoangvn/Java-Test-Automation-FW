
package com.mh.ta.core.helper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mh.ta.core.exception.TestContextException;



/**
 * @author minhhoang
 *
 */
public class ExcelUtility {

	public List<List<String>> readAllSheetValue(String path, String sheetName, Boolean hasHeader) {
		XSSFSheet spreadsheet = this.openWorkSheet(path, sheetName);
		Iterator<Row> rowIterator = spreadsheet.iterator();
		List<List<String>> sheetData = this.getSheetData(rowIterator);
		return hasHeader ? sheetData.subList(1, sheetData.size()) : sheetData;
	}

	public List<String> readAllRowValue(String path, String sheetName, int rowIndex) {
		XSSFSheet spreadsheet = this.openWorkSheet(path, sheetName);
		Iterator<Row> rowIterator = spreadsheet.iterator();
		return this.getSheetData(rowIterator).get(rowIndex);
	}

	private XSSFSheet openWorkSheet(String path, String sheetName) {
		XSSFWorkbook workBook = this.openWorkBook(path);
		return workBook.getSheet(sheetName);
	}

	private XSSFWorkbook openWorkBook(String path) {
		try {
			return new XSSFWorkbook(new File(path));
		} catch (IOException | InvalidFormatException e) {
			throw new TestContextException("Can not open test data file, " + e);
		}
	}

	private List<List<String>> getSheetData(Iterator<Row> rowIterator) {
		List<List<String>> sheetData = new ArrayList<>();
		while (rowIterator.hasNext()) {
			XSSFRow row = (XSSFRow) rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			sheetData.add(this.getRowData(cellIterator));
		}
		return sheetData;
	}

	private List<String> getRowData(Iterator<Cell> cellIterator) {
		List<String> rowValue = new ArrayList<>();
		while (cellIterator.hasNext()) {
			Cell cell = cellIterator.next();
			rowValue.add(cell.toString().trim().length() == 0 ? "" : cell.toString());
		}
		return rowValue;
	}
}
