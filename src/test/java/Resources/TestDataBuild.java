package Resources;

import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {
    public AddPlace addPlacePayload(){

        AddPlace p = new AddPlace();

        Location l = new Location();
        l.setLat(-38.383467);
        l.setLng(33.427378);
        p.setLocation(l);

        p.setAccuracy(50);
        p.setAddress("29, side layout, cohen 09...");
        p.setLanguage("French-IN");
        p.setPhone_number("(+91) 983 893 3900");
        p.setWebsite("https://rahulshettyacademy.com");
        p.setName("Frontline house");
        List<String> myList = new ArrayList<String>();
        myList.add("shoe park...");
        myList.add("shop...");

        p.setTypes(myList);

        return p;
    }
//This methods gets parameters from Scenario outline Examples table and update the payload POJO file for each row
    public AddPlace addPlacePayload2(String name, String language, String address){

        AddPlace p = new AddPlace();

        Location l = new Location();
        l.setLat(-38.383467);
        l.setLng(33.427378);
        p.setLocation(l);

        p.setAccuracy(50);
        p.setAddress(address);
        p.setLanguage(language);
        p.setPhone_number("(+91) 983 893 3900");
        p.setWebsite("https://rahulshettyacademy.com");
        p.setName(name);
        List<String> myList = new ArrayList<String>();
        myList.add("shoe park...");
        myList.add("shop...");

        p.setTypes(myList);

        return p;
    }

    public String deletePlacePayload(String placeID){

        return "{\"place_id\": \""+placeID +"\"}";
    }

}
