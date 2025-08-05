package com.farmalucia.FarmaLucia.infra.service;

import com.farmalucia.FarmaLucia.infra.DTO.UserDTO;
import com.farmalucia.FarmaLucia.infra.entity.Role;
import com.farmalucia.FarmaLucia.infra.entity.User;
import com.farmalucia.FarmaLucia.infra.projections.UserDetailsProjection;
import com.farmalucia.FarmaLucia.infra.repository.UserRepository;
import com.farmalucia.FarmaLucia.infra.util.CustomUsuarioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private CustomUsuarioUtil customUsuarioUtil;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<UserDetailsProjection> result = repository.searchUserAndRolesByEmail(username);
        if (result.size() == 0){
            throw new UsernameNotFoundException("User not found");
        }
        User usuario = new User();
        usuario.setEmail(result.get(0).getUsername());
        usuario.setSenha(result.get(0).getPassword());

        for (UserDetailsProjection projection : result){
            usuario.addRole(new Role(projection.getRoleId(), projection.getAuthority()));
        }
        return usuario;
    }

    protected User authenticated(){
        try {
            String username = customUsuarioUtil.getLoggedUsuario();
            return repository.findByEmail(username).get();
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found");
        }
    }

    @Transactional(readOnly = true)
    public UserDTO getMe(){
        User usuario = authenticated();
        return new UserDTO(usuario);
    }
}
