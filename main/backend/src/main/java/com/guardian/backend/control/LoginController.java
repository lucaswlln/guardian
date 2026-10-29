package com.guardian.backend.control;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guardian.backend.service.LoginService;

import jakarta.servlet.http.HttpSession;

@CrossOrigin(
    origins = {
        "http://127.0.0.1:5500",
        "http://localhost:5500"
    },
    allowCredentials = "true"
)
@RestController
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity<?> login(
            @RequestBody Map<String, String> dados,
            HttpSession session
    ) {
        boolean sucesso = loginService.login(
            dados.get("email"),
            dados.get("senha"),
            session
        );
        System.out.println("ID da sessão no login: " + session.getId());
        System.out.println("Email na sessão: " + session.getAttribute("emailUsuario"));

        if (!sucesso) {
            return ResponseEntity.status(401).body("Login inválido");
        }

        return ResponseEntity.ok("Login realizado com sucesso");
    }
}