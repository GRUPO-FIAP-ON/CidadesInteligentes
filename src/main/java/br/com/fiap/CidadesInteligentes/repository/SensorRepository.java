package br.com.fiap.CidadesInteligentes.repository;

import br.com.fiap.CidadesInteligentes.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorRepository extends JpaRepository<Sensor, Long> {
}