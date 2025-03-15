package tests.users.tests;


import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import tests.base.tests.BaseTest;
import tests.users.requests.UserRequest;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;

@Feature("Excluir usuários")
public class ExcluirUsuariosTest extends BaseTest {

    UserRequest usuarios = new UserRequest();

    @Test
    @Tag("todos")
    @Description("Excluir um usuário administrador com sucesso.")
    public void excluirUsuarioAdministradorComSucesso() throws Exception {
        String idUsuario = usuarios.cadastrar(true)
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .path("_id");

        usuarios.excluir(idUsuario)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("message", equalTo("Registro excluído com sucesso"));
    }

    @Test
    @Tag("todos")
    @Description("Excluir um usuário não administrador com sucesso.")
    public void excluirUsuarioNaoAdministradorComSucesso() throws Exception {
        String idUsuario = usuarios.cadastrar(true)
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .path("_id");

        usuarios.excluir(idUsuario)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("message", equalTo("Registro excluído com sucesso"));
    }

}
