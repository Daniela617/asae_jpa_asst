package co.edu.unicauca.asae.taller_jpa_asst.taller_jpa_asst.models;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Preguntas")
public class Pregunta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idpregunta;

    @Column(nullable = false, length = 30)
    private String enunciado;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "objPregunta")
	private List<Respuesta> respuestas;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "objPregunta")
    private TipoPregunta objTipoPregunta;

    @ManyToOne
    @JoinColumn(name = "idCuestionario", nullable = false)
    private Cuestionario objCuestionario;

}