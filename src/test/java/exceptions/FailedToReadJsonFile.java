package exceptions;

public class FailedToReadJsonFile extends RuntimeException {

    public FailedToReadJsonFile(String filePath) {
        super("Failed to read json file: " + filePath);
    }
}
