package petstore;
import org.testng.annotations.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Pet {
    //Atributos
    String  uri = "https://petstore.swagger.io/v2/pet"; // endereço da entidade Pet

    //Função ou Método
    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    // Incluir - Create - POST
    @Test (priority = 1) // Identifica o método ou função como um teste
    public void incluirPet() throws IOException {
        String jsonBody = lerJson("src/test/resources/dadosJson/pet1.json");


        given() // Pré condições
                .contentType("application/json") // comum em API REST
                .log().all()
                .body(jsonBody)
        .when()
                .post(uri)

        .then()
                .log().all()
                .statusCode(200)
                .body("name", is("Snoopy"))
                .body("status", is("available"))
                .body("category.name", containsString("Dog"))
                .body("tags.name",contains("sta"))
        ;
    }
    @Test (priority = 2)
    public void consultaPet(){
        String petId = "194754852";
        given()
                .contentType("appication/json")
                .log().all()

        .when()
                .get(uri + "/" + petId)
        .then()
                .log().all()
                .statusCode(200)
                .body("status",is("available"))
                .body("name",containsString("Snoopy"))
                .body("category.name",containsString("Dog"))
        ;
    }


}
