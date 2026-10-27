package com.guardian.backend.control;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guardian.backend.service.CadastroService;

import jakarta.servlet.http.HttpSession;

@CrossOrigin(origins = {
    "http://127.0.0.1:5500",
    "http://localhost:5500"
})
@RestController 
@RequestMapping("/cadastrar")
public class CadastroController {
    private final CadastroService cadastroService;

    public CadastroController(CadastroService cadastroService) {
        this.cadastroService = cadastroService;
    }

    @PostMapping 
    public ResponseEntity<?> cadastrar(  @RequestBody Map<String, String> dados  ){

        boolean resultado = cadastroService.cadastrar(
            dados.get("email"), dados.get("username"), dados.get("password")
        );

        if (resultado){
            return ResponseEntity.ok("Cadastrado com sucesso");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
