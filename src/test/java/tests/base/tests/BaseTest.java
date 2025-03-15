package tests.base.tests;


import io.restassured.RestAssured;
// import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
// import utils.Utils;
import utils.Utils;

import org.junit.jupiter.api.Assertions;
// import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeAll;

@Slf4j
public class BaseTest {

    @BeforeAll
    public static void setUp() {
        log.info("Iniciando os testes de API.");
        RestAssured.baseURI = Utils.getBaseUrl();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

     public static void validateStatusCode(Response response, int expectedStatusCode) {
        Assertions.assertEquals(expectedStatusCode, response.getStatusCode());
    }

    public static void validateResponseBodyField(Response response, String fieldPath, Object expectedValue) {
        Assertions.assertEquals(expectedValue, response.jsonPath().get(fieldPath));
    }
}
