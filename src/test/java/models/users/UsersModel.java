package models.users;
import lombok.Data;

@Data
public class UsersModel {

    private String nome;
    private String email;
    private String password;
    private String administrador;

    public UsersModel(String nome, String email, String password, boolean administrador) {
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.administrador = Boolean.toString(administrador);
    }
}