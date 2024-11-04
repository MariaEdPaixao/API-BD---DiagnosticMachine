package org.example.Model;


public class UsuarioUpdate {
    private String nome_completo;
    private String data_nasc;
    private String telefone;

    public UsuarioUpdate(){}
    public UsuarioUpdate(String nome_completo, String data_nasc, String telefone) {

        this.nome_completo = nome_completo;
        this.data_nasc = data_nasc;
        this.telefone = telefone;
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

