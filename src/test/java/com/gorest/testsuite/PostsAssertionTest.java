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

public class PostsAssertionTest extends TestBase {

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

    //1. Verify the if the total record is 25
    @Test
    public void test001() {
        response.body("size", equalTo(10));
    }

    //2. Verify the if the title of id = 38975 is equal to ”Comburo bis approbo circumvenio despirmatio sed pecco conqueror.”
    @Test
    public void test002() {
        response
                .body("[1]", hasEntry("title", "Comburo bis approbo circumvenio despirmatio sed pecco conqueror."))
                .body("[1]", hasEntry("id", 38975));
    }

    //3. Check the single user_id in the Array list (2250466)
    @Test
    public void test003() {
        response.body("user_id", hasItem(2250466));
    }

    //4. Check the multiple ids in the ArrayList (2250466,2250463,2250460)
    @Test
    public void test004() {
        response.body("user_id", hasItems(2250466,2250463,2250460));
    }
    //5. Verify the body of userid = 2678 is equal “Carus eaque voluptatem. Calcar spectaculum coniuratio. Abstergo consequatur deleo. Amiculum advenio dolorem.
    //Sollers conservo adiuvo. Concedo campana capitulus. Adfectus tibi truculenter. Canto temptatio adimpleo. Ter degenero animus. Adeo optio crapula. Abduco et
    //antiquus. Chirographum baiulus spoliatio. Suscipit fuga deleo. Comburo aequus cuppedia. Crur cuppedia voluptates. Argentum adduco vindico. Denique undique
    //adflicto. Assentator umquam pel.
    @Test
    public void test005() {
        response
                .body("[2].user_id", equalTo(2272670))
                .body("[2].body",equalTo("Utique vapulus spectaculum. Textor ut voveo. Sophismata est callide. Cito debilito uter. Turba antiquus volva. Cado expedita vitae. Autus contra avarus. Antea tantillus curatio. Sordeo solitudo cribro. Adhaero theatrum turpe."));
       // response.body("find{it.user_id = 2223264}", hasItem(IsMapContaining.hasEntry("body", "Tremo vilitas ascisco. Bis demum acidus. Pecus bardus atrocitas. Non varietas earum. Civitas defetiscor templum. Solitudo quia voluptatem. Quisquam tenus eaque. Barba quo colligo. Inflammatio statua clarus. Ea sollicito ustulo. Cogo ascit ustulo.")));
    }

}
