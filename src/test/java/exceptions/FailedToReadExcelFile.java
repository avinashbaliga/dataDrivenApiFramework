package exceptions;

public class FailedToReadExcelFile extends RuntimeException {

    public FailedToReadExcelFile(String file) {
        super("Failed to read excel file: " + file);
    }
}
