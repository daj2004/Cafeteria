package org.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Articulos {

    @Id
    @GeneratedValue
    private long identificador;

    private String descripcion;
    private String marca;
    private double costo;

    @ManyToOne
    @JoinColumn(name = "proveedor_id") // Foreign Key
    private Proveedores proveedor;

    private int existencia;
    private String estado;
}
