package org.resources;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.models.Usuarios;
import org.models.TiposDeUsuario;

import java.util.List;

@Path("/api/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuariosRecurso {

    @Inject
    EntityManager entityManager;


    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Usuarios crear(Usuarios usuario) {
        entityManager.persist(usuario);
        return usuario;
    }


    @GET
    public List<Usuarios> listar() {
        TypedQuery<Usuarios> query =
                entityManager.createQuery("SELECT u FROM Usuarios u", Usuarios.class);
        return query.getResultList();
    }


    @GET
    @Path("/{id}")
    public Usuarios obtenerPorId(@PathParam("id") Long id) {
        Usuarios usuario = entityManager.find(Usuarios.class, id);
        if (usuario == null) {
            throw new NotFoundException("Usuario con id " + id + " no encontrado");
        }
        return usuario;
    }


    @PUT
    @Path("/{id}")
    @Transactional
    public Usuarios actualizar(@PathParam("id") Long id, Usuarios datos) {

        Usuarios usuario = entityManager.find(Usuarios.class, id);
        if (usuario == null) {
            throw new NotFoundException("Usuario con id " + id + " no encontrado");
        }

        usuario.setNombre(datos.getNombre());
        usuario.setCedula(datos.getCedula());
        usuario.setLimitedecredito(datos.getLimitedecredito());
        usuario.setFechaDeRegistro(datos.getFechaDeRegistro());
        usuario.setEstado(datos.getEstado());

        // Actualizar tipo de usuario si viene en la petición
        if (datos.getTipoDeUsuario() != null) {
            Long tipoId = datos.getTipoDeUsuario().getIdentificador();
            TiposDeUsuario tipo = entityManager.find(TiposDeUsuario.class, tipoId);

            if (tipo == null) {
                throw new NotFoundException("Tipo de usuario con id " + tipoId + " no existe");
            }

            usuario.setTipoDeUsuario(tipo);
        }

        return usuario;
    }


    @DELETE
    @Path("/{id}")
    @Transactional
    public void eliminar(@PathParam("id") Long id) {
        Usuarios usuario = entityManager.find(Usuarios.class, id);
        if (usuario == null) {
            throw new NotFoundException("Usuario con id " + id + " no encontrado");
        }

        entityManager.remove(usuario);
    }
}
