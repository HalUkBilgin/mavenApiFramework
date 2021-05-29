package StepDefinitions;


import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {

    @Before("@DeletePlace")
    public void beforeScenerio() throws IOException {
// Execute this code only when PlaceID is null
// Write a code that will give you placeID

        StepDefinition m = new StepDefinition();
        if (StepDefinition.placeID == null) {
            m.addPlacePayloadWith("Fatih", "Turkish", "Suwanee");
            m.userCallsWithHttpRequestWithENUM("addPlaceAPI", "POST");
            m.verifyPlace_IDCreatedMapsToUsing("Fatih", "getPlaceAPI");

        }
    }
}
