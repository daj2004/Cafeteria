package org.resources;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.models.Marcas;

import java.util.List;

@Path("/api/marcas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class MarcasRecurso {

    @Inject
    EntityManager entityManager;

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Marcas crear(Marcas model){
        entityManager.persist(model);
        return model;
    }
    @GET
    public List<Marcas> listar() {
        TypedQuery<Marcas> query =
                entityManager.createQuery("SELECT u FROM Marcas u", Marcas.class);
        return query.getResultList();
    }

    @GET
    @Path("/{id}")
    public Marcas obtenerPorId(@PathParam("id") Long id) {
        Marcas Marcas = entityManager.find(Marcas.class, id);
        if (Marcas == null) {
            throw new NotFoundException("Marcas con id " + id + " no encontrado");
        }
        return Marcas;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Marcas actualizar(@PathParam("id") Long id, Marcas datos) {
        Marcas Marcas = entityManager.find(Marcas.class, id);
        if (Marcas == null) {
            throw new NotFoundException("Marcas con id " + id + " no encontrado");
        }
        Marcas.descripcion = datos.descripcion;
        Marcas.estado = datos.estado;
        return Marcas;
    }


    @DELETE
    @Path("/{id}")
    @Transactional
    public void eliminar(@PathParam("id") Long id) {
        Marcas Marcas = entityManager.find(Marcas.class, id);
        if (Marcas == null) {
            throw new NotFoundException("Marcas con id " + id + " no encontrado");
        }
        entityManager.remove(Marcas);
    }
}
