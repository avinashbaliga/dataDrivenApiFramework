package baseClass;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import utilities.ExcelWorker;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BaseTest {

    private ExcelWorker excelWorker = null;

    //ToDo: Method name here is not useful. Write code to put api basepath instead
    @AfterMethod
    public void afterMethod(ITestResult result) {
        if (excelWorker == null)
            excelWorker = new ExcelWorker("src/main/resources/apiDetails.xlsx");

        List<String> dataToWrite = new ArrayList<>();
        dataToWrite.add(String.valueOf(LocalDateTime.now()));
        dataToWrite.add(result.getMethod().getMethodName());
        dataToWrite.add((result.isSuccess()) ? "Passed" : "Failed. " + result.getThrowable().getMessage());

        excelWorker.writeToExcel(dataToWrite, "ExecutionDetails");
    }
}
