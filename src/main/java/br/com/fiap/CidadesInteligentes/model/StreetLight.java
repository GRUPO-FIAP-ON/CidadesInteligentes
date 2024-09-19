package br.com.fiap.CidadesInteligentes.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StreetLight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String localizacao;

    @Column(nullable = false)
    private String status; // Ligada, Desligada, Quebrada

    @Column(nullable = false)
    private double consumoEnergia; // em kWh

    @Column(nullable = false)
    private String ultimoManutencao; // Data da última manutenção
}
