package org.JiraTest;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;

import static io.restassured.RestAssured.*;

public class JiraTest {

    public static void main(String[] args) {

        RestAssured.baseURI = "http://localhost:8080";

        SessionFilter sessionFilter = new SessionFilter();

        String response = given()
                .header("Content-type", "application/json")
                .body("{\n" +
                        "    \"username\": \"meldanur.celik\",\n" +
                        "    \"password\": \"Gini123\"\n" +
                        "}")
                .log().all()
                .filter(sessionFilter).when()
                .post("/rest/auth/1/session")
                .then().log().all().extract().response().asString();

        given().pathParam("key", "10104")
                .log().all()
                .header("Content-type", "application/json")
                .body("{\n" +
                        "    \"body\": \"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eget venenatis elit. Duis eu justo eget augue iaculis fermentum. Sed semper quam laoreet nisi egestas at posuere augue semper\",\n" +
                        "    \"visibility\": {\n" +
                        "        \"type\": \"role\",\n" +
                        "        \"value\": \"Administrators\"\n" +
                        "    }\n" +
                        "}")
                .filter(sessionFilter).when()
                .post("/rest/api/2/issue/{key}/comment").then().assertThat().statusCode(201);

    }

}