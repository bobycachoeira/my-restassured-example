package config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import utils.Utils;

public class RestAssuredConfig {

    public static RequestSpecification getRequestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(Utils.getBaseUrl())
                .setContentType(ContentType.JSON)
                .build();
    }

    public static void setup() {
        RestAssured.requestSpecification = getRequestSpecification();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}