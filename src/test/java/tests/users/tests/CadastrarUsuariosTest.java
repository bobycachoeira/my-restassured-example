package tests.users.tests;

import com.github.javafaker.Faker;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import tests.base.tests.BaseTest;
import tests.users.requests.UserRequest;
import utils.Utils;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static utils.RestAssuredAllureLogger.logResponseData;

@Feature("Cadastrar usuários")
public class CadastrarUsuariosTest extends BaseTest {
    UserRequest usuarios = new UserRequest();
    Faker faker = Utils.faker;

    @Test
    @Tag("todos")
    @Description("Deve cadastrar um usuário administrador com sucesso")
    public void deveCadastrarUsuarioAdministradorComSucesso() throws Exception {
        Response response = usuarios.cadastrar(true)
                .then()
                .log().ifValidationFails()
                .statusCode(HttpStatus.SC_CREATED)
                .body("message", equalTo("Cadastro realizado com sucesso"), "_id", notNullValue())
                .extract()
                .response();

        logResponseData(response);
    }

    @Test
    @Tag("todos")
    @Description("Deve cadastrar um usuário administrador nome em branco")
    public void deveCadastrarUsuarioAdministradorNomeEmBranco() throws Exception {
        Response response = usuarios.cadastrar("", faker.internet().emailAddress(), "teste", true)
                .then()
                .log().ifValidationFails()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("nome", equalTo("nome não pode ficar em branco"))
                .extract()
                .response();

        logResponseData(response);
    }

    @Test
    @Tag("todos")
    @Description("Deve cadastrar um usuário administrador email em branco")
    public void deveCadastrarUsuarioAdministradorEmailEmBranco() throws Exception {
        Response response = usuarios.cadastrar(faker.name().fullName(), "", "teste", true)
                .then()
                .log().ifValidationFails()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("email", equalTo("email não pode ficar em branco"))
                .extract()
                .response();

        logResponseData(response);
    }

    @Test
    @Tag("todos")
    @Description("Deve cadastrar um usuário administrador senha em branco")
    public void deveCadastrarUsuarioAdministradorSenhaEmBranco() throws Exception {
        Response response = usuarios.cadastrar(faker.name().fullName(), faker.internet().emailAddress(), "", true)
                .then()
                .log().ifValidationFails()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("password", equalTo("password não pode ficar em branco"))
                .extract()
                .response();

        logResponseData(response);
    }

    @Test
    @Tag("todos")
    @Description("Deve cadastrar um usuário não administrador com sucesso")
    public void deveCadastrarUsuarioNaoAdministradorComSucesso() throws Exception {
        Response response = usuarios.cadastrar(false)
                .then()
                .log().ifValidationFails()
                .statusCode(HttpStatus.SC_CREATED)
                .body("message", equalTo("Cadastro realizado com sucesso"), "_id", notNullValue())
                .extract()
                .response();

        logResponseData(response);
    }

}