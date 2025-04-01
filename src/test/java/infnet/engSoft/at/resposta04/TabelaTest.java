package infnet.engSoft.at.resposta04;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TabelaTest {

    private RestTemplate restTemplate;
    private String baseUrl = "https://viacep.com.br/ws";

    @BeforeEach
    public void setUp() {
        restTemplate = new RestTemplate();
    }

    @Test
    public void testConsultaEnderecoValido() {
        String cep = "01311-921";  // CEP válido
        String url = String.format("%s/%s/json", baseUrl, cep);

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        // Verifica se o status HTTP é 200 OK
        assertEquals(200, response.getStatusCodeValue(), "O status da resposta não foi 200");
        assertTrue(response.getBody().contains("Avenida Paulista"), "O logradouro não foi encontrado");
    }

    @Test
    public void testConsultaEnderecoCidadeComOUmAcentuacao() {
        // Com acentuação
        String urlAcentuacao = String.format("%s/SP/São Paulo/Avenida Paulista/json", baseUrl);
        ResponseEntity<String> responseAcentuacao = restTemplate.getForEntity(urlAcentuacao, String.class);
        assertEquals(200, responseAcentuacao.getStatusCodeValue(), "O status da resposta não foi 200");

        // Sem acentuação
        String urlSemAcentuacao = String.format("%s/SP/Sao Paulo/Avenida Paulista/json", baseUrl);
        ResponseEntity<String> responseSemAcentuacao = restTemplate.getForEntity(urlSemAcentuacao, String.class);
        assertEquals(200, responseSemAcentuacao.getStatusCodeValue(), "O status da resposta não foi 200");
    }

    @Test
    public void testConsultaEnderecoLimiteCep() {
        // Teste para o limite inferior
        String urlInferior = String.format("%s/00000-000/json", baseUrl);
        ResponseEntity<String> responseInferior = restTemplate.getForEntity(urlInferior, String.class);
        assertEquals(200, responseInferior.getStatusCodeValue(), "O status da resposta não foi 200");

        // Teste para o limite superior
        String urlSuperior = String.format("%s/99999-999/json", baseUrl);
        ResponseEntity<String> responseSuperior = restTemplate.getForEntity(urlSuperior, String.class);
        assertEquals(200, responseSuperior.getStatusCodeValue(), "O status da resposta não foi 200");
    }


}
