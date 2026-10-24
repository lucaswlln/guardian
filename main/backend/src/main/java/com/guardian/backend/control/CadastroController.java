package com.guardian.backend.control;

import com.guardian.backend.service.CadastroService;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.Map;

@RestController 
@RequestMapping("/cadastrar")
public class CadastroController {
    private final CadastroService cadastroService;

    public CadastroController(CadastroService cadastroService) {
        this.cadastroService = cadastroService;
    }

    @PostMapping 
    public ResponseEntity<?> cadastrar(  @RequestBody Map<String, String> dados, HttpSession session  ){

        private boolean resultado = cadastroService.cadastrar(
            dados.get("email"), dados.get("username"), dados.get("password")
        );

        return ResponseEntity.ok("");
    }
}
