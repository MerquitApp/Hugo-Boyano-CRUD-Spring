package one.hgo.crudspring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import one.hgo.crudspring.enums.ProyectoStatus;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProyectoDTO {
    private String name;
    private String descripcion;
    private Date fecha_inicio;
    private ProyectoStatus estado;
}
