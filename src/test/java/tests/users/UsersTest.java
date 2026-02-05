package tests.users;

import io.restassured.response.Response;
import api.models.User;
import api.services.UsersService;
import org.junit.jupiter.api.Test;
import tests.base.BaseTest;

import static org.junit.jupiter.api.Assertions.*;

public class UsersTest extends BaseTest {

    UsersService usersService = new UsersService();

    @Test
    void deveCriarEBuscarUsuario() {

        User user = criarUsuario();

        Response createResponse = usersService.createUser(user);
        assertEquals(200, createResponse.statusCode());

        Response getResponse = usersService.getUserByUsername(user.username);
        assertEquals(200, getResponse.statusCode());
        assertEquals(user.username, getResponse.jsonPath().getString("username"));
        assertEquals(user.firstName, getResponse.jsonPath().getString("firstName"));
    }

    @Test
    void deveDeletarUsuario() {

        User user = criarUsuario();
        usersService.createUser(user);

        Response deleteResponse = usersService.deleteUser(user.username);
        assertEquals(200, deleteResponse.statusCode());
    }

    @Test
    void deveRetornarUserNotFound() {

        Response response = usersService.getUserByUsername("usuario_inexistente");
        assertEquals(404, response.statusCode());
        assertEquals("User not found", response.jsonPath().getString("message"));
    }

    private User criarUsuario() {
        User user = new User();
        user.id = 101;
        user.username = "joice";
        user.firstName = "Joice";
        user.lastName = "QA";
        user.email = "joice@test.com";
        user.password = "1234";
        user.phone = "999999999";
        return user;
    }
}