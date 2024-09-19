package br.com.fiap.CidadesInteligentes.repository;

import br.com.fiap.CidadesInteligentes.model.StreetLight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StreetLightRepository extends JpaRepository<StreetLight, Long> {
}
