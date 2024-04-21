package co.edu.unicauca.asae.taller_jpa_asst.taller_jpa_asst.models;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TipoPregunta")
public class TipoPregunta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idtippregunta;

    @Column(nullable = false, length = 30)
    private String nombre;

    @Column(nullable = false, length = 30)
    private String descripcion;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "objTipoPregunta")
    private Pregunta objPregunta;
    
}