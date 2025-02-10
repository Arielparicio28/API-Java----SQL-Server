package com.restapi.apirest.service;


import com.restapi.apirest.entity.Visita;
import com.restapi.apirest.repository.VisitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VisitaService {

    @Autowired
    private VisitaRepository visitaRepository;

    public List<Visita> obtenerTodas() {
        return visitaRepository.findAll();
    }

    public Optional<Visita> obtenerPorId(Long id) {
        return visitaRepository.findById(id);
    }

    public Visita guardar(Visita visita) {
        return visitaRepository.save(visita);
    }

    public void eliminar(Long id) {
        visitaRepository.deleteById(id);
    }
}
