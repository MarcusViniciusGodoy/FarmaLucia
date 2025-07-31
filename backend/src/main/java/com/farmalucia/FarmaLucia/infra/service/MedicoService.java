package com.farmalucia.FarmaLucia.infra.service;

import com.farmalucia.FarmaLucia.infra.DTO.DadosCadastroMedicoDTO;
import com.farmalucia.FarmaLucia.infra.DTO.DadosListagemMedicoDTO;
import com.farmalucia.FarmaLucia.infra.RegraDeNegocioException;
import com.farmalucia.FarmaLucia.infra.entity.Especialidade;
import com.farmalucia.FarmaLucia.infra.entity.Medico;
import com.farmalucia.FarmaLucia.infra.repository.MedicoRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MedicoService {

    private final MedicoRepository repository;

    public MedicoService(MedicoRepository repository) {
        this.repository = repository;
    }

    public Page<DadosListagemMedicoDTO> listar(Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosListagemMedicoDTO::new);
    }

    public DadosCadastroMedicoDTO carregarPorId(Long id){
        var medico = repository.findById(id).orElseThrow();
        return new DadosCadastroMedicoDTO(medico.getId(), medico.getNome(), medico.getEmail(), medico.getSenha(), medico.getCrm(), medico.getTelefones(), medico.getEnderecos(), medico.getEspecialidade());
    }

    @Transactional
    public void cadastrar(DadosCadastroMedicoDTO dados){
        if(repository.isJaCadastrado(dados.email(), dados.crm(), dados.id())){
            throw new RegraDeNegocioException("E-mail ou CRM já cadastrados para outro médico");
        }

        if(dados.id() == null){
            repository.save(new Medico(dados));
        } else {
            var medico = repository.findById(dados.id()).orElseThrow();
            medico.atualizarDados(dados);
        }
    }

    @Transactional
    public void excluir(Long id){
        repository.deleteById(id);
    }

    public List<DadosListagemMedicoDTO> listarPorEspecialidade(Especialidade especialidade) {
        return repository.findByEspecialidade(especialidade)
                .stream()
                .map(DadosListagemMedicoDTO::new)
                .toList();
    }
}
