package org.resources;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.models.Proveedores;

import java.util.List;

public class ProveedoresRecurso {

    @Inject
    EntityManager entityManager;

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Proveedores crear(Proveedores model){
        entityManager.persist(model);
        return model;
    }

    @GET
    public List<Proveedores> listar() {
        TypedQuery<Proveedores> query =
                entityManager.createQuery("SELECT u FROM Proveedores u", Proveedores.class);
        return query.getResultList();
    }

    @GET
    @Path("/{id}")
    public Proveedores obtenerPorId(@PathParam("id") Long id) {
        Proveedores Proveedores = entityManager.find(Proveedores.class, id);
        if (Proveedores == null) {
            throw new NotFoundException("Proveedores con id " + id + " no encontrado");
        }
        return Proveedores;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Proveedores actualizar(@PathParam("id") Long id, Proveedores datos) {
        Proveedores Proveedores = entityManager.find(Proveedores.class, id);
        if (Proveedores == null) {
            throw new NotFoundException("Proveedores con id " + id + " no encontrado");
        }
        Proveedores.nombreComercial = datos.nombreComercial;
        Proveedores.estado = datos.estado;
        Proveedores.RNC = datos.RNC;
        Proveedores.fechaDeRegistro = datos.fechaDeRegistro;

        return Proveedores;
    }


    @DELETE
    @Path("/{id}")
    @Transactional
    public void eliminar(@PathParam("id") Long id) {
        Proveedores Proveedores = entityManager.find(Proveedores.class, id);
        if (Proveedores == null) {
            throw new NotFoundException("Proveedores con id " + id + " no encontrado");
        }
        entityManager.remove(Proveedores);
    }
}
