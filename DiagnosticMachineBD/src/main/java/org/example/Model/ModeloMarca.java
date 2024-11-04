package org.example.Model;
import java.util.ArrayList;
import java.util.List;

public class ModeloMarca {
    private int idModeloMarca;
    private String modelo;
    private String marca;

    public ModeloMarca(){}

    public ModeloMarca(String marca, String modelo, int idModeloMarca) {
        this.modelo = modelo;
        this.marca = marca;
        this.idModeloMarca = idModeloMarca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getIdModeloMarca() {
        return idModeloMarca;
    }

    public void setIdModeloMarca(int idModeloMarca) {
        this.idModeloMarca = idModeloMarca;
    }
}
