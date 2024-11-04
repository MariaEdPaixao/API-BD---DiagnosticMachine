package org.example.Model;

public class Usuario {
    private int id;
    private String email;

    private String senha;
    private String nome_completo;
    private String data_nasc;
    private String telefone;

    public Usuario(){}
    public Usuario(int id, String email, String senha, String nome_completo, String data_nasc, String telefone) {
        this.email = email;
        this.senha = senha;
        this.nome_completo = nome_completo;
        this.data_nasc = data_nasc;
        this.telefone = telefone;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getNome_completo() {
        return nome_completo;
    }

    public void setNome_completo(String nome_completo) {
        this.nome_completo = nome_completo;
    }

    public String getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(String data_nasc) {
        this.data_nasc = data_nasc;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

}

