package com.guardian.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "contas")
public class Conta {

    @Id
    private int id;

    @ManyToOne
    @JoinColumn(name = "usuario_email", referencedColumnName = "email")
    private Usuario usuario;

    @Column(name = "login_conta")
    private String login_conta;

    @Column(name = "senha")
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(name = "plataforma")
    private EnumPlataforma plataforma;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getLoginConta() {
        return login_conta;
    }

    public void setLoginConta(String login_conta) {
        this.login_conta = login_conta;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public EnumPlataforma getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(EnumPlataforma plataforma) {
        this.plataforma = plataforma;
    }
}