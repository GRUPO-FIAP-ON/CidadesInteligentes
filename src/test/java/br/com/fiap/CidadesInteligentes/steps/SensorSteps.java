package br.com.fiap.CidadesInteligentes.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import br.com.fiap.CidadesInteligentes.model.Sensor;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SensorSteps {

    @Autowired
    private TestRestTemplate restTemplate;

    private HttpEntity<Sensor> request;
    private ResponseEntity<Sensor> response;
    private Long sensorId;

    @Given("que eu tenho os dados do sensor")
    public void queEuTenhoOsDadosDoSensor() {
        Sensor sensor = new Sensor(null, "Sensor de temperatura", "Temperatura", "Ativo");
        request = new HttpEntity<>(sensor);
    }

    @When("eu envio uma requisição para criar o sensor")
    public void euEnvioUmaRequisicaoParaCriarOSensor() {
        response = restTemplate.postForEntity("/sensors", request, Sensor.class);
    }

    @Then("o sensor deve ser criado com sucesso")
    public void oSensorDeveSerCriadoComSucesso() {
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        sensorId = response.getBody().getId();
        assertNotNull(sensorId, "O ID do sensor criado não deve ser nulo.");
    }

    @Then("a resposta deve conter os detalhes do sensor criado")
    public void aRespostaDeveConterOsDetalhesDoSensorCriado() {
        Sensor sensor = response.getBody();
        assertEquals("Sensor de temperatura", sensor.getNome());
        assertEquals("Temperatura", sensor.getTipo());
        assertEquals("Ativo", sensor.getStatus());
    }

    @Given("que existe um sensor com ID {int}")
    public void queExisteUmSensorComID(int id) {
        Sensor sensor = new Sensor(null, "Sensor de temperatura", "Temperatura", "Ativo");
        ResponseEntity<Sensor> response = restTemplate.postForEntity("/sensors", new HttpEntity<>(sensor), Sensor.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody(), "A resposta não deve ser nula.");
        this.sensorId = response.getBody().getId();
    }

    @When("eu envio uma requisição para consultar o sensor com ID {int}")
    public void euEnvioUmaRequisicaoParaConsultarOSensorComID(int id) {
        response = restTemplate.getForEntity("/sensors/" + id, Sensor.class);
    }

    @Then("a resposta deve conter os detalhes do sensor com ID {int}")
    public void aRespostaDeveConterOsDetalhesDoSensorComID(int id) {
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Sensor sensor = response.getBody();
        assertNotNull(sensor);
        assertEquals(id, sensor.getId());
        assertEquals("Sensor de temperatura", sensor.getNome());
        assertEquals("Temperatura", sensor.getTipo());
        assertEquals("Ativo", sensor.getStatus());
    }

    @When("eu envio uma requisição para deletar o sensor com ID {int}")
    public void euEnvioUmaRequisicaoParaDeletarOSensorComID(int id) {
        response = restTemplate.exchange("/sensors/" + id, HttpMethod.DELETE, null, Sensor.class);
    }

    @Then("a resposta deve ter status {int}")
    public void aRespostaDeveTerStatus(int expectedStatus) {
        assertEquals(expectedStatus, response.getStatusCodeValue());
    }
}
