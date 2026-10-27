// 1. CRIA UM OBJETO DO TIPO USUARIO
// 2. PEGA TODAS AS INFORMAÇÕES DO USUÁRIO
// 3. SALVA AS INFORMAÇÕES NO OBJETO
// 4. RETORNA O OBJETO




package com.guardian.backend.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.guardian.backend.model.EnumAvatar;
import com.guardian.backend.model.Usuario;

@Repository
public class UsuarioRepository {
    private final ContaRepository contaRepository;

    public UsuarioRepository(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    public Usuario getUsuarioByEmail(String email) {
        String sql = "SELECT * FROM usuarios WHERE email = ?";

        try (
            Connection conexao = Connect.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(sql)
        ) {
            stmt.setString(1, email);

            try (ResultSet resultado = stmt.executeQuery()) {

                if (!resultado.next()) {
                    return null;
                }

                Usuario usuario = new Usuario();

                usuario.setEmail(resultado.getString("email"));
                usuario.setNome(resultado.getString("nome"));
                usuario.setSenhaHash(resultado.getString("senha_hash"));

                String avatar = resultado.getString("avatar");

                if (avatar != null) {
                    usuario.setAvatar(EnumAvatar.valueOf(avatar));
                }

                // Busca as contas vinculadas ao usuário
                usuario.setContas(
                    contaRepository.getContasByEmail(conexao, usuario)
                );

                return usuario;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar perfil.", e);
        }
    }

    public boolean cadastrar(String email, String nome, String senhaHash){

        String sql = "INSERT INTO usuarios (email, nome, senha_hash) VALUES (?, ?, ?)";

        try (
            Connection conexao = Connect.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(sql)
        ){
            stmt.setString(1, email);
            stmt.setString(2, nome);
            stmt.setString(3, senhaHash);

            return stmt.executeUpdate() == 1;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}