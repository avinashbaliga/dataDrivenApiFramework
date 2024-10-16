package exceptions;

public class TestConfigNotFoundException extends RuntimeException {

    public TestConfigNotFoundException(String key) {
        super("Key: " + key + " not found in the testConfig.properties file");
    }
}
