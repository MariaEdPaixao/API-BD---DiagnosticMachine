package org.example.Resources;

import jakarta.json.JsonObject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.Model.FotoRequest;
import org.example.Model.Usuario;
import org.example.Model.UsuarioUpdate;
import org.example.Service.UsuarioService;

import java.sql.SQLException;
import java.util.Map;
import java.util.Objects;

@Path("usuarioresource")
public class UsuarioResource {

    UsuarioService us = new UsuarioService();

    @Path("/login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(Usuario usuario) {
        String result = us.loginUsuario(usuario);
        return result.contains("sucesso") ? Response.ok(result).build() : Response.status(Response.Status.BAD_REQUEST).entity(result).build();
    }

    @Path("/cadastroUsuario")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarUser(Usuario usuario) {
        try {
            String result = us.cadastrarUsuario(usuario);
            return result.contains("sucesso") ? Response.ok(result).build() : Response.status(Response.Status.CONFLICT).entity(result).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"message\":\"Erro ao inserir o usuário!\"}").build();
        }
    }

    @Path("/buscaIdUsuario/{email}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscaIdUsuario(@PathParam("email") String email) {
        String result = us.buscaIdUsuarioPorEmail(email);
        return result.contains("message") ? Response.status(Response.Status.NOT_FOUND).entity(result).build() : Response.ok(result).build();
    }

    @Path("/exibirUsuario/{email}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response exibirUsuario(@PathParam("email") String email) {
        Usuario user = us.exibirUsuarioPorEmail(email);
        return user == null ? Response.status(Response.Status.NOT_FOUND).entity("{\"message\":\"Usuário não encontrado para esse e-mail\"}").build() : Response.ok(user).build();
    }

    @Path("/exibirFoto/{email}")
    @GET
    public Response exibirFoto(@PathParam("email") String email) {
        try {
            String result = us.buscarFotoPorEmail(email);
            return Response.ok(result).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("{\"message\":\"Erro ao buscar a foto do usuário\"}").build();
        }
    }

    @Path("/atualizaDados/{email}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizaDadosUser(@PathParam("email") String email, UsuarioUpdate up) {
        String result = us.atualizarDadosUsuario(email, up);
        return result.contains("sucesso") ? Response.ok(result).build() : Response.status(Response.Status.CONFLICT).entity(result).build();
    }

    @Path("/atualizaFoto/{email}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizaFoto(@PathParam("email") String email, FotoRequest request) {
        String result = us.atualizarFotoUsuario(email, request.getImgURL());
        return result.contains("sucesso") ? Response.ok(result).build() : Response.status(Response.Status.BAD_REQUEST).entity(result).build();
    }

    @Path("/deletarUsuario/{idUser}")
    @DELETE
    public Response deletaUsuario(@PathParam("idUser") int idUser) {
        String result = us.deletarUsuario(idUser);
        return result.contains("sucesso") ? Response.ok(result).build() : Response.status(Response.Status.BAD_REQUEST).entity(result).build();
    }
}
