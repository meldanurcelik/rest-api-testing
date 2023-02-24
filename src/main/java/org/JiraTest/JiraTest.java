package org.JiraTest;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;

public class JiraTest {

    public static void main(String[] args) {

        RestAssured.baseURI = "http://localhost:8080";

        given().pathParam("id", "10001")
                .header("Content-type", "application/json")
                .log().all()
                .body("{\n" +
                        "    \"body\": \"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eget venenatis elit. Duis eu justo eget augue iaculis fermentum. Sed semper quam laoreet nisi egestas at posuere augue semper\",\n" +
                        "    \"visibility\": {\n" +
                        "        \"type\": \"role\",\n" +
                        "        \"value\": \"Administrators\"\n" +
                        "    }\n" +
                        "}")
                .post("/rest/api/2/issue/{id}/comment");

    }

}