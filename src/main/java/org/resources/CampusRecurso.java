package org.resources;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.models.Campus;

import java.util.List;

@Path("/api/campus")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CampusRecurso {

    @Inject
    EntityManager entityManager;

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Campus crear(Campus campus){
        entityManager.persist(campus);
        return campus;
    }

    @GET
    public List<Campus> listar() {
        TypedQuery<Campus> query =
                entityManager.createQuery("SELECT u FROM Campus u", Campus.class);
        return query.getResultList();
    }

    @GET
    @Path("/{id}")
    public Campus obtenerPorId(@PathParam("id") Long id) {
        Campus campus = entityManager.find(Campus.class, id);
        if (campus == null) {
            throw new NotFoundException("Campus con id " + id + " no encontrado");
        }
        return campus;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Campus actualizar(@PathParam("id") Long id, Campus datos) {
        Campus campus = entityManager.find(Campus.class, id);
        if (campus == null) {
            throw new NotFoundException("campus con id " + id + " no encontrado");
        }
        campus.descripcion = datos.descripcion;
        campus.estado = datos.estado;
        return campus;
    }


    @DELETE
    @Path("/{id}")
    @Transactional
    public void eliminar(@PathParam("id") Long id) {
        Campus campus = entityManager.find(Campus.class, id);
        if (campus == null) {
            throw new NotFoundException("campus con id " + id + " no encontrado");
        }
        entityManager.remove(campus);
    }
}
