package org.resources;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.models.Cafeteria;

import java.util.List;

@Path("/api/cafeteria")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class CafeteriaRecurso {
    @Inject
    EntityManager entityManager;

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Cafeteria crear(Cafeteria model){
        entityManager.persist(model);
        return model;
    }

    @GET
    public List<Cafeteria> listar() {
        TypedQuery<Cafeteria> query =
                entityManager.createQuery("SELECT u FROM Cafeteria u", Cafeteria.class);
        return query.getResultList();
    }

    @GET
    @Path("/{id}")
    public Cafeteria obtenerPorId(@PathParam("id") Long id) {
        Cafeteria Cafeteria = entityManager.find(Cafeteria.class, id);
        if (Cafeteria == null) {
            throw new NotFoundException("Cafeteria con id " + id + " no encontrado");
        }
        return Cafeteria;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Cafeteria actualizar(@PathParam("id") Long id, Cafeteria datos) {
        Cafeteria Cafeteria = entityManager.find(Cafeteria.class, id);
        if (Cafeteria == null) {
            throw new NotFoundException("Cafeteria con id " + id + " no encontrado");
        }
        Cafeteria.descripcion = datos.descripcion;
        Cafeteria.estado = datos.estado;
        Cafeteria.encargado = datos.encargado;

        return Cafeteria;
    }


    @DELETE
    @Path("/{id}")
    @Transactional
    public void eliminar(@PathParam("id") Long id) {
        Cafeteria Cafeteria = entityManager.find(Cafeteria.class, id);
        if (Cafeteria == null) {
            throw new NotFoundException("Cafeteria con id " + id + " no encontrado");
        }
        entityManager.remove(Cafeteria);
    }
}
