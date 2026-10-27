package com.guardian.backend.control;

import java.util.Map;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guardian.backend.service.VaultService;
import com.guardian.backend.model.Conta;

import jakarta.servlet.http.HttpSession;

@CrossOrigin(origins = {
    "http://127.0.0.1:5500",
    "http://localhost:5500"
})
@RestController
@RequestMapping("/vault") // primeira parte do endpoint "/vault" + [...]
public class VaultController {

    private final VaultService vaultService;

    public VaultController(VaultService vaultService) {
        this.vaultService = vaultService;
    }

    @PostMapping("/cadastrar_conta")
    // última parte do endpoint "/vault/cadastrar_conta" usando o método POST

    // Não precisa se preocupar com o que isso retorna, só precisa
    // se atentar a passar os parametros da forma correta
    public ResponseEntity<?> cadastrarConta(  
        @RequestBody Map<String, String> dados, 
        HttpSession session  
    ){

        String emailValido = (String) session.getAttribute("emailUsuario");
        if (emailValido == null){
            return ResponseEntity.status(401).build();
        }
        try {
            boolean deuCerto = vaultService.cadastrarConta(
                emailValido, 
                dados.get("loginConta"), 
                dados.get("senha"), 
                dados.get("plataforma")
            );

            if(!deuCerto){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Não foi possível realizar a ação");
            }
            return ResponseEntity.ok("Ação realizada");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Erro interno ao cadastrar a conta");
        }
    }

    // Não precisa se preocupar com o que isso retorna, só precisa
    // se atentar a passar os parametros da forma correta
    public ResponseEntity<?> removerConta(
        @RequestBody Map<String, String> dados, 
        HttpSession session
    ){
        String emailValido = (String) session.getAttribute("emailUsuario");
        if (emailValido == null){
            return ResponseEntity.status(401).build();
        }
        try {
            boolean deuCerto = vaultService.removerConta(
                dados.get("id")
            );

            if(!deuCerto){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Não foi possível realizar a ação");
            }
            return ResponseEntity.ok("Ação realizada");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Erro interno ao remover a conta");
        }
    }

    // Não precisa se preocupar com o que isso retorna, só precisa
    // se atentar a passar os parametros da forma correta
    public ResponseEntity<?> atualizarConta(  
        @RequestBody Map<String, String> dados, 
        HttpSession session  
    ){

        String emailValido = (String) session.getAttribute("emailUsuario");
        if (emailValido == null){
            return ResponseEntity.status(401).build();
        }
        try {
            boolean deuCerto = vaultService.atualizarConta(
                emailValido, 
                dados.get("loginConta"), 
                dados.get("senha"), 
                dados.get("plataforma")
            );

            if(!deuCerto){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Não foi possível realizar a ação");
            }
            return ResponseEntity.ok("Ação realizada");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Erro interno ao atualizar a conta");
        }
    }

    // Isso aqui retorna uma Response com um array de contas do usuario
    // É a única coisa que você tem que se preocupar com retorno
    // Se houver bug no back (tipo retornar contas de outros usuarios e tals) me avisa
    public ResponseEntity<?> getContasUsuario(
        @RequestBody Map<String, String> dados, 
        HttpSession session
    ){
        String emailValido = (String) session.getAttribute("emailUsuario");
        if (emailValido == null){
            return ResponseEntity.status(401).build();
        }

        try {
            List<Conta> contas = vaultService.getContas(
                emailValido
            );
            return ResponseEntity.ok(contas);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Erro interno ao cadastrar a conta");
        }
    }

}
