package com.guardian.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
    private String usuario_email;

    @Column(name = "login_conta")
    private String login_conta;

    @Column(name = "senha")
    private String senha;

    @Column(name = "plataforma")
    private EnumPlataforma plataforma;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario_email;
    }

    public void setUsuario(String usuario_email) {
        this.usuario_email = usuario_email;
    }

    public String getLogin_conta() {
        return login_conta;
    }

    public void setLogin_conta(String login_conta) {
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
