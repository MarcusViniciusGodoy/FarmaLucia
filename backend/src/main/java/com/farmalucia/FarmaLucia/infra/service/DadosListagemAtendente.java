package com.farmalucia.FarmaLucia.infra.service;

import com.farmalucia.FarmaLucia.infra.entity.Atendente;
import com.farmalucia.FarmaLucia.infra.entity.Endereco;
import com.farmalucia.FarmaLucia.infra.entity.Telefone;

public record DadosListagemAtendente(Long id, String nome, String email, String senha, Telefone telefone, Endereco endereco) {

    public DadosListagemAtendente(Atendente atendente){
        this(atendente.getId(),
                atendente.getNome(),
                atendente.getEmail(),
                atendente.getSenha(),
                (Telefone) atendente.getTelefones(),
                (Endereco) atendente.getEnderecos());
    }
}
