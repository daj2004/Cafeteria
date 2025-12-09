package org.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Usuarios {

    @Id
    @GeneratedValue

    public long identificador;
    public String nombre;
    public String cedula;

    @ManyToOne
    @JoinColumn(name = "tipousuario_id")

    private TiposDeUsuario tipoDeUsuario;

    public String limitedecredito;
    public String fechaDeRegistro;
    public String estado;
}
