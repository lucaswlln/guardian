package com.guardian.backend.service;

import org.mindrot.jbcrypt.BCrypt;

import com.guardian.backend.model.Usuario;
import com.guardian.backend.repository.LoginRepository;

public class LoginService {

    public boolean login(String email, String senha) {

        // pega email, se for null -> false
        String emailValido = LoginRepository.getEmail(email);
        if(emailValido == null){
            return false;
        }

        // compara se a senha atrelada ao email corresponde -> true
        if (BCrypt.checkpw(senha, LoginRepository.getSenha(email))) {
            Usuario usuario = new Usuario(email, LoginRepository.getNome(email));
            return true;
        }

        return false;
    }

    public String gerarHash(String senha) {
        return BCrypt.hashpw(senha, BCrypt.gensalt());
    }


}