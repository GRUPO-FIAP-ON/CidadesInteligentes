package br.com.fiap.CidadesInteligentes.controller;

import br.com.fiap.CidadesInteligentes.model.Sensor;
import br.com.fiap.CidadesInteligentes.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    @Autowired
    private SensorService sensorService;

    @GetMapping
    public ResponseEntity<List<Sensor>> getAllSensors() {
        try {
            List<Sensor> sensors = sensorService.getAllSensors();
            return ResponseEntity.ok(sensors);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("mensagem", "Erro interno ao buscar sensores");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Sensor> createSensor(@RequestBody Sensor sensor) {
        try {
            Sensor createdSensor = sensorService.createSensor(sensor);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdSensor);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("mensagem", "Erro ao criar sensor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSensorById(@PathVariable Long id) {
        try {
            Optional<Sensor> sensor = sensorService.getSensorById(id);
            return sensor.map(ResponseEntity::ok)
                    .orElseGet(() -> {
                        Map<String, String> errorResponse = new HashMap<>();
                        errorResponse.put("mensagem", "Sensor não encontrado");
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body((Sensor) errorResponse);
                    });
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("mensagem", "Erro interno ao buscar sensor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSensor(@PathVariable Long id, @RequestBody Sensor sensorDetails) {
        try {
            Sensor updatedSensor = sensorService.updateSensor(id, sensorDetails);
            return ResponseEntity.ok(updatedSensor);
        } catch (RuntimeException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("mensagem", "Sensor não encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("mensagem", "Erro ao atualizar sensor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSensor(@PathVariable Long id) {
        try {
            sensorService.deleteSensor(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("mensagem", "Sensor não encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("mensagem", "Erro ao excluir sensor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
