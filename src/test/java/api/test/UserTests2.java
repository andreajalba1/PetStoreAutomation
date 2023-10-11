package api.test;

import api.endpoints.UserEndpoints;
import api.endpoints.UserEndpoints2;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTests2 {
    Faker faker;
    User user;
    String username;
    public Logger logger;

    @BeforeClass
    public void setUpData() {
        faker = new Faker();
        user = new User();

        int id = faker.idNumber().hashCode();
        user.setId(id);

        String userName = faker.name().username();
        user.setUsername(userName);

        String firstName = faker.name().firstName();
        user.setFirstName(firstName);

        String lastName = faker.name().lastName();
        user.setLastName(lastName);

        String email = faker.internet().emailAddress();
        user.setEmail(email);

        String password = faker.internet().password();
        user.setPassword(password);

        String phone = faker.phoneNumber().cellPhone();
        user.setPhone(phone);

        user.setUserStatus(0);

        //logs
        logger = LogManager.getLogger(this.getClass());
    }

    @Test(priority = 1)
    public void testPostUser() {
        logger.info("Creating user");
        Response response = UserEndpoints2.createUser(user);
        response.then().log().all();

        System.out.println(user.getUsername());

        Assert.assertEquals(response.getStatusCode(), 200);

        logger.info("User is created");
    }

    @Test(priority = 2)
    public void testGetUser() {
        logger.info("Reading user");
        Response response = UserEndpoints2.ReadUser(this.user.getUsername());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("User is displayed");
    }

    @Test(priority = 3)
    public void testupdateUser() {
        logger.info("Updating User");
        user.setLastName(faker.name().lastName());
        user.setFirstName(faker.name().firstName());
        user.setPhone(faker.phoneNumber().cellPhone());

        Response response = UserEndpoints2.UpdateUser(this.user.getUsername(), user);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("User Updated");

        Response responseAfterUpdating = UserEndpoints2.ReadUser(this.user.getUsername());
        responseAfterUpdating.then().log().all();
        Assert.assertEquals(responseAfterUpdating.getStatusCode(), 200);
    }

    @Test(priority = 4)
    public void testDeleteUser() {
        logger.info("Deleting user");
        Response response = UserEndpoints2.deleteUser(this.user.getUsername());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("User is deleted");
    }
}