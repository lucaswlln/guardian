package com.guardian.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.guardian.backend.model.Conta;
import com.guardian.backend.model.Usuario;
import com.guardian.backend.repository.ContaRepository;
import com.guardian.backend.repository.UsuarioRepository;

@Service
public class VaultService {

    private final UsuarioRepository usuarioRepository;
    private final ContaRepository contaRepository;

    public VaultService(ContaRepository contaRepository, UsuarioRepository usuarioRepository){
        this.contaRepository = contaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public boolean cadastrarConta(
        String email, String login, String senha, String plataforma
    ){
        return contaRepository.cadastrarConta(  
            email, login, senha, plataforma
          );
    }

    public boolean removerConta(int idConta){
        return contaRepository.removerConta(idConta);
    }

    public boolean atualizarConta(
        int idConta, String login, String senha, String plataforma, String emailUsuario
    ){
        return contaRepository.atualizarConta(  
            idConta, login, senha, plataforma, emailUsuario
          );
    }

    public List<Conta> getContas(String emailUsuario){
        Usuario usuario = usuarioRepository.getUsuarioByEmail(emailUsuario);
        return usuario.getContas();
    }

}
