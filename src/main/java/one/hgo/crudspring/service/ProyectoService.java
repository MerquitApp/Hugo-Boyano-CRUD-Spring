package one.hgo.crudspring.service;

import one.hgo.crudspring.dto.ProyectoDTO;
import one.hgo.crudspring.enums.ProyectoStatus;
import one.hgo.crudspring.model.Proyecto;
import one.hgo.crudspring.repository.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProyectoService {
    @Autowired
    private ProyectoRepository proyectoRepository;

    public Proyecto create(ProyectoDTO proyectoDTO) {
        Proyecto proyecto = new Proyecto();
        proyecto.setName(proyectoDTO.getName());
        proyecto.setDescripcion(proyectoDTO.getDescripcion());
        proyecto.setFecha_inicio(proyectoDTO.getFecha_inicio());

        if (proyectoDTO.getEstado() == null) {
            proyecto.setEstado(ProyectoStatus.ACTIVO);
        } else {
            proyecto.setEstado(proyectoDTO.getEstado());
        }

        return proyectoRepository.save(proyecto);
    }

    public void update(Long id, ProyectoDTO proyectoDTO) {
        Proyecto proyecto = this.getById(id);

        if (proyectoDTO.getName() != null) {
            proyecto.setName(proyectoDTO.getName());
        }

        if (proyectoDTO.getDescripcion() != null) {
            proyecto.setDescripcion(proyectoDTO.getDescripcion());
        }

        if (proyectoDTO.getFecha_inicio() != null) {
            proyecto.setFecha_inicio(proyectoDTO.getFecha_inicio());
        }

        if (proyectoDTO.getEstado() != null) {
            proyecto.setEstado(proyectoDTO.getEstado());
        }

        proyectoRepository.save(proyecto);
    }

    public List<Proyecto> getAll() {
        return proyectoRepository.findAll();
    }

    public Proyecto getById(Long id) {
        return proyectoRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        proyectoRepository.deleteById(id);
    }
}
