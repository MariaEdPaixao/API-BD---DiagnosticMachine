package org.example.DAO;

import org.example.Model.FotoRequest;
import org.example.Model.Usuario;
import org.example.Model.UsuarioUpdate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO implements InterfaceUserDAO{


    //criando conexão
    Connection con = null;
    public UsuarioDAO() {
        con = ConexaoBD.criarConexao();
    }

    public void gerarConexao() {
        con = ConexaoBD.criarConexao();
    }

    public String buscarSenhaporEmail(String email){
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            String sql = "SELECT senha from tbl_usuarios WHERE email = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();

            if(rs.next()){
                return rs.getString("senha");
            }
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Erro ao consultar");
        }
        return null;
    }
    public void inserirUsuarioBase(Usuario usuario) {
        try {
            PreparedStatement st = con.prepareStatement("INSERT INTO tbl_usuarios (email, senha, nome_completo, data_nasc, telefone) VALUES (?, ?, ?,?, ?)");

            // Convertendo String para java.sql.Date
            java.sql.Date sqlDate = java.sql.Date.valueOf(usuario.getData_nasc());

            st.setString(1, usuario.getEmail());
            st.setString(2, usuario.getSenha());
            st.setString(3, usuario.getNome_completo());
            st.setDate(4, sqlDate);
            st.setString(5, usuario.getTelefone());

            st.executeUpdate();

            st.close();

        } catch (SQLException e) {
            System.out.println("Deu ruim na inserção");
        }

    }
    public int buscarIdPorEmail(String email) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        int idUser = 0;

        try {
            pst = con.prepareStatement("SELECT id_usuario FROM tbl_usuarios WHERE email = ?");
            pst.setString(1, email);

            rs = pst.executeQuery();

            if (rs.next()) {
                idUser = rs.getInt("id_usuario");
            }

            rs.close();
            pst.close();
        } catch (SQLException e) {
            System.out.println("Erro ao verificar a existência do usuário: " + e.getMessage());
        }

        return idUser;
    }
    public boolean existeUsuario(String email) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        boolean retorno = false;

        try {
            pst = con.prepareStatement("SELECT 1 FROM tbl_usuarios WHERE email = ?");
            pst.setString(1, email);

            rs = pst.executeQuery();

            if (rs.next()) {
                retorno = true;
            }

            rs.close();
            pst.close();
        } catch (SQLException e) {
            System.out.println("Erro ao verificar a existência do usuário: " + e.getMessage());
        }

        return retorno;
    }
    public boolean existeTelefone(String telefone) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        boolean retorno = false;

        try {
            pst = con.prepareStatement("SELECT 1 FROM tbl_usuarios WHERE telefone = ?");
            pst.setString(1, telefone);

            rs = pst.executeQuery();

            if (rs.next()) {
                retorno = true;
            }

            rs.close();
            pst.close();
        } catch (SQLException e) {
            System.out.println("Erro ao verificar a existência do usuário: " + e.getMessage());
        }

        return retorno;
    }
    public Usuario buscarUsuarioBasePorEmail(String email) {
        Usuario user = new Usuario();
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            pst = con.prepareStatement("select * from tbl_usuarios where email = ?");

            pst.setString(1, email);

            rs = pst.executeQuery();

            if (rs.next()){
                user = new Usuario(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
            }else{
                user = null;
            }

            pst.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Erro ao exibir o cliente");
        }
        return user;
    }
    public String buscarFotoBaseUsuario(String email) throws SQLException {
        String sql = "SELECT foto FROM tbl_usuarios WHERE email = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("foto") != null ? rs.getString("foto") : " ";
                }
            }
        }
        return null;
    }
    public boolean atualizaFoto(String email, String imgURL){
        String sql = "UPDATE tbl_usuarios\n" +
                "SET foto = ?\n" +
                "WHERE email = ?\n";
        boolean retorno = false;


        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, imgURL);
            ps.setString(2, email);

            // Executa a atualização
            int rowsUpdated = ps.executeUpdate();

            if (rowsUpdated > 0) {
                retorno = true;
            }

            ps.close();
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Erro ao atualizar o usuário!, ");
        }
        return retorno;
    }
    public boolean atualizaUsuario(UsuarioUpdate up, String email){
        String sql = "UPDATE tbl_usuarios\n" +
                "SET nome_completo = ?, telefone = ?, data_nasc = ?\n" +
                "WHERE email = ?\n";
        boolean retorno = false;

        // Convertendo String para java.sql.Date
        java.sql.Date sqlDate = java.sql.Date.valueOf(up.getData_nasc());

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, up.getNome_completo());
            ps.setString(2, up.getTelefone());
            ps.setDate(3, sqlDate);
            ps.setString(4, email);

            // Executa a atualização
            int rowsUpdated = ps.executeUpdate();

            if (rowsUpdated > 0) {
                retorno = true;
            }

        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Erro ao atualizar o usuário!, ");
        }
        return retorno;
    }
    public String buscaTelefone(String email){
        String telefone = "";
         try{
             PreparedStatement ps = con.prepareStatement("SELECT telefone FROM tbl_usuarios WHERE email = ?");
             ps.setString(1, email);

             ResultSet rs = ps.executeQuery();

             if(rs.next()){
                 telefone = rs.getString(1);
             }
             rs.close();
             ps.close();
         }catch (SQLException e){
             e.printStackTrace();
         }
         return telefone;
    }
    public boolean deletarUsuario(int idUser) {
        boolean retorno = false;
        int idVeiculo = 0;

        try {
            // Seleciona o veículo associado ao usuário
            PreparedStatement ps1 = con.prepareStatement("SELECT id_veiculos FROM tbl_veiculos_usuarios WHERE id_usuarios = ?");
            ps1.setInt(1, idUser);
            ResultSet rs = ps1.executeQuery();

            if (rs.next()) {
                idVeiculo = rs.getInt("id_veiculos");
            }

            // Fechando ResultSet e PreparedStatement
            rs.close();
            ps1.close();

            // Deletar a associação veículo-usuário
            PreparedStatement ex1 = con.prepareStatement("DELETE FROM tbl_veiculos_usuarios WHERE id_usuarios = ?");
            ex1.setInt(1, idUser);
            ex1.executeUpdate();
            ex1.close();

            // Deletar o veículo associado
            PreparedStatement ex2 = con.prepareStatement("DELETE FROM tbl_veiculos WHERE id_veiculos = ?");
            ex2.setInt(1, idVeiculo);
            ex2.executeUpdate();
            ex2.close();

            // Deletar o usuário
            PreparedStatement ex3 = con.prepareStatement("DELETE FROM tbl_usuarios WHERE id_usuario = ?");
            ex3.setInt(1, idUser);
            int rowsDelete = ex3.executeUpdate();
            ex3.close();

            // Verifica se o usuário foi excluído
            if (rowsDelete > 0) {
                retorno = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();  // Imprime o erro para facilitar a depuração
        }

        return retorno;
    }

}
