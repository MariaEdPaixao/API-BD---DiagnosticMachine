package org.example.Model;

public class Veiculo {

    private int ano;
    private String placa;
    private int id_modelo_marca;

    public Veiculo(){}
    public Veiculo(int ano, String placa) {
        this.ano = ano;
        this.placa = placa;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getId_modelo_marca() {
        return id_modelo_marca;
    }

    public void setId_modelo_marca(int id_modelo_marca) {
        this.id_modelo_marca = id_modelo_marca;
    }
}
