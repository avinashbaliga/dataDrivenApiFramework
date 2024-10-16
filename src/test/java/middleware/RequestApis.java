package middleware;

import io.restassured.http.Method;
import io.restassured.response.Response;
import org.testng.Assert;
import utilities.CommonRestUtils;
import utilities.TestConfig;

public class RequestApis extends CommonRestUtils {

    public RequestApis() {
        super(TestConfig.get("baseUri"));
    }

    public void requestApis(String basePath, String method) {
        Response response = request(basePath, getHttpMethod(method));
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    private Method getHttpMethod(String method) {
        return Method.valueOf(Method.class, method.toUpperCase());
    }
}
