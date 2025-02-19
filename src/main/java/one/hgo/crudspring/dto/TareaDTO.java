package one.hgo.crudspring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import one.hgo.crudspring.enums.TareaStatus;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TareaDTO {
    private String titulo;
    private String descripcion;
    private Date fecha_limite;
    private TareaStatus estado;
    private Long proyecto_id;
}
