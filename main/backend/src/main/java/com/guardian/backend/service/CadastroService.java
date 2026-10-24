package com.guardian.backend.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.guardian.backend.repository.UsuarioRepository;

@Service
public class CadastroService {

    private final UsuarioRepository usuarioRepository;

    public CadastroService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public boolean cadastrar(String email, String nome, String senha) {

        String senhaHash = BCrypt.hashpw(senha, BCrypt.gensalt());

        return usuarioRepository.cadastrar(email, nome, senhaHash);
    }
}