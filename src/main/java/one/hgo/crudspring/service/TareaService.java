package one.hgo.crudspring.service;

import one.hgo.crudspring.model.Tarea;
import one.hgo.crudspring.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TareaService {
    @Autowired
    private TareaRepository tareaRepository;

    public List<Tarea> getTareasByProyectoId(Long proyectoId) {
        return tareaRepository.findTareasByProyectoId(proyectoId);
    }

    public Tarea create(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    public Tarea update(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    public void delete(Tarea tarea) {
        tareaRepository.delete(tarea);
    }
}
