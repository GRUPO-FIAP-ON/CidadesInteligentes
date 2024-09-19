package br.com.fiap.CidadesInteligentes.service;

import br.com.fiap.CidadesInteligentes.model.StreetLight;
import java.util.List;
import java.util.Optional;

public interface StreetLightService {
    List<StreetLight> getAllStreetLights();
    Optional<StreetLight> getStreetLightById(Long id);
    StreetLight createStreetLight(StreetLight streetLight);
    StreetLight updateStreetLight(Long id, StreetLight streetLight);
    void deleteStreetLight(Long id);
}
