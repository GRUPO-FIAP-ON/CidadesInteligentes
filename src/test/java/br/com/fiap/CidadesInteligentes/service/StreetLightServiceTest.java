package br.com.fiap.CidadesInteligentes.service;

import br.com.fiap.CidadesInteligentes.model.StreetLight;
import br.com.fiap.CidadesInteligentes.repository.StreetLightRepository;
import br.com.fiap.CidadesInteligentes.service.impl.StreetLightServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StreetLightServiceTest {
    private StreetLightServiceImpl streetLightService;
    private StreetLightRepository streetLightRepository;

    @BeforeEach
    void setUp() throws Exception {
        streetLightRepository = Mockito.mock(StreetLightRepository.class);
        streetLightService = new StreetLightServiceImpl();

        // Usando reflexão para injetar o mock no campo privado
        Field field = StreetLightServiceImpl.class.getDeclaredField("streetLightRepository");
        field.setAccessible(true);
        field.set(streetLightService, streetLightRepository);
    }

    @Test
    void testGetAllStreetLights() {
        // Cenário: Quando há luminárias cadastradas
        StreetLight streetLight = new StreetLight(1L, "Rua A", "Ligada", 10.5, LocalDate.now().toString());
        when(streetLightRepository.findAll()).thenReturn(Collections.singletonList(streetLight));

        var result = streetLightService.getAllStreetLights();

        assertEquals(1, result.size());
        assertEquals("Rua A", result.get(0).getLocalizacao());
    }
    @Test
    void testCreateStreetLight() {
        // Cenário: Criação de uma luminária válida
        StreetLight streetLight = new StreetLight(null, "Rua A", "Ligada", 10.5, LocalDate.now().toString());
        when(streetLightRepository.save(any(StreetLight.class))).thenReturn(streetLight);

        StreetLight created = streetLightService.createStreetLight(streetLight);

        assertNotNull(created);
        assertEquals("Rua A", created.getLocalizacao());
    }



    @Test
    void testUpdateStreetLight() {
        // Cenário: Atualização de uma luminária existente
        StreetLight streetLight = new StreetLight(1L, "Rua A", "Ligada", 10.5, LocalDate.now().toString());
        when(streetLightRepository.existsById(1L)).thenReturn(true);
        when(streetLightRepository.save(any(StreetLight.class))).thenReturn(streetLight);

        StreetLight updated = streetLightService.updateStreetLight(1L, streetLight);

        assertNotNull(updated);
        assertEquals("Rua A", updated.getLocalizacao());
    }

    @Test
    void testUpdateStreetLightNotFound() {
        // Cenário: Tentativa de atualização de uma luminária que não existe
        StreetLight streetLight = new StreetLight(1L, "Rua A", "Ligada", 10.5, LocalDate.now().toString());
        when(streetLightRepository.existsById(1L)).thenReturn(false);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            streetLightService.updateStreetLight(1L, streetLight);
        });

        assertEquals("Luminária com id 1 não encontrada.", exception.getMessage());
    }

    @Test
    void testDeleteStreetLight() {
        // Cenário: Exclusão de uma luminária existente
        when(streetLightRepository.existsById(1L)).thenReturn(true);

        streetLightService.deleteStreetLight(1L);

        verify(streetLightRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteStreetLightNotFound() {
        // Cenário: Tentativa de exclusão de uma luminária que não existe
        when(streetLightRepository.existsById(1L)).thenReturn(false);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            streetLightService.deleteStreetLight(1L);
        });

        assertEquals("Luminária com id 1 não encontrada.", exception.getMessage());
    }
}
