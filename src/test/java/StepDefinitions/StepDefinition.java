package StepDefinitions;

import Resources.APIResources;
import Resources.TestDataBuild;
import Resources.Utils;
import com.oracle.webservices.internal.api.message.ContentType;

import javax.xml.ws.Response;
import java.io.IOException;



public class StepDefinition extends Utils {
    RequestSpecification res;
    ResponseSpecification resspec;
    Response response;
    TestDataBuild data = new TestDataBuild();
    static String placeID;

    @Given("Add Place Payload")
    public void add_place_payload() throws IOException {

        resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        res = given().spec(requestSpecification())
                .body(data.addPlacePayload());
    }

    @When("User calls {string} with Post http request")
    public void user_calls_with_post_http_request(String string) {
        response = res.when().post("/maps/api/place/add/json")
                .then().spec(resspec).extract().response();
    }

    //This is used by ENUM version
    @When("User calls {string} with Post http request with ENUM")
    public void userCallsWithPostHttpRequestWithENUM(String resource) {
        APIResources resourceAPI = APIResources.valueOf(resource);
        response = res.when().post(resourceAPI.getResource())
                .then().spec(resspec).extract().response();
    }

    //This is third version of this method. this one gets the http method as parameter
    @When("User calls {string} with {string} http request with ENUM")
    public void userCallsWithHttpRequestWithENUM(String resource, String method) {

        APIResources resourceAPI = APIResources.valueOf(resource);

        if (method.equalsIgnoreCase("POST"))
            response = res.when().post(resourceAPI.getResource());
        else if (method.equalsIgnoreCase("GET"))
            response = res.when().get(resourceAPI.getResource());
    }

    @Then("The API call got success with status code {int}")
    public void the_api_call_got_success_with_status_code(Integer int1) {
        assertEquals(response.getStatusCode(), 200);
    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String expectedValue) {

        // First we used this technic to get response, but then by using utility class we make it generic
        // that we can use it with any response
        //        String resp = response.asString();
        //        js = new JsonPath(resp);

        assertEquals(getJsonPath(response, keyValue), expectedValue);

    }

    @Given("Add Place Payload with {string} {string} {string}")
    public void addPlacePayloadWith(String name, String language, String address) throws IOException {
        resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        res = given().spec(requestSpecification2())
                .body(data.addPlacePayload2(name, language, address));
    }


    @And("Verify place_ID created maps to {string} using {string}")
    public void verifyPlace_IDCreatedMapsToUsing(String expectedName, String resource) throws IOException {
        placeID = getJsonPath(response, "place_id");
        res = given().spec(requestSpecification()).queryParam("place_id", placeID);
        userCallsWithHttpRequestWithENUM(resource, "GET");
        String actualName = getJsonPath(response, "name");
        assertEquals(actualName, expectedName);

    }

    @Given("DeletePlace Payload")
    public void deleteplacePayload() throws IOException {
       res= given().spec(requestSpecification()).body(data.deletePlacePayload(placeID));
    }
}
