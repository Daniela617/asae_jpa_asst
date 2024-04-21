package co.edu.unicauca.asae.taller_jpa_asst.taller_jpa_asst.models;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Departamentos")
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int iddepartamento;

    @Column(name = "nombredep", nullable = false, length = 30)
    private String nombre;

    @Column( nullable = false, length = 30)
    private String descripcion;
    

}