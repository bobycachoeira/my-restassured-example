package utils;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.RequestSpecification;

public class RestAssuredAllureLogger {

    @Step("Logar Request Data")
    public static void logRequestData(FilterableRequestSpecification request) {
        Allure.addAttachment("Request Method", "text/plain", request.getMethod());
        Allure.addAttachment("Request URL", "text/plain", request.getURI());
        Allure.addAttachment("Request Headers", "text/plain", request.getHeaders().toString());
        if (request.getBody() != null) {
            Allure.addAttachment("Request Body", "text/plain", request.getBody().toString());
        }
    }

    @Step("Logar Response Data")
    public static void logResponseData(Response response) {
        Allure.addAttachment("Response Status Code", "text/plain", String.valueOf(response.getStatusCode()));
        Allure.addAttachment("Response Headers", "text/plain", response.getHeaders().toString());
        if (response.getBody() != null) {
            Allure.addAttachment("Request Body", "text/plain", response.getBody().asPrettyString());
        }
    }
}