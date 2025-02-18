package one.hgo.crudspring.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import one.hgo.crudspring.model.enums.Status;

import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String descripcion;
    private Date fecha_inicio;

    @Enumerated(EnumType.STRING)
    private Status estado;

    @OneToMany(mappedBy = "proyecto_id")
    private List<Tarea> tareas;
}
