package org.example.Service;

import org.example.DAO.VeiculoDAO;
import org.example.Model.ModeloMarca;
import org.example.Model.Veiculo;
import org.example.Model.VeiculoCompleto;

import java.sql.SQLException;
import java.util.List;

public class VeiculoService {

    private VeiculoDAO vd = new VeiculoDAO();

    public List<ModeloMarca> buscarModeloMarcaBase() {
        return vd.buscarModeloMarcaBase();
    }

    public void cadastrarVeiculo(Veiculo veiculo) throws SQLException {
        if (existePlaca(veiculo.getPlaca())) {
            throw new IllegalArgumentException("Essa placa já está cadastrada!");
        }
        vd.inserirVeiculoBase(veiculo);
    }

    public boolean existePlaca(String placa) {
        return vd.existePlaca(placa);
    }

    public int buscarIdPorPlaca(String placa) {
        int idVeiculo = vd.buscarIdPorPlaca(placa);
        if (idVeiculo == 0) {
            throw new IllegalArgumentException("Não existe um id correspondente a essa placa!");
        }
        return idVeiculo;
    }

    public void associarUsuarioVeiculo(int idUser, int idVeiculo) throws SQLException {
        vd.relacionandoUsuarioVeiculo(idUser, idVeiculo);
    }

    public VeiculoCompleto exibirVeiculoDoUsuario(int idUsuario) {
        VeiculoCompleto vc = vd.exibirVeiculoDoUsuario(idUsuario);
        if (vc == null) {
            throw new IllegalArgumentException("Veículo não encontrado para esse usuário");
        }
        return vc;
    }
}
