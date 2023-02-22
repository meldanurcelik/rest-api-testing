package org.restassuredtesting;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ReUsableMethods {

    public static JsonPath rawToJson(Response response) {
        JsonPath jp = new JsonPath(response.asString());
        return jp;
    }

}