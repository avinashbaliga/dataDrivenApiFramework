package utilities;

import exceptions.EmptyExcelSheetException;
import exceptions.FailedToReadExcelFile;
import exceptions.FailedToWriteExcel;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelWorker {

    private final Workbook workbook;
    private static Workbook workbookToWrite = null;
    File file;

    public ExcelWorker(String filePath) {
        try {
            file = new File(filePath);
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

    public void writeToExcel(List<String> dataToWrite, String sheetName) {
        int existingNumberOfRows;
        Sheet sheetToWrite;
        Cell cellToWriteTo;

        if (workbookToWrite == null)
            createWorkBook();

        sheetToWrite = getExcelSheet(sheetName);
        existingNumberOfRows = sheetToWrite.getLastRowNum();
        Row row = sheetToWrite.createRow(existingNumberOfRows + 1);

        for (int counter = 0; counter < dataToWrite.size(); counter++) {
            cellToWriteTo = row.createCell(counter);
            cellToWriteTo.setCellValue(dataToWrite.get(counter));
        }

        writeDataToExcel();
    }

    private void createWorkBook() {
        try {
            FileInputStream inputStream = new FileInputStream(file);
            workbookToWrite = new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new FailedToWriteExcel(file.getPath());
        }
    }

    private Sheet getExcelSheet(String sheetName) {
        try {
            return workbookToWrite.getSheet(sheetName);
        } catch (Exception e) {
            e.printStackTrace();
            return workbookToWrite.createSheet(sheetName);
        }
    }

    private void writeDataToExcel() {
        try {
            workbookToWrite.write(new FileOutputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
            throw new FailedToWriteExcel(file.getPath());
        }
    }
}
