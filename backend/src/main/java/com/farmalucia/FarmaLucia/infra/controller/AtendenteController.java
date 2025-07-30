package com.farmalucia.FarmaLucia.infra.controller;

import com.farmalucia.FarmaLucia.infra.RegraDeNegocioException;
import com.farmalucia.FarmaLucia.infra.service.AtendenteService;
import com.farmalucia.FarmaLucia.infra.service.DadosCadastroAtendente;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("atendente")
public class AtendenteController {

    private static final String PAGINA_LISTAGEM = "atendente/listagem-atendentes";
    private static final String PAGINA_CADASTRO = "atendente/formulario-atendente";
    private static final String REDIRECT_LISTAGEM = "redirect:/atendente?sucesso";

    private final AtendenteService service;

    public AtendenteController(AtendenteService service) {
        this.service = service;
    }

    @GetMapping("formulario")
    public String carregarPaginaCadastro(Long id, Model model){
        if(id != null){
            model.addAttribute("dados", service.carregarPorId(id));
        } else {
            model.addAttribute("dados", new DadosCadastroAtendente(null,"","","", null, null));
        }
        return PAGINA_CADASTRO;
    }

    @PostMapping
    public String cadastrarAtendente(@Valid @ModelAttribute("dados") DadosCadastroAtendente dados, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("dados", dados);
            return PAGINA_CADASTRO;
        }

        try {
            service.cadastrar(dados);
            return REDIRECT_LISTAGEM;
        } catch (RegraDeNegocioException e){
            model.addAttribute("erro", e.getMessage());
            model.addAttribute("dados", dados);
            return PAGINA_CADASTRO;
        }
    }

    @DeleteMapping
    public String excluirAtendente(Long id){
        service.excluirAtendente(id);
        return REDIRECT_LISTAGEM;
    }
}
