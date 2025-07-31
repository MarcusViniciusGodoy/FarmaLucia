package com.farmalucia.FarmaLucia.infra.DTO;

import com.farmalucia.FarmaLucia.infra.entity.Endereco;
import com.farmalucia.FarmaLucia.infra.entity.Telefone;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record DadosCadastroAtendenteDTO(Long id,
                                        @NotBlank
                                     String nome,
                                        @NotBlank
                                     @Email
                                     String email,
                                        @NotBlank
                                     String senha,
                                        List<Endereco> enderecos,
                                        List<Telefone> telefones) {
}
