package br.com.fiap.CidadesInteligentes.service;

import br.com.fiap.CidadesInteligentes.model.Sensor;
import java.util.List;
import java.util.Optional;

public interface SensorService {
    List<Sensor> getAllSensors();
    Optional<Sensor> getSensorById(Long id);
    Sensor createSensor(Sensor sensor);
    Sensor updateSensor(Long id, Sensor sensorDetails);
    void deleteSensor(Long id);
}
