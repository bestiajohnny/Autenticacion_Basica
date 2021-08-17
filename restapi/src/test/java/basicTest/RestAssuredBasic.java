
package basicTest;




import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class RestAssuredBasic {

    /*
      given()----> configuracion
      when() ----> request action --> URL ---> Method
      then() ----> verificaciones
     */

    @Test
    public void createProjectExternalFile(){
        given()
                .auth()
                .preemptive()
                .basic("Grupo3@gmail.com","12345")
                .body(new File("E:\\Diplomado de testing de software\\material\\restapi\\src\\test\\resources\\projectBody.json"))
                .log()
                .all()
                .when()
                .post("http://todo.ly/api/projects.json")
                .then()
                .log()
                .all();
    }

}
