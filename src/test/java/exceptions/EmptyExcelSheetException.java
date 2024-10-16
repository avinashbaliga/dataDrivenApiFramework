package exceptions;

public class EmptyExcelSheetException extends RuntimeException {

    public EmptyExcelSheetException(String sheetName) {
        super("Excel sheet: " + sheetName + " is empty");
    }
}
