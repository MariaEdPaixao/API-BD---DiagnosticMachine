package org.example.Service;

import org.example.DAO.UsuarioDAO;
import org.example.Model.FotoRequest;
import org.example.Model.Usuario;
import org.example.Model.UsuarioUpdate;

import java.sql.SQLException;
import java.util.Objects;

public class UsuarioService {

    UsuarioDAO ud = new UsuarioDAO();

    public String loginUsuario(Usuario usuario) {
        String senhaBD = ud.buscarSenhaporEmail(usuario.getEmail());

        if (senhaBD == null) {
            return "{\"message\":\"Usuário não encontrado!\"}";
        }

        if (!senhaBD.equals(usuario.getSenha())) {
            return "{\"message\":\"Senha incorreta\"}";
        }

        return "{\"message\":\"Login obteve sucesso\"}";
    }

    public String cadastrarUsuario(Usuario usuario) throws SQLException {
        boolean emailExiste = ud.existeUsuario(usuario.getEmail());
        boolean telefoneExiste = ud.existeTelefone(usuario.getTelefone());

        if (emailExiste) {
            return "{\"message\":\"Esse email já foi cadastrado!\"}";
        }

        if (telefoneExiste) {
            return "{\"message\":\"Esse telefone já foi cadastrado!\"}";
        }

        ud.inserirUsuarioBase(usuario);
        return "{\"message\":\"Cadastro obteve sucesso\"}";
    }

    public String buscaIdUsuarioPorEmail(String email) {
        int idUsuario = ud.buscarIdPorEmail(email);
        return idUsuario != 0 ? String.valueOf(idUsuario) : "{\"message\":\"Não existe um id correspondente a esse e-mail!\"}";
    }

    public Usuario exibirUsuarioPorEmail(String email) {
        return ud.buscarUsuarioBasePorEmail(email);
    }

    public String buscarFotoPorEmail(String email) throws SQLException {
        String foto = ud.buscarFotoBaseUsuario(email);
        return foto != null ? "{\"foto\":\"" + foto + "\"}" : "{\"foto\":\"\"}";
    }

    public String atualizarDadosUsuario(String email, UsuarioUpdate up) {
        String telefoneAtual = ud.buscaTelefone(email);

        if (!Objects.equals(up.getTelefone(), telefoneAtual) && ud.existeTelefone(up.getTelefone())) {
            return "{\"message\":\"Esse telefone já foi cadastrado para outro usuário!\"}";
        }

        boolean atualizou = ud.atualizaUsuario(up, email);
        return atualizou ? "{\"message\":\"Usuário atualizado com sucesso!\"}" : "{\"message\":\"Dados não atualizados.\"}";
    }

    public String atualizarFotoUsuario(String email, String urlFoto) {
        if (urlFoto == null || urlFoto.isEmpty()) {
            return "{\"message\":\"URL da foto é obrigatória.\"}";
        }

        boolean atualizouFoto = ud.atualizaFoto(email, urlFoto);
        return atualizouFoto ? "{\"message\":\"Foto atualizada com sucesso!\"}" : "{\"message\":\"Usuário não encontrado ou não foi possível atualizar a foto.\"}";
    }

    public String deletarUsuario(int idUser) {
        boolean deletado = ud.deletarUsuario(idUser);
        return deletado ? "{\"message\":\"Usuário deletado com sucesso!\"}" : "{\"message\":\"Não foi possível excluir o usuário\"}";
    }
}
