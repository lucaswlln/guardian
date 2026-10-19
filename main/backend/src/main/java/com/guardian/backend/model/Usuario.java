package com.guardian.backend.model;

public class Usuario {
    private String email;
    private String nome;
    private int qtdSenhas;
    private EnumAvatar avatar;

    public Usuario(String email, String nome, int qtdSenhas, EnumAvatar avatar) {
        this.email = email;
        this.nome = nome;
        this.qtdSenhas = qtdSenhas;
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQtdSenhas() {
        return qtdSenhas;
    }

    public void setQtdSenhas(int qtdSenhas) {
        if (qtdSenhas < 0) {
            throw new IllegalArgumentException(
                "A quantidade de senhas não pode ser negativa."
            );
        }
        this.qtdSenhas = qtdSenhas;
    }

    public EnumAvatar getAvatar() {
        return avatar;
    }

    public void setAvatar(EnumAvatar avatar) {
        if (avatar == null) {
            throw new IllegalArgumentException("O avatar não pode ser nulo.");
        }
        this.avatar = avatar;
    }
}