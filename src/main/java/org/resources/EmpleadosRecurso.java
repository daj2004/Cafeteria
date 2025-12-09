package org.resources;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.models.Empleados;

import java.util.List;

@Path("/api/empleados")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)


public class EmpleadosRecurso {


    @Inject
    EntityManager entityManager;


    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Empleados crear(Empleados empleados) {
        entityManager.persist(empleados);
        return empleados;
    }


    @GET
    public List<Empleados> listar() {
        TypedQuery<Empleados> query =
                entityManager.createQuery("SELECT u FROM Empleados u", Empleados.class);
        return query.getResultList();
    }


    @GET
    @Path("/{id}")
    public Empleados obtenerPorId(@PathParam("id") Long id) {
        Empleados empleados = entityManager.find(Empleados.class, id);
        if (empleados == null) {
            throw new NotFoundException("empleados con id " + id + " no encontrado");
        }
        return empleados;
    }


    @PUT
    @Path("/{id}")
    @Transactional
    public Empleados actualizar(@PathParam("id") Long id, Empleados datos) {

        Empleados empleados = entityManager.find(Empleados.class, id);
        if (empleados == null) {
            throw new NotFoundException("empleados con id " + id + " no encontrado");
        }

        empleados.setNombre(datos.getNombre());
        empleados.setCedula(datos.getCedula());
        empleados.setTandalaboral(datos.getTandalaboral());
        empleados.setPorcientodecomision(datos.getPorcientodecomision());
        empleados.setFechaingreso(datos.getFechaingreso());
        empleados.setEstado(datos.getEstado());


        return empleados;
    }


    @DELETE
    @Path("/{id}")
    @Transactional
    public void eliminar(@PathParam("id") Long id) {
        Empleados empleados = entityManager.find(Empleados.class, id);
        if (empleados == null) {
            throw new NotFoundException("empleados con id " + id + " no encontrado");
        }

        entityManager.remove(empleados);
    }
}
