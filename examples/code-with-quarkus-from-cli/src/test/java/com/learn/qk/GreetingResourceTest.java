package com.learn.qk;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class GreetingResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/hello")
          .then()
             .statusCode(200)
             .body(is("Hello from RESTEasy Reactive"));
    }
    
    @Test
    public void testHelloWithNameEndpoint() {
        given()
          .when().get("/hello/john")
          .then()
             .statusCode(200)
             .body(is("Hello john from RESTEasy Reactive"));
    }

}