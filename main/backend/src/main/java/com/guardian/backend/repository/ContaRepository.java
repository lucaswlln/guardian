package com.guardian.backend.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.guardian.backend.model.Conta;
import com.guardian.backend.model.EnumPlataforma;
import com.guardian.backend.model.Usuario;

@Repository
public class ContaRepository {
    public boolean cadastrarConta(
        String usuarioEmail, String loginConta, String senhaHash, String plataforma
    ) {
        if (loginConta == null || loginConta.isBlank()
            || senhaHash == null || senhaHash.isBlank()
            || plataforma == null) {
                return false;
        }
        String sql = "INSERT INTO contas (usuario_email, login_conta, senha, plataforma) VALUES (?, ?, ?, ?)";
        try(
            Connection conexao = Connect.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(sql)
        ){
            stmt.setString(1, usuarioEmail);
            stmt.setString(2, loginConta);
            stmt.setString(3, senhaHash);
            stmt.setString(4, plataforma);

            stmt.executeUpdate();
        
        } catch (SQLException e){
            throw new RuntimeException("Repository: Erro ao cadastrar conta.", e);
        }

        return true;
    }

    public boolean removerConta(int idConta){
        String sql = "DELETE FROM contas WHERE id = ?";

        try(
            Connection conexao = Connect.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(sql);
        ) {
            stmt.setString(1, String.valueOf(idConta));
            stmt.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException("Repository: Erro ao excluir conta");
        }
        return true;
    }

    public boolean atualizarConta(
        int idConta, String loginConta, String senhaHash, String plataforma, String emailUsuario
    ){
        if (loginConta == null || loginConta.isBlank()
            || senhaHash == null || senhaHash.isBlank()
            || plataforma == null) {
                return false;
        }
        String sql = "UPDATE contas SET login_conta = ?, senha = ?, plataforma = ? WHERE id = ? AND usuario_email = ?;";
        try(
            Connection conexao = Connect.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(sql)
        ){
            stmt.setString(1, loginConta);
            stmt.setString(2, senhaHash);
            stmt.setString(3, plataforma);
            stmt.setString(4, String.valueOf(idConta));
            stmt.setString(4, emailUsuario);

            stmt.executeUpdate();
        
        } catch (SQLException e){
            throw new RuntimeException("Repository: Erro ao atualizar conta.", e);
        }

        return true;
    }

    public List<Conta> getContasByEmail(
        Connection conexao, Usuario usuario
    ) throws SQLException {

        String sql = "SELECT * FROM contas WHERE usuario_email = ?";
        List<Conta> contas = new ArrayList<>();

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, usuario.getEmail());

            try (ResultSet resultado = stmt.executeQuery()) {

                while (resultado.next()) {
                    Conta conta = new Conta();

                    conta.setId(resultado.getInt("id"));

                    conta.setLoginConta(
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

                    conta.setUsuario(usuario);

                    contas.add(conta);
                }
            }
        }

        return contas;
    }

    // public Conta getContaById(){

    // }

}
