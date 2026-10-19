package com.guardian.backend.service;

import org.mindrot.jbcrypt.BCrypt;

import com.guardian.backend.repository.LoginRepository;

public class LoginService {

    public boolean login(String email, String senha) {

        // pega email, se for null -> false
        String emailValido = LoginRepository.getEmail(email);
        if(emailValido == null){
            return false;
        }

        // compara se a senha atrelada ao email corresponde
        return BCrypt.checkpw(senha, LoginRepository.getSenha(email));
    }

    public String gerarHash(String senha) {
        return BCrypt.hashpw(senha, BCrypt.gensalt());
    }


}