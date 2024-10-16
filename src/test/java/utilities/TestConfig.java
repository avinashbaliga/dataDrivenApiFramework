package utilities;

import exceptions.FailedToLoadConfigFile;
import exceptions.TestConfigNotFoundException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestConfig {

    private static Properties properties = null;

    public static String get(String key) {
        initiatePropertiesFile();

        if (!properties.containsKey(key))
            throw new TestConfigNotFoundException(key);

        return properties.getProperty(key);
    }

    private static void initiatePropertiesFile() {
        if (properties == null) {
            File file = new File("src/main/resources/testConfig.properties");
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                properties = new Properties();
                properties.load(fileInputStream);
            } catch (IOException e) {
                throw new FailedToLoadConfigFile(file.getPath());
            }
        }
    }
}
