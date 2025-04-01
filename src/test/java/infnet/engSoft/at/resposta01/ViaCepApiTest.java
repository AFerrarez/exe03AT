package infnet.engSoft.at.resposta01;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class ViaCepApiTest {

    @Test
    public void testCepComLetras() {
        given().when().get("https://viacep.com.br/ws/ABCDE/json/").then().statusCode(400);

    }

    @Test
    public void testCepVazio() {
        given()
                .when()
                .get("https://viacep.com.br/ws//json/")
                .then()
                .statusCode(400);
    }

    @Test
    public void testCepComTamanhoIncorreto() {
        // Teste com menos de 8 dígitos
        given()
                .when()
                .get("https://viacep.com.br/ws/123456/json/")
                .then()
                .statusCode(400);

        // Teste com mais de 8 dígitos
        given()
                .when()
                .get("https://viacep.com.br/ws/123456789/json/")
                .then()
                .statusCode(400);
    }


}

