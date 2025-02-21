package one.hgo.crudspring.controller;

import one.hgo.crudspring.dto.TareaDTO;
import one.hgo.crudspring.model.Proyecto;
import one.hgo.crudspring.model.Tarea;
import one.hgo.crudspring.service.ProyectoService;
import one.hgo.crudspring.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tareas")
public class TareaController {
    @Autowired
    private TareaService tareaService;

    @Autowired
    private ProyectoService proyectoService;

    @PostMapping()
    public ResponseEntity<Tarea> create(@RequestBody TareaDTO tareaDto) {
        Tarea tarea = this.tareaService.create(tareaDto);
        return ResponseEntity.ok(tarea);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        this.tareaService.deleteById(id);
        return ResponseEntity.ok("Tarea Eliminada");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Tarea> update(@RequestBody TareaDTO tareaDto, @PathVariable("id") Long id) {
        Tarea tarea = this.tareaService.update(id, tareaDto);
        return ResponseEntity.ok(tarea);
    }

    @GetMapping("/crear")
    public String crearTarea(Model model) {
        List<Proyecto> proyectos = this.proyectoService.getAll();
        model.addAttribute("proyectos", proyectos);
        return "crear-tarea";
    }

    @GetMapping("/ver")
    public String verTareas(Model model) {
        List<Tarea> tareas = this.tareaService.getAll();
        model.addAttribute("tareas", tareas);
        return "ver-tareas";
    }
}
