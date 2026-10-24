package com.guardian.backend.control;

import com.guardian.backend.service.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.Map;

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
            HttpSession session) {

        boolean sucesso = loginService.login(
                dados.get("email"),
                dados.get("senha"),
                session
        );

        if (!sucesso) {
            return ResponseEntity.status(401).body("Login inválido");
        }

        return ResponseEntity.ok("Login realizado com sucesso");
    }
}