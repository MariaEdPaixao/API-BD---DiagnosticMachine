package org.example.Resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.Model.ModeloMarca;
import org.example.Model.Veiculo;
import org.example.Model.VeiculoCompleto;
import org.example.Service.VeiculoService;

import java.sql.SQLException;
import java.util.List;

@Path("veiculoresource")
public class VeiculoResource {

    private VeiculoService vs = new VeiculoService();

    @Path("/buscarModelosMarcas")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModelosMarcas() {
        List<ModeloMarca> modelosMarcas = vs.buscarModeloMarcaBase();
        return Response.ok(modelosMarcas).build();
    }

    @Path("/cadastroVeiculo")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarVeiculo(Veiculo veiculo) {
        try {
            vs.cadastrarVeiculo(veiculo);
            return Response.ok("{\"message\":\"Veículo cadastrado com sucesso\"}").build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("{\"message\":\"" + e.getMessage() + "\"}")
                    .build();
        } catch (SQLException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"message\":\"Erro ao inserir o veículo!\"}")
                    .build();
        }
    }

    @Path("/buscaIdVeiculo/{placa}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscaIdVeiculo(@PathParam("placa") String placa) {
        try {
            int idVeiculo = vs.buscarIdPorPlaca(placa);
            return Response.ok(idVeiculo).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"message\":\"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @Path("/associacaoUserVeiculo/{idUser}/{idVeiculo}")
    @GET
    public Response associaTabelas(@PathParam("idUser") int idUser, @PathParam("idVeiculo") int idVeiculo) {
        try {
            vs.associarUsuarioVeiculo(idUser, idVeiculo);
            return Response.ok("{\"message\":\"Associação feita com sucesso!\"}").build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("{\"message\":\"" + e.getMessage() + "\"}")
                    .build();
        } catch (SQLException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"message\":\"Erro ao associar o veículo!\"}")
                    .build();
        }
    }

    @Path("/exibirVeiculo/{idUser}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response exibeVeiculo(@PathParam("idUser") int idUser) {
        try {
            VeiculoCompleto vc = vs.exibirVeiculoDoUsuario(idUser);
            return Response.ok(vc).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"message\":\"" + e.getMessage() + "\"}")
                    .build();
        }
    }
}
