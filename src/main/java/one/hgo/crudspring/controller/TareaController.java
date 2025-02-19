package one.hgo.crudspring.controller;

import one.hgo.crudspring.dto.TareaDTO;
import one.hgo.crudspring.model.Tarea;
import one.hgo.crudspring.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tareas")
public class TareaController {
    @Autowired
    private TareaService tareaService;

    @PostMapping()
    public ResponseEntity<Tarea> create(@RequestBody TareaDTO tareaDto) {
        Tarea tarea = this.tareaService.create(tareaDto);
        return ResponseEntity.ok(tarea);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@RequestParam("id") Long id) {
        this.tareaService.deleteById(id);
        return ResponseEntity.ok("Tarea Eliminado");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Tarea> update(@RequestBody TareaDTO tareaDto, @PathVariable("id") Long id) {
        Tarea tarea = this.tareaService.update(id, tareaDto);
        return ResponseEntity.ok(tarea);
    }
}
