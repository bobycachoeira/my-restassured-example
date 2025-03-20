package utils;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;

public class RestAssuredAllureLogger {

    @Step("Logar Request Data")
    public static void logRequestData(FilterableRequestSpecification request) {
        // VER AQUI SE TEM ALGO QUE PODE ESTAR GERANDO AQUELE LOG A MAIS NO ALLURE.
        Allure.addAttachment("Request", "text/plain", request.getMethod() + ": " + request.getURI());
        Allure.addAttachment("Request Headers", "text/plain", request.getHeaders().toString());
        if (request.getBody() != null && !request.getBody().toString().isEmpty()) {
            Allure.addAttachment("Request Body", "application/json", request.getBody().toString());
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