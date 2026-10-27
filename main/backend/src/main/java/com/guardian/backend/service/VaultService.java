package com.guardian.backend.service;

import org.springframework.stereotype.Service;

import com.guardian.backend.repository.ContaRepository;

@Service
public class VaultService {

    private final ContaRepository contaRepository;

    public VaultService(ContaRepository contaRepository){
        this.contaRepository = contaRepository;
    }

    public boolean cadastrarConta(
        String email, String login, String senha, String plataforma
    ){
        return contaRepository.cadastrarConta(  
            email, login, senha, plataforma
          );
    }

    // public removerConta(){

    // }

    // public atualizarConta(){

    // }

    // public getContas(){

    // }

}
