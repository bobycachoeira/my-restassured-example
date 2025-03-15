package payloads.users;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import lombok.Data;
import models.users.UsersModel;
import utils.Utils;

@Data
public class UsersPayload {

    public static Faker faker = Utils.faker;

    public static String toJson(boolean administrador){
        UsersModel usuariosModel = new UsersModel(faker.name().fullName(), faker.internet().emailAddress(), "teste", administrador);
        return new Gson().toJson(usuariosModel);
    }

    public static String toJson(String nome, String email, String password, boolean administrador){
        UsersModel usuariosModel = new UsersModel(nome, email, password, administrador);
        return new Gson().toJson(usuariosModel);
    }
}