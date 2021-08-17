
package basicTest;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class CRUDProjectTest {
    @Test
    public void testCreateUserApi(){
        String email = "G77@gmail.com";
        JSONObject body= new JSONObject();
        body.put("Email", email);
        body.put("Password","12345");
        body.put("FullName","Grupo3");
        //Create
        Response response=given()
                .body(body.toString())
                .log()
                .all()
                .when()
                .post("https://todo.ly/api/user.json");
        response.then()
                .statusCode(200)
                .body("Email", equalTo(email))
                .body("FullName",equalTo("Grupo3"))
                .log()
                .all();
        //update
        String id = response.then().extract().path("Id")+"";
        body.put("FullName","UCB Grupo3");

        response=given()
                .auth()
                .preemptive()
                .basic(email,"12345")
                .body(body.toString())
                .log()
                .all()
                .when()
                .put("https://todo.ly/api/user/ID.json".replace("ID", id));

        response.then()
                .statusCode(200)
                .body("FullName",equalTo("UCB Grupo3"))
                .log()
                .all();
        // Get/*
        response=given()
                .auth()
                .preemptive()
                .basic(email,"12345")
                .log()
                .all()
                .when()
                .get("https://todo.ly/api/user.json");

        response.then()
                .statusCode(200)
                .body("FullName",equalTo("UCB Grupo3"))
                .log()
                .all();
        // Delete
        /*response=given()
                .auth()
                .preemptive()
                .basic(email,"12345")
                .log()
                .all()
                .when()
                .delete("https://todo.ly/api/user/"+id+".json");

        response.then()
                .statusCode(200)
                .body("FullName",equalTo("UCB Grupo3"))
                .body("Deleted", equalTo(true))
                .log()
                .all();*/
    }

}

