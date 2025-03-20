package tests.users.tests;


import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.restassured.response.Response;

import tests.base.tests.BaseTest;
import tests.users.requests.UserRequest;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static utils.RestAssuredAllureLogger.logResponseData;

@Feature("Excluir usuários")
public class ExcluirUsuariosTest extends BaseTest {

    UserRequest usuarios = new UserRequest();

    @Test
    @Tag("todos")
    @Description("Excluir um usuário administrador com sucesso.")
    public void excluirUsuarioAdministradorComSucesso() {
        String idUsuario = usuarios.cadastrar(true)
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .path("_id");

        Response response = usuarios.excluir(idUsuario)
                .then()
                .log().ifValidationFails()
                .extract()
                .response();
                logResponseData(response);

                response.then()
                .statusCode(HttpStatus.SC_OK)
                .body("message", equalTo("Registro excluído com sucesso"));
    }

    @Test
    @Tag("todos")
    @Description("Excluir um usuário não administrador com sucesso.")
    public void excluirUsuarioNaoAdministradorComSucesso() {
        String idUsuario = usuarios.cadastrar(true)
                .then()
                .log().ifValidationFails()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .path("_id");

        Response response = usuarios.excluir(idUsuario)
                .then()
                .log().ifValidationFails()
                .extract()
                .response();
                logResponseData(response);

                response.then()
                .statusCode(HttpStatus.SC_OK)
                .body("message", equalTo("Registro excluído com sucesso"));
    }

}
