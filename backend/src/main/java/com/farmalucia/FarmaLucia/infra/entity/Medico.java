package com.farmalucia.FarmaLucia.infra.entity;

import com.farmalucia.FarmaLucia.infra.DTO.DadosCadastroMedicoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "medicos")
public class Medico implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome", length = 100)
    private String nome;
    @Column(name = "email", length = 100)
    private String email;
    @Column(name = "senha")
    private String senha;
    @Column(name = "crm", length = 100)
    private String crm;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private List<Endereco> enderecos;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private List<Telefone> telefones;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Deprecated
    public Medico(){}

    public Medico(DadosCadastroMedicoDTO dados) {
        atualizarDados(dados);
    }

    public void atualizarDados(DadosCadastroMedicoDTO dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.crm = dados.crm();
        this.senha = dados.senha();
        this.enderecos = dados.enderecos();
        this.telefones = dados.telefones();
        this.especialidade = dados.especialidade();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
