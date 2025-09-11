package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {

    private Workbook workbook;

    public ExcelUtils(String filePath) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fis);
    }

    // Get row count excluding completely empty rows
    public int getRowCount(String sheetName) {
        Sheet sheet = workbook.getSheet(sheetName);
        int physicalRows = sheet.getPhysicalNumberOfRows();
        int actualRows = 0;

        for (int i = 0; i < physicalRows; i++) {
            Row row = sheet.getRow(i);
            if (row != null && !isRowEmpty(row)) {
                actualRows++;
            }
        }
        return actualRows;
    }

    // Check if row is completely empty
    private boolean isRowEmpty(Row row) {
        for (Cell cell : row) {
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                return false;
            }
        }
        return true;
    }

    // Get cell data safely
    public String getCellData(String sheetName, int rowNum, int colNum) {
        Sheet sheet = workbook.getSheet(sheetName);
        Row row = sheet.getRow(rowNum);

        if (row == null) return "";
        Cell cell = row.getCell(colNum);

        if (cell == null) return "";

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf((int) cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }

    // Get column count for first non-empty row
    public int getColumnCount(String sheetName) {
        Sheet sheet = workbook.getSheet(sheetName);
        for (Row row : sheet) {
            if (!isRowEmpty(row)) {
                return row.getPhysicalNumberOfCells();
            }
        }
        return 0;
    }
}
