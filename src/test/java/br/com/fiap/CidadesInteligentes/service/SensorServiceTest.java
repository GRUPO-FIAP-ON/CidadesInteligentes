package br.com.fiap.CidadesInteligentes.service;

import br.com.fiap.CidadesInteligentes.model.Sensor;
import br.com.fiap.CidadesInteligentes.repository.SensorRepository;
import br.com.fiap.CidadesInteligentes.service.impl.SensorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SensorServiceTest {

    @InjectMocks
    private SensorServiceImpl sensorService;

    @Mock
    private SensorRepository sensorRepository;

    private Sensor sensor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sensor = new Sensor();
        sensor.setId(1L);
        sensor.setNome("Sensor de Temperatura");
        sensor.setTipo("Temperatura");
        sensor.setStatus("Ativo");
    }

    @Test
    void testCriarSensorComDadosValidos() {
        when(sensorRepository.save(any(Sensor.class))).thenReturn(sensor);

        Sensor createdSensor = sensorService.createSensor(sensor);

        assertNotNull(createdSensor);
        assertEquals("Sensor de Temperatura", createdSensor.getNome());
        verify(sensorRepository, times(1)).save(sensor);
    }

    @Test
    void testDeletarSensorInexistente() {
        when(sensorRepository.existsById(sensor.getId())).thenReturn(false); // Mock to return false

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            sensorService.deleteSensor(sensor.getId());
        });

        assertEquals("Sensor com ID " + sensor.getId() + " não encontrado.", exception.getMessage());
    }

    @Test
    void testCriarSensorComNomeVazio() {
        sensor.setNome(""); // Nome vazio

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            sensorService.createSensor(sensor);
        });

        assertEquals("Nome não pode ser vazio", exception.getMessage());
    }

    @Test
    void testAtualizarSensorInexistente() {
        when(sensorRepository.existsById(sensor.getId())).thenReturn(false);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            sensorService.updateSensor(sensor.getId(), sensor);
        });

        assertEquals("Sensor com ID " + sensor.getId() + " não encontrado.", exception.getMessage());
    }
}