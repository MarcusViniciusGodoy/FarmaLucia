package com.farmalucia.FarmaLucia.infra.service;

import com.farmalucia.FarmaLucia.infra.DTO.UsuarioDTO;
import com.farmalucia.FarmaLucia.infra.entity.Role;
import com.farmalucia.FarmaLucia.infra.entity.Usuario;
import com.farmalucia.FarmaLucia.infra.projections.UserDetailsProjection;
import com.farmalucia.FarmaLucia.infra.repository.UsuarioRepository;
import com.farmalucia.FarmaLucia.infra.util.CustomUsuarioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private CustomUsuarioUtil customUsuarioUtil;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<UserDetailsProjection> result = repository.searchUserAndRolesByEmail(username);
        if (result.size() == 0){
            throw new UsernameNotFoundException("User not found");
        }
        Usuario usuario = new Usuario();
        usuario.setEmail(result.get(0).getUsername());
        usuario.setSenha(result.get(0).getPassword());

        for (UserDetailsProjection projection : result){
            usuario.addRole(new Role(projection.getRoleId(), projection.getAuthority()));
        }
        return usuario;
    }

    protected Usuario authenticated(){
        try {
            String username = customUsuarioUtil.getLoggedUsuario();
            return repository.findByEmail(username).get();
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found");
        }
    }

    @Transactional(readOnly = true)
    public UsuarioDTO getMe(){
        Usuario usuario = authenticated();
        return new UsuarioDTO(usuario);
    }
}
