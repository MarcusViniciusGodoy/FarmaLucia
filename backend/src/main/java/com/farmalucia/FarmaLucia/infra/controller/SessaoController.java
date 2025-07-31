package com.farmalucia.FarmaLucia.infra.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class SessaoController {

    @GetMapping("/api/verifica-autenticacao")
    public ResponseEntity<?> verificaAutenticacao(Principal principal) {
        if (principal != null) {
            return ResponseEntity.ok("Autenticado");
        } else {
            return ResponseEntity.status(401).body("Não autenticado");
        }
    }
}
