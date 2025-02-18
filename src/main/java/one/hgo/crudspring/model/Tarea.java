package one.hgo.crudspring.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import one.hgo.crudspring.model.enums.Status;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String descripcion;
    private Date fecha_limite;

    @Enumerated(EnumType.STRING)
    private Status estado;

    @ManyToOne
    private Proyecto proyecto_id;
}
