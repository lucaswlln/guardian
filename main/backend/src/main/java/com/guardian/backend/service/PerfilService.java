
package com.guardian.backend.service;

import org.springframework.stereotype.Service;

import com.guardian.backend.model.InfoPerfil;
import com.guardian.backend.model.Usuario;
import com.guardian.backend.repository.UsuarioRepository;

@Service
public class PerfilService {

    private final UsuarioRepository perfilRepository;

    public PerfilService(UsuarioRepository perfilRepository) {
        this.perfilRepository = perfilRepository;
    }

    public InfoPerfil getInfo(String email) {

        Usuario usuario = perfilRepository.getUsuarioByEmail(email);

        if (usuario == null) {
            return null;
        }

        InfoPerfil infoPerfil = new InfoPerfil();

        infoPerfil.setEmailUsuario(usuario.getEmail());
        infoPerfil.setNomeUsuario(usuario.getNome());
        infoPerfil.setQtdContasUsuario(usuario.getContas().size());

        return infoPerfil;
    }
}
