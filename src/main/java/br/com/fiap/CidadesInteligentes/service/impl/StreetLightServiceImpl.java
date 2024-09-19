package br.com.fiap.CidadesInteligentes.service.impl;

import br.com.fiap.CidadesInteligentes.model.StreetLight;
import br.com.fiap.CidadesInteligentes.repository.StreetLightRepository;
import br.com.fiap.CidadesInteligentes.service.StreetLightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

@Service
public class StreetLightServiceImpl implements StreetLightService {

    @Autowired
    private StreetLightRepository streetLightRepository;

    @Override
    public List<StreetLight> getAllStreetLights() {
        try {
            return streetLightRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Falha ao recuperar luminárias", e);
        }
    }

    @Override
    public Optional<StreetLight> getStreetLightById(Long id) {
        return streetLightRepository.findById(id);
    }

    @Override
    public StreetLight createStreetLight(StreetLight streetLight) {
        try {
            validateStreetLight(streetLight);
            return streetLightRepository.save(streetLight);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao criar luminária", e);
        }
    }

    @Override
    public StreetLight updateStreetLight(Long id, StreetLight streetLight) {
        if (!streetLightRepository.existsById(id)) {
            throw new IllegalArgumentException("Luminária com id " + id + " não encontrada.");
        }
        try {
            validateStreetLight(streetLight);
            streetLight.setId(id);
            return streetLightRepository.save(streetLight);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao atualizar luminária", e);
        }
    }

    @Override
    public void deleteStreetLight(Long id) {
        if (streetLightRepository.existsById(id)) {
            try {
                streetLightRepository.deleteById(id);
            } catch (Exception e) {
                throw new RuntimeException("Falha ao excluir luminária", e);
            }
        } else {
            throw new IllegalArgumentException("Luminária com id " + id + " não encontrada.");
        }
    }

    private void validateStreetLight(StreetLight streetLight) {
        if (streetLight.getStatus() == null ||
                (!streetLight.getStatus().equalsIgnoreCase("Ligada") &&
                        !streetLight.getStatus().equalsIgnoreCase("Desligada") &&
                        !streetLight.getStatus().equalsIgnoreCase("Quebrada"))) {
            throw new IllegalArgumentException("Status inválido: " + streetLight.getStatus());
        }

        if (streetLight.getConsumoEnergia() < 0) {
            throw new IllegalArgumentException("O consumo de energia não pode ser negativo.");
        }

        try {
            LocalDate.parse(streetLight.getUltimoManutencao());
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato de data de manutenção inválido.");
        }
    }
}
