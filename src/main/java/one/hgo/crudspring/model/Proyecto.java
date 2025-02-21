package one.hgo.crudspring.model;

import jakarta.persistence.*;
import lombok.*;
import one.hgo.crudspring.enums.ProyectoStatus;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Proyecto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String descripcion;
    private Date fecha_inicio;

    @Enumerated(EnumType.STRING)
    private ProyectoStatus estado;

    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tarea> tareas;
}
