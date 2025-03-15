package tests.users.requests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import payloads.users.UsersPayload;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.reset;

import org.apache.http.HttpStatus;

import io.qameta.allure.Step;

public class UserRequest {

    private final String PATH_USUARIOS = "/usuarios";

    @Step("Listar usuários cadastrados")
    public Response listar() {
        return given()
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .when()
                .get(PATH_USUARIOS);
    }

    @Step("Listar usuários cadastrados")
    public Response listar(String queryParam, String valueParam) {
        return given()
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .when()
                .queryParam(queryParam, valueParam)
                .get(PATH_USUARIOS);
    }


    @Step("Buscar usuário por ID")
    public Response buscarPorId(String id) {
        return given()
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .when()
                .pathParam("_id", id)
                .get(PATH_USUARIOS + "/{_id}");
    }

    @Step("Cadastrar usuários")
    public Response cadastrar(boolean administrador) {
        return given()
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .when()
                .body(UsersPayload.toJson(administrador))
                .post(PATH_USUARIOS);
    }

    @Step("Cadastrar usuários")
    public Response cadastrar(String nome, String email, String password, boolean administrador) {
        return given()
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .when()
                .body(UsersPayload.toJson(nome, email, password, administrador))
                .post(PATH_USUARIOS);
    }

    @Step("Excluir usuário")
    public Response excluir(String id) {
        return given()
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .when()
                .pathParam("_id", id)
                .delete(PATH_USUARIOS + "/{_id}");
    }

    @Step("Editar usuário")
    public Response alterar(String nome, String email, String password, boolean administrador, String id) {
        return given()
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .when()
                .body(UsersPayload.toJson(nome, email, password, administrador))
                .pathParam("_id", id)
                .put(PATH_USUARIOS + "/{_id}");
    }

    @Step("Buscar ID do primeiro usuário")
    public String getIdPrimeiroUsuario() {
        return listar().then().statusCode(HttpStatus.SC_OK).extract().path("usuarios[0]._id");
    }
}