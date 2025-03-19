package tests.users.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import tests.base.tests.BaseTest;
import tests.users.requests.UserRequest;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import utils.RestAssuredAllureLogger;

import java.util.concurrent.TimeUnit;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;
import static utils.RestAssuredAllureLogger.logResponseData;

@Slf4j
@Feature("Listar usuários")
public class ListarUsuariosTest extends BaseTest {

        UserRequest usuarios = new UserRequest();

        @Test
        @Tag("todos")
        @Description("Deve listar os usuários com sucesso.")
        public void deveListarUsuariosComSucesso() throws Exception {
            Response response = usuarios.listar()
                                .then()
                                .statusCode(HttpStatus.SC_OK)
                                .time(lessThan(2L), TimeUnit.SECONDS)
                                .extract()
                                .response();
                logResponseData(response);
        }

        @Test
        @Tag("todos")
        @Description("Deve listar os usuários administradores com sucesso.")
        public void deveListarUsuariosAdministradoresComSucesso() throws Exception {
            Response response = usuarios.listar("administrador", "true")
                                .then()
                                .statusCode(HttpStatus.SC_OK)
                                .body("usuarios", hasItems(allOf(hasEntry("administrador", "true"))))
                                .extract()
                                .response();
                logResponseData(response);

        }

        @Test
        @Tag("todos")
        @Description("Deve listar os usuários não administradores com sucesso.")
        public void deveListarUsuariosNaoAdministradoresComSucesso() throws Exception {
                Response response = usuarios.listar("administrador", "false")
                                .then()
                                .statusCode(HttpStatus.SC_OK)
                                .body("usuarios", hasItems(allOf(hasEntry("administrador", "false"))))
                                .extract()
                                .response();

                logResponseData(response);

        }

        @Tag("schemas")
        @Tag("todos")
        @Test
        @Description("Deve validar o schema json da lista de usuários")
        public void deveValidarSchemaListaUsuarios() throws Exception {
                Response response = usuarios.listar()
                                .then()
                                .statusCode(HttpStatus.SC_OK)
                                .body(matchesJsonSchemaInClasspath("schemas/users/users.json"))
                                .extract()
                                .response();

                logResponseData(response);
        }

        @Test
        @Tag("todos")
        @Description("Deve listar um usuário utilizando o ID")
        public void deveListarUsuarioUtilizandoId() throws Exception {
                String idUsuario = usuarios.getIdPrimeiroUsuario();
                Response response = usuarios.buscarPorId(idUsuario)
                                .then()
                                .log().ifValidationFails()
                                .statusCode(HttpStatus.SC_OK)
                                .time(lessThan(2L), TimeUnit.SECONDS)
                                .extract()
                                .response();

                logResponseData(response);
        }

        @Tag("schemas")
        @Tag("todos")
        @Test
        @Description("Deve validar o schema json de um usuário utilizando o ID")
        public void deveValidarSchemaUsuarioUtilizandoId() throws Exception {
                String idUsuario = usuarios.getIdPrimeiroUsuario();
                Response response = usuarios.buscarPorId(idUsuario)
                                .then()
                                .log().ifValidationFails()
                                .statusCode(HttpStatus.SC_OK)
                                .body(matchesJsonSchemaInClasspath("schemas/users/user.json"))
                                .extract()
                                .response();

                logResponseData(response);
                ;
        }
}
