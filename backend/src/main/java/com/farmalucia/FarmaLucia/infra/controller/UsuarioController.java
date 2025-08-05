package com.farmalucia.FarmaLucia.infra.controller;

import com.farmalucia.FarmaLucia.infra.DTO.UsuarioDTO;
import com.farmalucia.FarmaLucia.infra.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @GetMapping(value = "/me")
    public ResponseEntity<UsuarioDTO> getMe(){
        UsuarioDTO dto = service.getMe();
        return ResponseEntity.ok(dto);
    }
}
