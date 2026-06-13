package br.unitins.topicos1.brincos.resource;

import java.io.IOException;
import java.util.List;

import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;

import br.unitins.topicos1.brincos.dto.BrincoDTO;
import br.unitins.topicos1.brincos.dto.BrincoDTOResponse;
import br.unitins.topicos1.brincos.service.ArquivoDownload;
import br.unitins.topicos1.brincos.service.BrincoFileServiceImpl;
import br.unitins.topicos1.brincos.service.BrincoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.core.Response.Status;

@Path("/brincos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BrincoResource {

    @Inject
    BrincoService service;

    @Inject
    BrincoFileServiceImpl fileService;

    @GET
    public List<BrincoDTOResponse> buscarTodos(
        @QueryParam("page")     @DefaultValue("0")   int page,
        @QueryParam("pageSize") @DefaultValue("9999999") int pageSize
    ) {
        page = Math.max(0, page);
        pageSize = Math.min(Math.max(1, pageSize), 9999999);
        return service.findAll(page, pageSize);
    }

    @GET
    @Path("/count")
    public long total() {
        return service.count();
    }

    @GET
    @Path("/find/{cor}")
    public List<BrincoDTOResponse> buscarPorCor(String cor) {
        return service.findByCor(cor);
    }

    @GET
    @Path("/{id}")
    public BrincoDTOResponse buscarPorId(Long id) {
        return service.findById(id);
    }
    
    //@RolesAllowed("User")
    @POST
    @Transactional
    @RolesAllowed("Admin")
    public BrincoDTOResponse incluir(BrincoDTO dto){
        return service.create(dto);
    }

    //@RolesAllowed("User")
    @PUT
    @Path("/{id}")
    @Transactional
    @RolesAllowed("Admin")
    public void alterar(Long id, BrincoDTO dto){
        service.update(id, dto);
    }

    //@RolesAllowed("User")
    @DELETE
    @Path("/{id}")
    @Transactional
    @RolesAllowed("Admin")
    public void apagar(Long id){
        service.delete(id);
    }

    @GET
    @Path("/image/download/{fid}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response download(@PathParam("fid") String fid) {
        ArquivoDownload download = fileService.download(fid);
        ResponseBuilder response = Response.ok(download.content(), download.contentType());
        response.header("Content-Disposition", "attachment; filename=\"" + download.fileName().replace("\"", "") + "\"");
        return response.build();
    }

    @PATCH
    @Path("/image/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response salvarImagem(
            @RestForm("idProduto") 
            @NotNull(message = "idProduto é obrigatório.")
            @Min(value = 1, message = "idProduto deve ser maior ou igual a 1.")
            Long idProduto,

            @RestForm("file") 
            @NotNull(message = "Arquivo de imagem é obrigatório.")
            FileUpload file) {

        try {
            fileService.salvar(idProduto, file);
            return Response.noContent().build();
        } catch (IOException e) {
            return Response.status(Status.CONFLICT).build();
        }

    }

    @DELETE
    @Path("/image/{fid}")
    public Response removerImagem(@PathParam("fid") String fid) {
        fileService.remover(fid);
        return Response.noContent().build();
    }


}

