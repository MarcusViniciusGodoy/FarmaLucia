package com.farmalucia.FarmaLucia.infra.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public ResponseEntity<Void> redirecionarParaLoginHtml() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "http://127.0.0.1:5501/login.html");
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }
}

