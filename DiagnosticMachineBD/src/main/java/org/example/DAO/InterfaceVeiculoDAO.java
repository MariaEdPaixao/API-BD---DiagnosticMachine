package org.example.DAO;

import org.example.Model.ModeloMarca;
import org.example.Model.Veiculo;
import org.example.Model.VeiculoCompleto;

import java.sql.SQLException;
import java.util.List;

public interface InterfaceVeiculoDAO {
    public void gerarConexao();
    public List<ModeloMarca> buscarModeloMarcaBase();
    public void inserirVeiculoBase(Veiculo veiculo) throws SQLException;
    public boolean existePlaca(String placa);
    public int buscarIdPorPlaca(String placa);
    public void relacionandoUsuarioVeiculo(int idUser, int idVeiculo) throws SQLException;
    public VeiculoCompleto exibirVeiculoDoUsuario(int idUsuario);
}
