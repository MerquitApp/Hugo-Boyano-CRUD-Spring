package one.hgo.crudspring.service;

import one.hgo.crudspring.dto.TareaDTO;
import one.hgo.crudspring.enums.TareaStatus;
import one.hgo.crudspring.model.Proyecto;
import one.hgo.crudspring.model.Tarea;
import one.hgo.crudspring.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TareaService {
    @Autowired
    private TareaRepository tareaRepository;

    @Autowired
    private ProyectoService proyectoService;

    public List<Tarea> getTareasByProyectoId(Long proyectoId) {
        return tareaRepository.findByProyecto_Id(proyectoId);
    }

    public Tarea create(TareaDTO tareaDto) {
        Tarea tarea = new Tarea();

        Proyecto proyecto = proyectoService.getById(tareaDto.getProyecto_id());

        tarea.setTitulo(tareaDto.getTitulo());
        tarea.setDescripcion(tareaDto.getDescripcion());
        tarea.setFecha_limite(tareaDto.getFecha_limite());
        tarea.setEstado(tareaDto.getEstado());
        tarea.setProyecto(proyecto);

        return tareaRepository.save(tarea);
    }

    public Tarea getById(Long id) {
        return tareaRepository.findById(id).orElse(null);
    }

    public Tarea update(Long id, TareaDTO tareaDto) {
        Tarea tarea = this.getById(id);
        Proyecto proyecto = tarea.getProyecto();

        if (tareaDto.getTitulo() != null) {
            tarea.setTitulo(tareaDto.getTitulo());
        }

        if (tareaDto.getDescripcion() != null) {
            tarea.setDescripcion(tareaDto.getDescripcion());
        }

        if (tareaDto.getFecha_limite() != null) {
            tarea.setFecha_limite(tareaDto.getFecha_limite());
        }

        if (tareaDto.getEstado() != null) {
            tarea.setEstado(tareaDto.getEstado());
        }

        if (tareaDto.getProyecto_id() != null) {
            Long newProyectoId = tareaDto.getProyecto_id();
            proyecto.setId(newProyectoId);
        }

        return tareaRepository.save(tarea);
    }

    public void deleteById(Long id) {
        tareaRepository.deleteById(id);
    }
}
