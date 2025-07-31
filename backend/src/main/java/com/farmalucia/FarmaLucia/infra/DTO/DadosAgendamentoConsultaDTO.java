package com.farmalucia.FarmaLucia.infra.DTO;

import com.farmalucia.FarmaLucia.infra.entity.Especialidade;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosAgendamentoConsultaDTO(
        Long id,
        Long idMedico,

        @NotNull
        String paciente,

        @NotNull
        @Future
        LocalDateTime data,

        Especialidade especialidade
) {
}
