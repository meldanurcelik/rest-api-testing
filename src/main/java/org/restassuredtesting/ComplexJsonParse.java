package org.restassuredtesting;

import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

    public static void main(String[] args) {

        JsonPath js = new JsonPath(Payload.CoursePrice());

        //Print No of courses returned by API
        int count = js.getInt("courses.size()");
        System.out.println("courses.size() = " + count);

        //Print Purchase Amount
        int totalAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println("dashboard.purchaseAmount = " + totalAmount);

        //Print Title of the first course
        String titleFirstCourse = js.get("courses[2].title");
        System.out.println("courses[2].title = " + titleFirstCourse);

    }

}