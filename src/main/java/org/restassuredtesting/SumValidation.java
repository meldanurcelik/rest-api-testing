package org.restassuredtesting;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SumValidation {

    public SumValidation() {
    }

    @Test
    public void sumOfCourses() {

        int sum = 0;
        JsonPath js = new JsonPath(Payload.CoursePrice());
        int count = js.getInt("courses.size()");

        int purchaseAmount;
        for (purchaseAmount = 0; purchaseAmount < count; ++purchaseAmount) {
            int price = js.getInt("courses[" + purchaseAmount + "].price");
            int copies = js.getInt("courses[" + purchaseAmount + "].copies");
            int amount = price * copies;
            System.out.println(amount);
            sum += amount;
        }

        System.out.println(sum);
        purchaseAmount = js.getInt("dashboard.purchaseAmount");
        Assert.assertEquals(sum, purchaseAmount);

    }

}