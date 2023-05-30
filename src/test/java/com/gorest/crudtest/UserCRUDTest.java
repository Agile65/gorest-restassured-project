package com.gorest.crudtest;

import com.gorest.model.UserPojo;
import com.gorest.utils.TestUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class UserCRUDTest extends TestUtils {

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        RestAssured.basePath="/users";
    }

    static String firstName = "Max";
    static String email = "Max122"+getRandomValue()+"asr@gmail.com";
    static String status = "active";
    static String gender = "male";
    static String bearerToken="a48953281d1ec1f0cdc9448f2008b38b3b537c2b03ccddce12106f2e3c9b7d9a";


    @Test
    public void verifyUserCreatedSuccessfully() {

        UserPojo userPojo = new UserPojo();
        userPojo.setFirstName(firstName);
        userPojo.setStatus(status);
        userPojo.setEmail(email);
        userPojo.setGender(gender);

        Response response = given().log().all()
                .header("Authorization","Bearer "+bearerToken)
                .contentType(ContentType.JSON)
                //.header("Content-Type", "application/json")
                .when()
                .body(userPojo)
                .post();//post require 2 things
        //response.then().log().all().statusCode(201);
        response.then().statusCode(201);
        response.prettyPrint();
    }

    @Test
    public void getUser(){
        Response response = given()
                .when()
                .get();
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void verifyUserUpdateSuccessfully(){
        UserPojo userPojo = new UserPojo();
        userPojo.setStatus(status);
        userPojo.setGender("female");

        Response response = given()
                .header("Authorization","Bearer "+bearerToken)
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                //.header("Content-Type", "application/json")
                .when()
                .body(userPojo)
                .put("/2272632");//post require 2 things
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void VerifyUserDeleteSuccessfully() {
        Response response = given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer "+bearerToken)
                .when()
                .delete("/2272637");
        response.then().statusCode(201);
        response.prettyPrint();
    }
}
