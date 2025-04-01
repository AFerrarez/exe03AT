package infnet.engSoft.at;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnderecoTest {

    private RestTemplate restTemplate;
    private String baseUrl = "https://viacep.com.br/ws";

    @BeforeEach
    public void setUp() {
        restTemplate = new RestTemplate();
    }

    @Test
    public void testConsultaEndereco() {
        // Defina o CEP para a consulta
        String cep = "01311-921";  // CEP fornecido
        String url = String.format("%s/%s/json", baseUrl, cep);

        // Realiza a requisição GET
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        // Imprimir a resposta real no console
        System.out.println("Resposta real: " + response.getBody());

        // Verifica se o código de status HTTP retornado é 200 (OK)
        assertEquals(200, response.getStatusCodeValue(), "O status da resposta não foi 200");

        // Defina a resposta esperada com base na saída real
        String expectedResponse = "{\n  \"cep\": \"01311-921\",\n  \"logradouro\": \"Avenida Paulista\",\n  \"complemento\": \"1159\",\n  \"unidade\": \"Edifício Barão de Serro Azul\",\n  \"bairro\": \"Bela Vista\",\n  \"localidade\": \"São Paulo\",\n  \"uf\": \"SP\",\n  \"estado\": \"São Paulo\",\n  \"regiao\": \"Sudeste\",\n  \"ibge\": \"3550308\",\n  \"gia\": \"1004\",\n  \"ddd\": \"11\",\n  \"siafi\": \"7107\"\n}";

        // Comparar a resposta recebida com a esperada
        assertEquals(expectedResponse, response.getBody(), "O corpo da resposta não é o esperado");
    }
}
