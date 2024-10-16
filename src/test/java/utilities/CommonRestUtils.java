package utilities;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CommonRestUtils {

    RequestSpecification request;

    public CommonRestUtils(String baseUri) {
        request = RestAssured.given();
        request.baseUri(baseUri);
    }

    public Response request(String basePath, Method httpMethod) {
        return request.basePath(basePath).log().all().request(httpMethod);
    }
}
