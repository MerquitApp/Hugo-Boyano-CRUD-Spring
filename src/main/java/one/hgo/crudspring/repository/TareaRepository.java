package one.hgo.crudspring.repository;

import one.hgo.crudspring.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TareaRepository extends JpaRepository<Tarea, Long> {
    List<Tarea> findByProyecto_Id(Long proyectoId);
}
