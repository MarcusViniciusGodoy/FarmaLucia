package com.farmalucia.FarmaLucia.infra.service;

import com.farmalucia.FarmaLucia.infra.DTO.DadosAgendamentoConsultaDTO;
import com.farmalucia.FarmaLucia.infra.DTO.DadosListagemConsultaDTO;
import com.farmalucia.FarmaLucia.infra.entity.Consulta;
import com.farmalucia.FarmaLucia.infra.repository.ConsultaRepository;
import com.farmalucia.FarmaLucia.infra.repository.MedicoRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ConsultaService {

    private final ConsultaRepository repository;
    private final MedicoRepository medicoRepository;

    public ConsultaService(ConsultaRepository repository, MedicoRepository medicoRepository) {
        this.repository = repository;
        this.medicoRepository = medicoRepository;
    }

    public Page<DadosListagemConsultaDTO> listar(Pageable paginacao) {
        return repository.findAllByOrderByData(paginacao).map(DadosListagemConsultaDTO::new);
    }

    @Transactional
    public void cadastrar(DadosAgendamentoConsultaDTO dados) {
        var medicoConsulta = medicoRepository.findById(dados.idMedico()).orElseThrow();
        if (dados.id() == null) {
            repository.save(new Consulta(medicoConsulta, dados));
        } else {
            var consulta = repository.findById(dados.id()).orElseThrow();
            consulta.modificarDados(medicoConsulta, dados);
        }
    }

    public DadosAgendamentoConsultaDTO carregarPorId(Long id) {
        var consulta = repository.findById(id).orElseThrow();
        var medicoConsulta = medicoRepository.getReferenceById(consulta.getMedico().getId());
        return new DadosAgendamentoConsultaDTO(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente(), consulta.getData(), medicoConsulta.getEspecialidade());
    }

    @Transactional
    public void excluir(Long id) {
        repository.deleteById(id);
    }
}
