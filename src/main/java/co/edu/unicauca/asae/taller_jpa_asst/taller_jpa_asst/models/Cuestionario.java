package co.edu.unicauca.asae.taller_jpa_asst.taller_jpa_asst.models;

import lombok.*;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@Table(name = "Cuestionarios")

public class Cuestionario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idcuestionario;

    @Column( nullable = false, length = 30)
    private String titulo;

    @Column( nullable = false, length = 30)
    private String descripcion;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER, mappedBy = "objCuestionario")
    private List<Pregunta> preguntas;
    public Cuestionario(){
        this.preguntas = new ArrayList<>();
    }

}
