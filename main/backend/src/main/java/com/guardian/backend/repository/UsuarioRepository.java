// 1. CRIA UM OBJETO DO TIPO USUARIO
// 2. PEGA TODAS AS INFORMAÇÕES DO USUÁRIO
// 3. SALVA AS INFORMAÇÕES NO OBJETO
// 4. RETORNA O OBJETO




package com.guardian.backend.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.guardian.backend.model.Conta;
import com.guardian.backend.model.EnumAvatar;
import com.guardian.backend.model.EnumPlataforma;
import com.guardian.backend.model.Usuario;

@Repository
public class UsuarioRepository {

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
                    getContasByEmail(conexao, email)
                );

                return usuario;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar perfil.", e);
        }
    }

    private List<Conta> getContasByEmail(
        Connection conexao, String email
    ) throws SQLException {

        String sql = "SELECT * FROM contas WHERE contas_email = ?";
        List<Conta> contas = new ArrayList<>();

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, email);

            try (ResultSet resultado = stmt.executeQuery()) {

                while (resultado.next()) {
                    Conta conta = new Conta();

                    conta.setId(resultado.getInt("id"));

                    conta.setLogin_conta(
                        resultado.getString("login_conta")
                    );

                    conta.setSenha(
                        resultado.getString("senha")
                    );

                    String plataforma =
                        resultado.getString("plataforma");

                    if (plataforma != null) {
                        conta.setPlataforma(
                            EnumPlataforma.valueOf(plataforma)
                        );
                    }

                    conta.setUsuario(email);

                    contas.add(conta);
                }
            }
        }

        return contas;
    }

    public boolean cadastrar(String email, String nome, String senhaHash){


        return true;
    }

}