package com.farmalucia.FarmaLucia.infra.DTO;

import com.farmalucia.FarmaLucia.infra.entity.Endereco;
import com.farmalucia.FarmaLucia.infra.entity.Especialidade;
import com.farmalucia.FarmaLucia.infra.entity.Medico;
import com.farmalucia.FarmaLucia.infra.entity.Telefone;

public record DadosListagemMedicoDTO(Long id, String nome, String email, String senha, String crm, Telefone telefone, Endereco endereco, Especialidade especialidade) {

    public DadosListagemMedicoDTO(Medico medico){
        this(medico.getId(),
                medico.getNome(),
                medico.getEmail(),
                medico.getSenha(),
                medico.getCrm(),
                (Telefone) medico.getTelefones(),
                (Endereco) medico.getEnderecos(),
                medico.getEspecialidade());
    }
}
