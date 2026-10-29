
package com.guardian.backend.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.guardian.backend.model.EnumAvatar;
import com.guardian.backend.model.InfoPerfil;
import com.guardian.backend.model.Usuario;
import com.guardian.backend.repository.UsuarioRepository;

@Service
public class PerfilService {

    private final UsuarioRepository usuarioRepository;

    public PerfilService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public InfoPerfil getInfo(String email) {

        Usuario usuario = usuarioRepository.getUsuarioByEmail(email);

        if (usuario == null) {
            return null;
        }

        InfoPerfil infoPerfil = new InfoPerfil();

        infoPerfil.setEmailUsuario(usuario.getEmail());
        infoPerfil.setNomeUsuario(usuario.getNome());
        infoPerfil.setQtdContasUsuario(usuario.getContas().size());
        infoPerfil.setAvatar(usuario.getAvatar());

        return infoPerfil;
    }

    public boolean redefinirSenha(
        String emailUsuario,
        String senhaAtual,
        String novaSenha,
        String confirmarSenha
    ) {
        if (!novaSenha.equals(confirmarSenha)) {
            return false;
        }

        Usuario usuario = usuarioRepository.getUsuarioByEmail(emailUsuario);

        if (usuario == null) {
            return false;
        }

        String senhaHashAtual = usuario.getSenhaHash();

        if (!BCrypt.checkpw(senhaAtual, senhaHashAtual)) {
            return false;
        }

        String novaSenhaHash = BCrypt.hashpw(novaSenha, BCrypt.gensalt());

        boolean deuCerto = usuarioRepository.redefinePW(
            emailUsuario,
            novaSenhaHash
        );

        if (!deuCerto) {
            return false;
        }

        return true;
    }

    public boolean trocarAvatar(String emailUsuario, String avatar) {
        EnumAvatar avatarSelecionado;

        try {
            avatarSelecionado = EnumAvatar.valueOf(avatar);
        } catch (IllegalArgumentException | NullPointerException e) {
            return false;
        }

        return usuarioRepository.trocarAvatar(
            emailUsuario,
            avatarSelecionado
        );
    }
}
