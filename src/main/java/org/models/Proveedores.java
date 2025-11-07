package org.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Proveedores {
    @Id
    @GeneratedValue

    public long identificador;
    public String nombreComercial;
    public String RNC;
    public String fechaDeRegistro;
    public String estado;

}
