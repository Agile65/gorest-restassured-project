package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PostsExtractionTest extends TestBase {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";//
        RestAssured.basePath = "/public/v2/posts?page=1&per_page=25";
        response = given()
                .when()
                .get("")
                .then().statusCode(200);
        //response.log().all();// to print all data on the console

    }

    //1. Extract the title
    @Test
    public void test001() {
        List<String> title = response.extract().path("title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The title is : " + title);
        System.out.println("------------------End of Test---------------------------");
    }

    //2. Extract the total number of record
    @Test
    public void test002() {
        int total = response.extract().path("size");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The total number of record is : " + total);
        System.out.println("------------------End of Test---------------------------");
    }

    //3. Extract the body of 5th record
    @Test
    public void test003() {
        String body = response.extract().path("[4].body");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The body of 5th record : " + body);
        System.out.println("------------------End of Test---------------------------");
    }

    //4. Extract the user_id of all the records
    @Test
    public void test004() {
        List<Integer> userId = response.extract().path("user_id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The user_id of all the records : " + userId);
        System.out.println("------------------End of Test---------------------------");
    }

    //5. Extract the title of all the records
    @Test
    public void test005() {
        List<String> title = response.extract().path("title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The title of all the records : " + title);
        System.out.println("------------------End of Test---------------------------");
    }
    //6. Extract the title of all records whose user_id = 2250463
    @Test
    public void test006() {
        List<String> title = response.extract().path("findAll{it.user_id==2250463}.title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The title of all the records whose user_id = 2250463 : " + title);
        System.out.println("------------------End of Test---------------------------");
    }

    //7. Extract the body of all records whose id = 38970
    @Test
    public void test007() {
        List<String> body = response.extract().path("findAll{it.id==38970}.body");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The title of all the records whose user_id = 38970 : " + body);
        System.out.println("------------------End of Test---------------------------");
    }
}
