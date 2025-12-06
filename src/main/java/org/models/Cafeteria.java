package org.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Cafeteria {

    @Id
    @GeneratedValue

    public long identificador;
    public String descripcion;
    @ManyToOne
    @JoinColumn(name = "campus_id")

    private Campus campus;
    public String encargado;
    public String estado;


}
