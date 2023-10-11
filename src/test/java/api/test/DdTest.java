package api.test;

import api.endpoints.UserEndpoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DdTest {

    @Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void testUser(String id, String username, String firstName, String lastName, String email, String password, String phone,
                         String userStatus) {
        User user = new User();
        user.setId(Integer.parseInt(id));
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);
        user.setUserStatus(Integer.parseInt(userStatus));

        Response response = UserEndpoints.createUser(user);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
    }

@Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
public void testDeleteUserByName(String username){
     Response response =   UserEndpoints.deleteUser(username);
     Assert.assertEquals(response.getStatusCode(),200);

}
}