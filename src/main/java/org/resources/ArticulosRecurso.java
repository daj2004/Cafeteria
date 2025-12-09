package org.resources;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.models.Articulos;

import java.util.List;

@Path("/api/articulos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ArticulosRecurso {

    @Inject
    EntityManager entityManager;

    // Crear
    @POST
    @Transactional
    public Articulos crear(Articulos articulo) {
        entityManager.persist(articulo);
        return articulo;
    }

    // Listar
    @GET
    public List<Articulos> listar() {
        TypedQuery<Articulos> query =
                entityManager.createQuery("SELECT a FROM Articulos a", Articulos.class);
        return query.getResultList();
    }

    // Obtener por ID
    @GET
    @Path("/{id}")
    public Articulos obtenerPorId(@PathParam("id") Long id) {
        Articulos articulo = entityManager.find(Articulos.class, id);
        if (articulo == null) {
            throw new NotFoundException("Artículo con id " + id + " no encontrado");
        }
        return articulo;
    }

    // Actualizar
    @PUT
    @Path("/{id}")
    @Transactional
    public Articulos actualizar(@PathParam("id") Long id, Articulos datos) {

        Articulos articulo = entityManager.find(Articulos.class, id);
        if (articulo == null) {
            throw new NotFoundException("Artículo con id " + id + " no encontrado");
        }

        articulo.setDescripcion(datos.getDescripcion());
        articulo.setMarca(datos.getMarca());
        articulo.setCosto(datos.getCosto());
        articulo.setProveedor(datos.getProveedor());
        articulo.setExistencia(datos.getExistencia());
        articulo.setEstado(datos.getEstado());

        return articulo;
    }

    // Eliminar
    @DELETE
    @Path("/{id}")
    @Transactional
    public void eliminar(@PathParam("id") Long id) {
        Articulos articulo = entityManager.find(Articulos.class, id);
        if (articulo == null) {
            throw new NotFoundException("Artículo con id " + id + " no encontrado");
        }
        entityManager.remove(articulo);
    }
}
