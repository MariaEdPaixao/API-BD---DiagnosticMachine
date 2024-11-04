package org.example.DAO;

import org.example.Model.ModeloMarca;
import org.example.Model.Veiculo;
import org.example.Model.VeiculoCompleto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDAO implements InterfaceVeiculoDAO {

    // criando conexão
    Connection con = null;
    public VeiculoDAO() {
        con = ConexaoBD.criarConexao();
    }
    public void gerarConexao() {
        con = ConexaoBD.criarConexao();
    }

    public List<ModeloMarca> buscarModeloMarcaBase() {
        List<ModeloMarca> l = new ArrayList<ModeloMarca>();

        Statement st = null;
        ResultSet rs = null;
        try {
            st = con.createStatement();
            rs = st.executeQuery("select * from tbl_modelo_marca");

            while (rs.next()) {
                ModeloMarca c = new ModeloMarca(rs.getString(2), rs.getString(3),rs.getInt(1));
                l.add(c);
            }

            st.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro a exibir marcas e modelos");
        }

        return l;
    }

    public void inserirVeiculoBase(Veiculo veiculo) {
        try {
            PreparedStatement st = con.prepareStatement("INSERT INTO tbl_veiculos (ano, placa, id_modelo_marca) VALUES (?, ?, ?)");

            st.setInt(1, veiculo.getAno());
            st.setString(2, veiculo.getPlaca());
            st.setInt(3, veiculo.getId_modelo_marca());

            st.executeUpdate();

            st.close();

        } catch (SQLException e) {
            System.out.println("Deu ruim na inserção");
        }

    }

    public boolean existePlaca(String placa) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        boolean retorno = false;

        try {
            pst = con.prepareStatement("SELECT 1 FROM tbl_veiculos WHERE placa = ?");
            pst.setString(1, placa);

            rs = pst.executeQuery();

            // Se o ResultSet tiver um próximo registro, a placa existe
            if (rs.next()) {
                retorno = true;  // A placa foi encontrada, então retorno deve ser true
            }

            rs.close();
            pst.close();
        } catch (SQLException e) {
            System.out.println("Erro ao verificar a existência da placa: " + e.getMessage());
        }

        // Retorna true se a placa foi encontrada, false caso contrário
        return retorno;
    }

    public int buscarIdPorPlaca(String placa) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        int idPlaca = 0;

        try {
            pst = con.prepareStatement("SELECT id_veiculos FROM tbl_veiculos WHERE placa = ?");
            pst.setString(1, placa);

            rs = pst.executeQuery();

            if (rs.next()) {
                idPlaca = rs.getInt("id_veiculos");
            }

            rs.close();
            pst.close();
        } catch (SQLException e) {
            System.out.println("Erro ao verificar a existência do usuário: " + e.getMessage());
        }

        return idPlaca;
    }

    public void relacionandoUsuarioVeiculo(int idUser, int idVeiculo) {
        Statement st = null;
        try {
            st = con.createStatement();

            st.executeUpdate("INSERT INTO tbl_veiculos_usuarios (id_veiculos, id_usuarios) VALUES ('"+idVeiculo+"', ' "+idUser+"')");

            st.close();
        } catch (SQLException e) {
            System.out.println("Deu ruim na inserção");
        }
    }

    public VeiculoCompleto exibirVeiculoDoUsuario(int idUsuario) {
        VeiculoCompleto vc = new VeiculoCompleto();
        String sql = "SELECT v.placa, v.ano, mm.marca, mm.modelo " +
                "FROM tbl_veiculos v " +
                "JOIN tbl_modelo_marca mm ON v.id_modelo_marca = mm.id_modelo_marca " +
                "JOIN tbl_veiculos_usuarios vu ON v.id_veiculos = vu.id_veiculos " +
                "WHERE vu.id_usuarios = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idUsuario);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                vc = new VeiculoCompleto(rs.getString("placa"), rs.getInt("ano"), rs.getString("marca"), rs.getString("modelo"));
            }else{
                vc = null;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao tentar exibir informações do veículo: " + e.getMessage());
        }
        return vc;
    }

}
