package com.farmalucia.FarmaLucia.infra.DTO;

import com.farmalucia.FarmaLucia.infra.entity.Consulta;
import com.farmalucia.FarmaLucia.infra.entity.Especialidade;

import java.time.LocalDateTime;

public record DadosListagemConsultaDTO(Long id, String medico, String paciente, LocalDateTime data, Especialidade especialidade) {

    public DadosListagemConsultaDTO(Consulta consulta) {
        this(consulta.getId(), consulta.getMedico().getNome(), consulta.getPaciente(), consulta.getData(), consulta.getMedico().getEspecialidade());
    }

}
