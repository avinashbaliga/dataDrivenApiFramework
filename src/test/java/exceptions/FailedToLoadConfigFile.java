package exceptions;

public class FailedToLoadConfigFile extends RuntimeException {
    public FailedToLoadConfigFile(String configFilePath) {
        super("Failed to load config file: " + configFilePath);
    }
}
