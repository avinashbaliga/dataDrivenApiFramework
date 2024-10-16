package dataProvider;

import com.google.gson.JsonObject;
import org.testng.annotations.DataProvider;
import utilities.ExcelReader;
import utilities.JsonReader;
import utilities.TestConfig;

import java.util.List;

public class ApiDataProvider {

    private String[][] getApiDetailsFromJson() {

        JsonReader jsonReader = new JsonReader("src/main/resources/apiDetails.json");
        JsonObject apiDetails = (JsonObject) jsonReader.getJsonData();
        String[][] apiData = new String[apiDetails.size()][2];
        int dataCounter = 0;

        for (String key : apiDetails.keySet()) {
            apiData[dataCounter][0] = ((JsonObject) apiDetails.get(key)).get("basePath").getAsString();
            apiData[dataCounter][1] = ((JsonObject) apiDetails.get(key)).get("httpMethod").getAsString();
            dataCounter++;
        }

        return apiData;
    }

    private String[][] getApiDetailsFromExcel() {
        ExcelReader excelReader = new ExcelReader("src/main/resources/apiDetails.xlsx");
        List<List<String>> apiData = excelReader.getAllData("Sheet1");
        String[][] apiDetails = new String[apiData.size()][2];

        for (int counter = 0; counter < apiData.size(); counter++) {
            apiDetails[counter][0] = apiData.get(counter).get(0);
            apiDetails[counter][1] = apiData.get(counter).get(1);
        }

        return apiDetails;
    }

    @DataProvider(name = "getApiDetails")
    public String[][] apiDataProvider() {
        return (TestConfig.get("apiDataFileType").equalsIgnoreCase("excel")) ? getApiDetailsFromExcel() : getApiDetailsFromJson();
    }
}
