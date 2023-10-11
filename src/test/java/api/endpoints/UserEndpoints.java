package api.endpoints;

import api.payload.User;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class UserEndpoints {

    public static Response createUser(User payload) {

        Response response = given()
                .contentType("application/json")
                .body(payload)
                .when()
                .post(Routes.postURL);

        return response;
    }

    public static Response ReadUser(String username) {
        Response response = given()
                .pathParam("username",username)
                .when()
                .get(Routes.getURL);
        return response;
    }

    public static Response UpdateUser(String username, User payload) {
        Response response = given()
                .contentType("application/json")
                .body(payload)
                .pathParam("username",username)
                .when()
                .put(Routes.updateURL);
        return response;
    }

    public static Response deleteUser(String username) {
        Response response = given()
                .pathParam("username",username)
                .when()
                .delete(Routes.deleteURL);
        return response;
    }
}