package org.example.DAO;

import org.example.Model.Usuario;
import org.example.Model.UsuarioUpdate;

import java.sql.SQLException;

public interface InterfaceUserDAO {

    public void gerarConexao();
    public String buscarSenhaporEmail(String email);
    public void inserirUsuarioBase(Usuario usuario);
    public int buscarIdPorEmail(String email);
    public boolean existeUsuario(String email);
    public boolean existeTelefone(String telefone);
    public Usuario buscarUsuarioBasePorEmail(String email);
    public String buscarFotoBaseUsuario(String email) throws SQLException;
    public boolean atualizaFoto(String email, String imgURL);
    public boolean atualizaUsuario(UsuarioUpdate up, String email);
    public String buscaTelefone(String email);
    public boolean deletarUsuario(int idUser);

}
