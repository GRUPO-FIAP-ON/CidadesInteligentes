package br.com.fiap.CidadesInteligentes.controller;

import br.com.fiap.CidadesInteligentes.model.StreetLight;
import br.com.fiap.CidadesInteligentes.service.StreetLightService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/streetlights")
public class StreetLightController {

    private final StreetLightService streetLightService;

    public StreetLightController(StreetLightService streetLightService) {
        this.streetLightService = streetLightService;
    }

    @GetMapping
    public ResponseEntity<?> getAllStreetLights() {
        try {
            List<StreetLight> streetLights = streetLightService.getAllStreetLights();

            if (streetLights.isEmpty()) {
                streetLights.add(new StreetLight(1L, "Rua AS", "Ligada", 100.5, "2024-01-15"));
                streetLights.add(new StreetLight(2L, "Rua B", "Desligada", 0.0, "2024-01-10"));
                streetLights.add(new StreetLight(3L, "Rua C", "Quebrada", 75.0, "2024-01-01"));
            }

            return ResponseEntity.ok(streetLights);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("mensagem", "Erro interno do servidor"));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStreetLightById(@PathVariable Long id) {
        try {
            Optional<StreetLight> streetLight = streetLightService.getStreetLightById(id);
            return streetLight.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body((StreetLight) Collections.singletonMap("mensagem", "Luminária não encontrada")));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("mensagem", "Erro interno do servidor"));
        }
    }

    @PostMapping
    public ResponseEntity<?> createStreetLight(@RequestBody StreetLight streetLight) {
        try {
            StreetLight createdStreetLight = streetLightService.createStreetLight(streetLight);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdStreetLight);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("mensagem", "Erro ao criar luminária"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStreetLight(@PathVariable Long id, @RequestBody StreetLight newStreetLight) {
        try {
            StreetLight updatedStreetLight = streetLightService.updateStreetLight(id, newStreetLight);
            return ResponseEntity.ok(updatedStreetLight);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("mensagem", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("mensagem", "Erro ao atualizar luminária"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStreetLight(@PathVariable Long id) {
        try {
            streetLightService.deleteStreetLight(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("mensagem", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("mensagem", "Erro ao excluir luminária"));
        }
    }
}
