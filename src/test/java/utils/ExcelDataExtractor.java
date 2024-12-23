package utils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;

public class ExcelDataExtractor {
    static XSSFWorkbook workbook;
    static XSSFSheet sheet;
    public static int rowNumber;

    public static Object[][] testDataExtractor(String workbookPath, String sheetName) throws IOException {
        DataFormatter formatter = new DataFormatter();
        workbook = new XSSFWorkbook(workbookPath);
        sheet = workbook.getSheet(sheetName);
        rowNumber = sheet.getPhysicalNumberOfRows();
        int colNumber = sheet.getRow(0).getPhysicalNumberOfCells();

        Object[][] x = new Object[rowNumber - 1][colNumber];
        for (int i = 1; i < rowNumber; i++) {
            for (int j = 0; j < colNumber; j++) {
                x[i - 1][j] = formatter.formatCellValue(sheet.getRow(i).getCell(j));
            }
        }

        workbook.close();

        return x;
    }
}
