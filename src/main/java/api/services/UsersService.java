package api.services;

import api.base.BaseService;
import api.models.User;
import io.restassured.response.Response;

public class UsersService extends BaseService {

    public Response createUser(User user) {
        return request()
                .body(user)
                .post("/user");
    }

    public Response getUserByUsername(String username) {
        return request()
                .get("/user/" + username);
    }

    public Response deleteUser(String username) {
        return request()
                .delete("/user/" + username);
    }

    public Response login(String username, String password) {
        return request()
                .queryParam("username", username)
                .queryParam("password", password)
                .get("/user/login");
    }
}
