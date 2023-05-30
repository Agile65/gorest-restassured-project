package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;

public class UserAssertionTest extends TestBase {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";
        RestAssured.basePath = "/public/v2/users";
        response = given()
                .when()
                .get("")
                .then().statusCode(200);
        // response.log().all();// to print all data on the console

    }

    //1. Verify the if the total record is 10
    @Test
    public void test001() {
        response.body("size", equalTo(10));
    }

    //2. Verify the if the name of id = 2223202 is equal to ”Lalita Jha”
    @Test
    public void test002() {
        response
                .body("[2]", hasEntry("id", 2223202))
                .body("[2]", hasEntry("name", "Lalita Jha"));

    }

    //3. Check the single ‘Name’ in the Array list (Shashikala Butt)
    @Test
    public void test003() {
        response.body("name", hasItem("Shashikala Butt"));

    }

    //4. Check the multiple ‘Names’ in the ArrayList (Shashikala Butt, Bakula Iyengar, Ms. Deeptimoy Varrier)
    @Test
    public void test004() {
        response.body("name", hasItems("Shashikala Butt", "Bakula Iyengar", "Ms. Deeptimoy Varrier"));
    }

    //5. Verify the emai of userid = 2250472 is equal “laxman_desai@rice-haley.example”
    @Test
    public void test005() {
        response
                .body("[4]", hasEntry("id", 2250472))
                .body("[4]", hasEntry("email", "laxman_desai@rice-haley.example"));
    }

    //6. Verify the status is “Active” of user name is “Shanti Bhat V”
    @Test
    public void test006() {
        response
                .body("[0]", hasEntry("name", "Shanti Bhat V"))
                .body("[0]", hasEntry("status", "active"));
    }

    //7. Verify the Gender = male of user name is “Ms. Deeptimoy Varrier”
    @Test
    public void test007() {
        response
                .body("[0]", hasEntry("name", "Ms. Deeptimoy Varrier"))
                .body("[0]", hasEntry("gender", "male"));
    }
}
