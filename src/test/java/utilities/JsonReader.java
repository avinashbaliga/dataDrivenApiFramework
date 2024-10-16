package utilities;


import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import exceptions.FailedToReadJsonFile;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JsonReader {

    File file;

    public JsonReader(String filePath) {
        file = new File(filePath);
    }

    public JsonElement getJsonData() {
        try {
            return JsonParser.parseReader(new FileReader(file));
        } catch (IOException e) {
            throw new FailedToReadJsonFile(file.getPath());
        }
    }
}
