package utilities;

import exceptions.EmptyExcelSheetException;
import exceptions.FailedToReadExcelFile;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelReader {

    private final Workbook workbook;

    public ExcelReader(String filePath) {
        try {
            File file = new File(filePath);
            workbook = WorkbookFactory.create(file);
        } catch (IOException e) {
            throw new FailedToReadExcelFile(filePath);
        }
    }

    private List<Row> getRows(String sheetName) {
        List<Row> rows = new ArrayList<>();
        Sheet sheet = workbook.getSheet(sheetName);
        Iterator<Row> rowIterator = sheet.rowIterator();

        if (!rowIterator.hasNext())
            throw new EmptyExcelSheetException(sheetName);

        while (rowIterator.hasNext()) {
            rows.add(rowIterator.next());
        }

        return rows;
    }

    public List<List<String>> getAllData(String sheetName) {
        List<List<String>> allData = new ArrayList<>();
        List<String> eachRowData = new ArrayList<>();
        List<Row> rows = getRows(sheetName);

        for (Row row : rows) {

            for (int counter = 0; counter < row.getLastCellNum(); counter++) {
                eachRowData.add(row.getCell(counter).getStringCellValue());
            }

            allData.add(eachRowData);
            eachRowData = new ArrayList<>();
        }
        return allData;
    }
}
