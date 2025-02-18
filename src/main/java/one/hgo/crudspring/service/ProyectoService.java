package one.hgo.crudspring.service;

import one.hgo.crudspring.repository.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProyectoService {
    @Autowired
    private ProyectoRepository proyectoRepository;
}
