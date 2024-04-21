package co.edu.unicauca.asae.taller_jpa_asst.taller_jpa_asst.models;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
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
    private Integer idpregunta;

    @Column(nullable = false, length = 30)
    private String enunciado;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "objPregunta")
	private List<Respuesta> respuestas;

    @OneToOne
    @JoinColumn(name="idtippregunta")
    private TipoPregunta objTipoPregunta;

    @ManyToOne
    @JoinColumn(name = "idCuestionario", nullable = false)
    private Cuestionario objCuestionario;


}