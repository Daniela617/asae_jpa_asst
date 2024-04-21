package co.edu.unicauca.asae.taller_jpa_asst.taller_jpa_asst.models;


import jakarta.persistence.*;

import lombok.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Telefono")
public class Telefono {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idtelefono;

    @Column(nullable = false, length = 30)
    private String tipotelefono;

    @Column(nullable = false, length = 30)
    private String numero;

    @OneToOne
    @JoinColumn(name="idDocente")
    private Docente objDocente;
    
}