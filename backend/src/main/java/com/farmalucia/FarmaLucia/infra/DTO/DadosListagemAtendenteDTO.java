package com.farmalucia.FarmaLucia.infra.DTO;

import com.farmalucia.FarmaLucia.infra.entity.Atendente;
import com.farmalucia.FarmaLucia.infra.entity.Endereco;
import com.farmalucia.FarmaLucia.infra.entity.Telefone;

public record DadosListagemAtendenteDTO(Long id, String nome, String email, String senha, Telefone telefone, Endereco endereco) {

    public DadosListagemAtendenteDTO(Atendente atendente){
        this(atendente.getId(),
                atendente.getNome(),
                atendente.getEmail(),
                atendente.getSenha(),
                (Telefone) atendente.getTelefones(),
                (Endereco) atendente.getEnderecos());
    }
}
