package tests;

import baseClass.BaseTest;
import dataProvider.ApiDataProvider;
import objects.ManageObjects;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestHealthCheck extends BaseTest {

    private ManageObjects manageObjects;

    @BeforeClass
    public void beforeClass() {
        manageObjects = new ManageObjects();
    }

    @Test(dataProviderClass = ApiDataProvider.class, dataProvider = "getApiDetails")
    public void testHealthCheck(String basePath, String httpMethod) {
        //log
        manageObjects.getRequestApis().requestApis(basePath, httpMethod);
    }
}
