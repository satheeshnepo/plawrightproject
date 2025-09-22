package utils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.util.Iterator;

public class ExcelReader {
    private File path = null;
    private XSSFWorkbook workbook = null;
    private XSSFSheet worksheet;

    public ExcelReader(File path, String sheetName) throws Exception {
        this.path = path;
        workbook = new XSSFWorkbook(this.path);
        worksheet = workbook.getSheet(sheetName);
    }

    public int getNumberOfRows() {
        if (worksheet.getLastRowNum() == -1) {
            return 0;
        } else {
            return worksheet.getLastRowNum() + 1;
        }
    }

    public String getData(int rowPos, int colPos) {
        int colmnValue = 0;
        int rowValue = 0;
        Iterator<Row> rowIterator = worksheet.rowIterator();
        Row row = null;
        Cell cell = null;
        while (rowIterator.hasNext()) {
            rowValue++;
            if (rowValue == rowPos) {
                row = rowIterator.next();
                break;
            }
            rowIterator.next();
        }
        Iterator<Cell> col = row.iterator();
        while (col.hasNext()) {
            colmnValue++;
            if (colmnValue == colPos) {
                cell = col.next();
                if (cell.getCellType() == CellType.STRING) {
                    return cell.getStringCellValue();
                } else if (cell.getCellType() == CellType.BOOLEAN) {
                    return String.valueOf(cell.getBooleanCellValue());
                } else if (cell.getCellType() == CellType.FORMULA) {
                    return String.valueOf(cell.getCellFormula());
                } else if (cell.getCellType() == CellType.NUMERIC) {
                    return String.valueOf((int) cell.getNumericCellValue());
                } else {
                    return null;
                }
            }
            col.next();
        }
        return null;
    }
    public int getNumberOfCols() {
        int cellCount = 0;
        Iterator<Row> rowIter = worksheet.rowIterator();
        while (rowIter.hasNext()) {
            Iterator<Cell> cell = rowIter.next().iterator();
            while (cell.hasNext()) {
                cellCount = cellCount + 1;
                cell.next();

            }
            break;
        }
        return cellCount;
    }
}



