package org.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Empleados {

    @Id
    @GeneratedValue

    public long identificador;
    public String nombre;
    public String cedula;
    public String tandalaboral;
    public double porcientodecomision;
    public String fechaingreso;
    public String estado;
}
