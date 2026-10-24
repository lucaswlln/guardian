package com.guardian.backend.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.guardian.backend.model.Usuario;
import com.guardian.backend.repository.UsuarioRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class LoginService {

    private final UsuarioRepository usuarioRepository;

    public LoginService(UsuarioRepository UsuarioRepository) {
        this.usuarioRepository = UsuarioRepository;
    }

    public boolean login(String email, String senha, HttpSession session) {

        Usuario usuario = usuarioRepository.getUsuarioByEmail(email);

        if (usuario == null) {
            return false;
        }

        String emailValido = usuario.getEmail();

        String senhaHash = usuario.getSenhaHash();

        if (senhaHash == null || !BCrypt.checkpw(senha, senhaHash)) {
            return false;
        }

        session.setAttribute("emailUsuario", emailValido);

        return true;
    }

    public String gerarHash(String senha) {
        return BCrypt.hashpw(senha, BCrypt.gensalt());
    }
}