package api.endpoints;

import api.payload.User;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class UserEndpoints2 {


    //method created for getting URL's from properties file
    static ResourceBundle getURL() {
        ResourceBundle routes = ResourceBundle.getBundle("routes"); //load properties file
        return routes;
    }

    public static Response createUser(User payload) {

        String postURL = getURL().getString("postURL");
        Response response = given()
                .contentType("application/json")
                .body(payload)
                .when()
                .post(postURL);

        return response;
    }

    public static Response ReadUser(String username) {
        String getURL = getURL().getString("getURL");
        Response response = given()
                .pathParam("username", username)
                .when()
                .get(getURL);
        return response;
    }

    public static Response UpdateUser(String username, User payload) {
        String updateURL = getURL().getString("updateURL");
        Response response = given()
                .contentType("application/json")
                .body(payload)
                .pathParam("username", username)
                .when()
                .put(updateURL);
        return response;
    }

    public static Response deleteUser(String username) {
        String deleteURL = getURL().getString("deleteURL");
        Response response = given()
                .pathParam("username", username)
                .when()
                .delete(deleteURL);
        return response;
    }
}