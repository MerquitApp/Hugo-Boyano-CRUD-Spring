package one.hgo.crudspring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import one.hgo.crudspring.enums.TareaStatus;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descripcion;
    private Date fecha_limite;

    @Enumerated(EnumType.STRING)
    private TareaStatus estado;

    @JsonIgnore
    @ManyToOne
    private Proyecto proyecto;
}
