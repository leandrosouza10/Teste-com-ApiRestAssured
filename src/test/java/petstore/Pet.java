package petstore;
import org.testng.annotations.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.*;

public class Pet {
    //Atributos
    String  uri = "https://petstore.swagger.io/v2/pet"; // endereço da entidade Pet

    //Função ou Método
    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    // Incluir - Create - POST
    @Test // Identifica o método ou função como um teste
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
        ;
    }
}