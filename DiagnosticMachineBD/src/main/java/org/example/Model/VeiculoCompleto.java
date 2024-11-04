package org.example.Model;

public class VeiculoCompleto {
    // aqui nesse model irei recuperar uma junção de tabelas que fiz com uma seleção (select)
    private String placa;
    private int ano;
    private String marca;
    private String modelo;

    public VeiculoCompleto(){}

    public VeiculoCompleto(String placa, int ano, String marca, String modelo) {
        this.placa = placa;
        this.ano = ano;
        this.marca = marca;
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
}
