package one.hgo.crudspring.controller;

import one.hgo.crudspring.dto.ProyectoDTO;
import one.hgo.crudspring.model.Proyecto;
import one.hgo.crudspring.service.ProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/proyectos")
public class ProyectoController {
    @Autowired
    private ProyectoService proyectoService;

    @GetMapping()
    public ResponseEntity<List<Proyecto>> getAll() {
        List<Proyecto> proyectos = this.proyectoService.getAll();
        return ResponseEntity.ok(proyectos);
    }

    @PostMapping()
    public ResponseEntity<Proyecto> create(@RequestBody ProyectoDTO proyectoDTO) {
        Proyecto proyecto = this.proyectoService.create(proyectoDTO);
        return ResponseEntity.ok(proyecto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proyecto> getById(@PathVariable("id") Long id) {
        Proyecto proyecto = this.proyectoService.getById(id);
        return ResponseEntity.ok(proyecto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        this.proyectoService.deleteById(id);
        return ResponseEntity.ok("Proyecto Eliminado");
    }

    @GetMapping("/crear")
    public String crearProyecto(Model model) {
        return "crear-proyecto";
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Proyecto> update(@RequestBody ProyectoDTO proyectoDTO, @PathVariable("id") Long id) {
        Proyecto proyecto = this.proyectoService.update(id, proyectoDTO);
        return ResponseEntity.ok(proyecto);
    }

    @GetMapping("/ver")
    public String verProyectos(Model model) {
        List<Proyecto> proyectos = this.proyectoService.getAll();
        model.addAttribute("proyectos", proyectos);
        return "ver-proyectos";
    }
}
