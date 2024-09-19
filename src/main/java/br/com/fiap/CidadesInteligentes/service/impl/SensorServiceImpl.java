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
        // Adicione regras de negócio para a criação de um sensor, se necessário
        return sensorRepository.save(sensor);
    }

    @Override
    public Sensor updateSensor(Long id, Sensor sensorDetails) {
        // Adicione regras de negócio para a atualização de um sensor, se necessário
        Sensor sensor = sensorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sensor não encontrado"));
        sensor.setNome(sensorDetails.getNome());
        sensor.setTipo(sensorDetails.getTipo());
        sensor.setStatus(sensorDetails.getStatus());
        return sensorRepository.save(sensor);
    }

    @Override
    public void deleteSensor(Long id) {
        // Adicione regras de negócio para a exclusão de um sensor, se necessário
        Sensor sensor = sensorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sensor não encontrado"));
        sensorRepository.delete(sensor);
    }
}
