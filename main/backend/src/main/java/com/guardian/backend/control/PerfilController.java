
package com.guardian.backend.control;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guardian.backend.model.InfoPerfil;
import com.guardian.backend.service.PerfilService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/usuario")
public class PerfilController {

    private final PerfilService perfilService;

    public PerfilController(PerfilService perfilService) {
        this.perfilService = perfilService;
    }

    @GetMapping("/info")
    public ResponseEntity<?> getInfoUsuario(HttpSession session) {

        String email = (String) session.getAttribute("emailUsuario");

        // if (email == null) {
        //     return ResponseEntity.status(401).build();               SE TÁ LOGADO É PORQUE TEM EMAIL
        // }

        InfoPerfil infoPerfil = perfilService.getInfo(email);

        // if (infoPerfil == null) {
        //     return ResponseEntity.notFound().build();                SE TÁ LOGADO É PORQUE TEM PERFIL
        // }

        return ResponseEntity.ok(infoPerfil);
    }
}
