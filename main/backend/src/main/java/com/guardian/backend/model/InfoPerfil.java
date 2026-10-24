package com.guardian.backend.model;

public class InfoPerfil {
    private String nomeUsuario;
    private String emailUsuario;
    private int qtdContasUsuario;

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public int getQtdContasUsuario() {
        return qtdContasUsuario;
    }

    public void setQtdContasUsuario(int qtdContasUsuario) {
        this.qtdContasUsuario = qtdContasUsuario;
    }
}