package com.restapi.apirest.controller;


import com.restapi.apirest.entity.Visita;
import com.restapi.apirest.service.VisitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.wavemaker.commons.MessageResource;
import com.wavemaker.commons.WMRuntimeException;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping(path = "/visitas")
// @CrossOrigin permite que cualquier dominio acceda a tu API.
//Útil en desarrollo, pero en producción es mejor permitir solo ciertos dominios.
//✔ Para una mejor práctica, usa una configuración global con WebMvcConfigurer
//@CrossOrigin(origins = "*")
public class VisitaController {
/*
@Autowired en Spring se usa para inyectar automáticamente dependencias en una clase sin necesidad de inicializarlas manualmente.
 Es parte del mecanismo de Inversión de Control (IoC) y Inyección de Dependencias (DI) que usa Spring.
*/
    @Autowired
    private VisitaService visitaService;


    @GetMapping
    public List<Visita> listar() {
        return visitaService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public Visita obtenerPorId(@PathVariable Long id) {
        return visitaService.obtenerPorId(id).
                orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "La visita con id " + id + " no fue encontrada"
        ));

    }

    @PostMapping
    public Visita crear(@RequestBody Visita visita) {
        return visitaService.guardar(visita);
    }

    @PutMapping("/{id}")
    public Visita actualizar(@PathVariable Long id, @RequestBody Visita visita) {
        visita.setId(id);
        return visitaService.guardar(visita);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        visitaService.eliminar(id);
    }
}
