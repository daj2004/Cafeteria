package org.resources;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.models.TiposDeUsuario;

import java.util.List;

@Path("/api/usuario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuariosRecurso
{

    @Inject
    EntityManager entityManager;


    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public TiposDeUsuario crear(TiposDeUsuario usuario) {
        entityManager.persist(usuario);
        return usuario;
    }


    @GET
    public List<TiposDeUsuario> listar() {
        TypedQuery<TiposDeUsuario> query =
                entityManager.createQuery("SELECT u FROM TiposDeUsuario u", TiposDeUsuario.class);
        return query.getResultList();
    }


    @GET
    @Path("/{id}")
    public TiposDeUsuario obtenerPorId(@PathParam("id") Long id) {
        TiposDeUsuario usuario = entityManager.find(TiposDeUsuario.class, id);
        if (usuario == null) {
            throw new NotFoundException("Usuario con id " + id + " no encontrado");
        }
        return usuario;
    }


    @PUT
    @Path("/{id}")
    @Transactional
    public TiposDeUsuario actualizar(@PathParam("id") Long id, TiposDeUsuario datos) {
        TiposDeUsuario usuario = entityManager.find(TiposDeUsuario.class, id);
        if (usuario == null) {
            throw new NotFoundException("Usuario con id " + id + " no encontrado");
        }
        usuario.descripcion = datos.descripcion;
        usuario.estado = datos.estado;
        return usuario;
    }


    @DELETE
    @Path("/{id}")
    @Transactional
    public void eliminar(@PathParam("id") Long id) {
        TiposDeUsuario usuario = entityManager.find(TiposDeUsuario.class, id);
        if (usuario == null) {
            throw new NotFoundException("Usuario con id " + id + " no encontrado");
        }
        entityManager.remove(usuario);
    }
}
