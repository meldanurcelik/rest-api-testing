package org.restassuredtesting;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class Main {
    public static void main(String[] args) {
        //System.out.println("Hello world!");

        //given - all iput details
        //when - submit the api - resource, http method
        //then - validate the response

        RestAssured.baseURI = "https://rahulshettyacademy.com";

        String response = given().log().all()
                .queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(Payload.AddPlace())
                .when().post("/maps/api/place/add/json")
                .then().assertThat().statusCode(200)
                .body("scope", equalTo("APP"))
                .extract().response().asString();

        //Add place -> Update place with new address -> Get place to validate if new address is present in response
        System.out.println(response);

        JsonPath jsonPath1 = new JsonPath(response); //for parsing Json
        String placeId = jsonPath1.getString("place_id");
        System.out.println("placeId = " + placeId);

        //Update place
        String newAddress = "Summer Walk, Africa";

        given().log().all().queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "\"place_id\":\"" + placeId + "\",\n" +
                        "\"address\":\"" + newAddress + "\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}\n")
                .when().put("/maps/api/place/update/json")
                .then().assertThat().log().all().statusCode(200)
                .body("msg", equalTo("Address successfully updated"));

        //Get place
        String getPlaceResponse = given().log().all()
                .queryParam("key", "qaclick123")
                .queryParam("place_id", placeId)
                .when().get("maps/api/place/get/json")
                .then().assertThat().log().all().statusCode(200)
                .extract().response().asString();

        JsonPath jsonPath2 = new JsonPath(getPlaceResponse);
        String actualAddress = jsonPath2.getString("address");
        System.out.println("actualAddress = " + actualAddress);

    }
}