package br.com.fiap.CidadesInteligentes.service.impl;

import br.com.fiap.CidadesInteligentes.model.Sensor;
import br.com.fiap.CidadesInteligentes.repository.SensorRepository;
import br.com.fiap.CidadesInteligentes.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SensorServiceImpl implements SensorService {

    @Autowired
    private SensorRepository sensorRepository;

    @Override
    public List<Sensor> getAllSensors() {
        return sensorRepository.findAll();
    }

    @Override
    public Optional<Sensor> getSensorById(Long id) {
        return sensorRepository.findById(id);
    }

    @Override
    public Sensor createSensor(Sensor sensor) {
        if (sensor.getNome() == null || sensor.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }

        return sensorRepository.save(sensor);
    }



    @Override
    public Sensor updateSensor(Long id, Sensor sensorDetails) {
        Sensor sensor = sensorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Sensor com ID " + id + " não encontrado."));

        validateSensor(sensorDetails);

        sensor.setNome(sensorDetails.getNome());
        sensor.setTipo(sensorDetails.getTipo());
        sensor.setStatus(sensorDetails.getStatus());
        return sensorRepository.save(sensor);
    }

    @Override
    public void deleteSensor(Long id) {
        Sensor sensor = sensorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Sensor com ID " + id + " não encontrado."));

        sensorRepository.delete(sensor);
    }

    private void validateSensor(Sensor sensor) {
        if (sensor.getNome() == null || sensor.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }

        if (sensor.getTipo() == null || sensor.getTipo().trim().isEmpty()) {
            throw new IllegalArgumentException("O tipo de sensor é obrigatório.");
        }

        // Validação do status
        if (sensor.getStatus() == null ||
                (!sensor.getStatus().equalsIgnoreCase("Ativo") &&
                        !sensor.getStatus().equalsIgnoreCase("Inativo") &&
                        !sensor.getStatus().equalsIgnoreCase("Manutenção"))) {
            throw new IllegalArgumentException("Status inválido: " + sensor.getStatus() + ". Os status válidos são: Ativo, Inativo, Manutenção.");
        }
    }
}
