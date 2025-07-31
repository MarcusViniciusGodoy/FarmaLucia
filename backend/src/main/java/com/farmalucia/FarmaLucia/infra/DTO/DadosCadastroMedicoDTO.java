package com.farmalucia.FarmaLucia.infra.DTO;

import com.farmalucia.FarmaLucia.infra.entity.Endereco;
import com.farmalucia.FarmaLucia.infra.entity.Especialidade;
import com.farmalucia.FarmaLucia.infra.entity.Telefone;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public record DadosCadastroMedicoDTO(
        Long id,
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String senha,

        @NotBlank
        @Pattern(regexp = "\\d{4,6}", message = "CRM deve ter de 4 a 6 digitos numéricos")
        String crm,

        List<Telefone> telefones,
        List<Endereco> enderecos,

        @NotNull
        Especialidade especialidade
) {
}
