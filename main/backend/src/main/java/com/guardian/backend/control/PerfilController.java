package com.guardian.backend.control;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guardian.backend.model.InfoPerfil;
import com.guardian.backend.service.PerfilService;

import jakarta.servlet.http.HttpSession;

@CrossOrigin(
    origins = {
        "http://127.0.0.1:5500",
        "http://localhost:5500"
    },
    allowCredentials = "true"
)
@RestController
@RequestMapping("/usuario")
public class PerfilController {

    private final PerfilService perfilService;

    public PerfilController(PerfilService perfilService) {
        this.perfilService = perfilService;
    }

    @GetMapping("/info")
    public ResponseEntity<?> getInfoUsuario (HttpSession session) {

        String email = (String) session.getAttribute("emailUsuario");

        if (email == null) {
            return ResponseEntity.status(401).build();
        }

        InfoPerfil infoPerfil = perfilService.getInfo(email);

        if (infoPerfil == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(infoPerfil);
    }

    @PostMapping("/redefinir_senha")
    public ResponseEntity<?> redefinirSenha(
        @RequestBody Map<String, String> dados,
        HttpSession session
    ){

        if((String) session.getAttribute("emailUsuario") == null){
            return ResponseEntity.status(401).build();
        }
        boolean deuCerto = perfilService.redefinirSenha(
            (String) session.getAttribute("emailUsuario"),
            dados.get("senhaAtual"),
            dados.get("novaSenha"),
            dados.get("confirmarSenha")
        );

        if(deuCerto){
            return ResponseEntity.ok("Controller: Senha redefinida");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body("Controller: Não foi possível realizar a ação");
    }

    @PostMapping("/trocar_avatar")
    public ResponseEntity<?> trocarAvatar(
        @RequestBody Map<String, String> dados,
        HttpSession session
    ){
        String emailUsuario = (String) session.getAttribute("emailUsuario");

        if (emailUsuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        boolean deuCerto = perfilService.trocarAvatar(emailUsuario, (String) dados.get("avatar"));
        if(!deuCerto){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Controller: não foi possível trocar o avatar");
        }
        return ResponseEntity.ok("Controller: avatar trocado");
    }
}
