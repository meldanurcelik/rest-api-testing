package org.restassuredtesting;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class Main {
    public static void main(String[] args) {
        //System.out.println("Hello world!");

        //given - all iput details
        //when - submit the api - resource, http method
        //then - validate the response

        RestAssured.baseURI = "https://rahulshettyacademy.com";

        given().queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(Payload.AddPlace())
                .when().post("/maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200)
                .body("scope", equalTo("APP"));

        //Add place -> Update place with new address -> Get place to validate if new address is present in response



    }
}