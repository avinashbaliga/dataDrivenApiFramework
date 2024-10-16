package exceptions;

public class FailedToWriteExcel extends RuntimeException {

    public FailedToWriteExcel(String filePath) {
        super("Failed to write into excel file: " + filePath);
    }
}
