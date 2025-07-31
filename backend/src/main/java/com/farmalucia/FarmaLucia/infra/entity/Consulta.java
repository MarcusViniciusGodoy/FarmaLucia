package com.farmalucia.FarmaLucia.infra.entity;

import com.farmalucia.FarmaLucia.infra.DTO.DadosAgendamentoConsultaDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "consulta")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String paciente;
    @ManyToOne(fetch = FetchType.LAZY)
    private Medico medico;
    private LocalDateTime data;

    @Deprecated
    public Consulta(){}

    public Consulta(Medico medico, DadosAgendamentoConsultaDTO dados) {
        modificarDados(medico, dados);
    }

    public void modificarDados(Medico medico, DadosAgendamentoConsultaDTO dados) {
        this.medico = medico;
        this.paciente = dados.paciente();
        this.data = dados.data();
    }
}
