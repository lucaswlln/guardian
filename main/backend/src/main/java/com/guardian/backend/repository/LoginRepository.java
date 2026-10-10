package com.guardian.backend.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginRepository {

    public static String getEmail(String email) {
        String sql = "SELECT email FROM usuario WHERE email = ?";

        try (
            Connection conexao = Connect.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(sql)
        ) {
            stmt.setString(1, email);

            try (ResultSet resultado = stmt.executeQuery()) {
                if (resultado.next()) {
                    return resultado.getString("email");
                }

                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar email.", e);
        }
    }

    public static String getSenha(String email) {
        String sql = "SELECT senha_hash FROM usuario WHERE email = ?";

        try (
            Connection conexao = Connect.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(sql)
        ) {
            stmt.setString(1, email);

            try (ResultSet resultado = stmt.executeQuery()) {
                if (resultado.next()) {
                    return resultado.getString("senha_hash");
                }

                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar hash da senha.", e);
        }
    }

public static String getNome(String email) {
    String sql = "SELECT nome FROM usuario WHERE email = ?";

    try (
        Connection conexao = Connect.getConnection();
        PreparedStatement stmt = conexao.prepareStatement(sql)
    ) {
        stmt.setString(1, email);

        try (ResultSet resultado = stmt.executeQuery()) {
            if (resultado.next()) {
                return resultado.getString("nome");
            }
 
            return null;
        }
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao buscar nome do usuário.", e);
    }
}
}