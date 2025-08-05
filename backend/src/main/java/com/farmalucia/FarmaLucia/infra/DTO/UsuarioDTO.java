package com.farmalucia.FarmaLucia.infra.DTO;

import com.farmalucia.FarmaLucia.infra.entity.Endereco;
import com.farmalucia.FarmaLucia.infra.entity.Telefone;
import com.farmalucia.FarmaLucia.infra.entity.Usuario;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@Getter
public class UsuarioDTO {

    private Long id;
    private String nome;
    private String email;
    private List<String> enderecos = new ArrayList<>();
    private List<String> telefones = new ArrayList<>();
    private List<String> roles = new ArrayList<>();

    public UsuarioDTO(Usuario entity){
        id = entity.getId();
        nome = entity.getNome();
        email = entity.getEmail();
        for(Endereco endereco : entity.getEnderecos()){
            enderecos.add(endereco.getRua());
            enderecos.add(endereco.getComplemento());
            enderecos.add(endereco.getCep());
            enderecos.add(endereco.getCidade());
        }
        for(Telefone telefone : entity.getTelefones()){
            telefones.add(telefone.getNumero());
        }
        for(GrantedAuthority role : entity.getRoles()){
            roles.add(role.getAuthority());
        }
    }

}
