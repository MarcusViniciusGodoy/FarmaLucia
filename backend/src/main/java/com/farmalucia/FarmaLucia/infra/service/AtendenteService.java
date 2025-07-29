package com.farmalucia.FarmaLucia.infra.service;

import com.farmalucia.FarmaLucia.infra.RegraDeNegocioException;
import com.farmalucia.FarmaLucia.infra.entity.Atendente;
import com.farmalucia.FarmaLucia.infra.repository.AtendenteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AtendenteService {

    private AtendenteRepository repository;

    public AtendenteService(AtendenteRepository repository){
        this.repository = repository;
    }

    @Transactional
    public void cadastrar (DadosCadastroAtendente dados){
        if(repository.isJaCadastrado(dados.email(), dados.id())){
            throw new RegraDeNegocioException("Email já cadastrado para esse atendente");
        }

        if(dados.id() == null){
            repository.save(new Atendente(dados));
        } else {
            var atendente = repository.findById(dados.id()).orElseThrow();
            atendente.atualizarDados(dados);
        }
    }

    public DadosCadastroAtendente carregarPorId(Long id){
        var atendente = repository.findById(id).orElseThrow();
        return new DadosCadastroAtendente(atendente.getId(), atendente.getNome(), atendente.getEmail(), atendente.getSenha(), atendente.getEnderecos(), atendente.getTelefones() );
    }

    @Transactional
    public void excluirAtendente(Long id){
        repository.deleteById(id);
    }
}
