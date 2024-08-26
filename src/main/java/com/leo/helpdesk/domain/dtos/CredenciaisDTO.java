package com.leo.helpdesk.domain.dtos;

public class CredenciaisDTO {

    // Incluindo atributos email e senha
    private String email;
    private String senha;

    // Gerando os Getters e Setters de email e senha
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}