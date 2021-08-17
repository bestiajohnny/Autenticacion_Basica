package basicTest;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class RestAssuredExtract {

    @Test
    public void restAssuredVerification(){
        JSONObject body= new JSONObject();
        body.put("Content","Grupo3Check");
        body.put("Icon",1);

        given()
                .auth()
                .preemptive()
                .basic("Grupo3@gmail.com","12345")
                .body(body.toString())
                .log()
                .all()
                .when()
                .post("http://todo.ly/api/projects.json")
                .then()
                .statusCode(200)
                .body("Content", equalTo("Grupo3Check"))
                .body("Icon",equalTo(1))
                .log()
                .all();
    }
}
