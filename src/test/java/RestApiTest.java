import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

public class RestApiTest {

    @Test
    public void testGetRequestWithCheckContentType() {
        Response response = RestAssured
                .when()
                .get("https://catalog.onliner.by/mouse/genesiszone/nmg1572");
        Assert.assertEquals(response.header("content-type"), "text/html; charset=UTF-8");
    }

    @Test
    public void testGetRequestAndCheckStatusCode() {
        RestAssured
                .when()
                .get("https://catalog.onliner.by/mouse/genesiszone/nmg1572")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void testGetRequestOnNonExistentPage() {
        RestAssured
                .when()
                .get("https://catalog.onliner.by/some_text")
                .then()
                .assertThat()
                .statusCode(404);
    }

    @Test
    public void testBadPostRequest() {
        String someRandomString = RandomStringUtils.random(20, true, true);

        JSONObject requestBody = new JSONObject();
        requestBody.put("FirstName", someRandomString);
        requestBody.put("LastName", someRandomString);
        requestBody.put("UserName", someRandomString);
        requestBody.put("Password", someRandomString);
        requestBody.put("Email", someRandomString + "@gmail.com");

        RequestSpecification request = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .body(requestBody.toString());

        Response response = request.post("http://catalog.onliner.by");
        int statusCode = response.getStatusCode();
        Assert.assertNotSame(statusCode, 200);
    }
}
