package com.guardian.backend.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    private String email;

    @Column(name = "nome")
    private String nome;

    @Enumerated(EnumType.STRING)
    private EnumAvatar avatar;

    @OneToMany(mappedBy = "usuario")
    private List<Conta> contas;

    @Column(name = "senha_hash", nullable = false)
    private String senhaHash;


    public Usuario() {
        this.avatar = EnumAvatar.valueOf("GUARDIAN_HERO");
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

    public EnumAvatar getAvatar() {
        return avatar;
    }

    public void setAvatar(EnumAvatar avatar) {
        if (avatar == null) {
            throw new IllegalArgumentException("O avatar não pode ser nulo.");
        }
        this.avatar = avatar;
    }

    public List<Conta> getContas(){
        return this.contas;
    }

    public void setContas(List<Conta> contas){
        this.contas = contas;
    }

    public String getSenhaHash() {
        return senhaHash;
    }

    public void setSenhaHash(String senhaHash) {
        this.senhaHash = senhaHash;
    }
}