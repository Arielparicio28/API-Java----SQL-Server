package com.restapi.apirest;


import com.restapi.apirest.controller.VisitaController;
import com.restapi.apirest.entity.Visita;
import com.restapi.apirest.service.VisitaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(VisitaController.class)
public class VisitaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // Permite inyectar el ObjectMapper para convertir objetos a JSON y viceversa
    @Autowired
    private ObjectMapper objectMapper;

    // Simulamos el servicio para evitar llamar a la lógica real
    @MockitoBean
    private VisitaService visitaService;

    @Test
    public void testListar() throws Exception {
        // Arrange: preparamos datos de prueba
        Visita visita1 = new Visita();
        visita1.setId(1L);
        // Configura otros atributos de visita1 si es necesario

        Visita visita2 = new Visita();
        visita2.setId(2L);
        // Configura otros atributos de visita2 si es necesario

        when(visitaService.obtenerTodas()).thenReturn(Arrays.asList(visita1, visita2));

        // Act & Assert: realizamos la petición y verificamos el resultado
        mockMvc.perform(get("/visitas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));
    }

    @Test
    public void testObtenerPorId() throws Exception {
        // Arrange
        Visita visita = new Visita();
        visita.setId(1L);

        when(visitaService.obtenerPorId(anyLong())).thenReturn(Optional.of(visita));

        // Act & Assert
        mockMvc.perform(get("/visitas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testCrear() throws Exception {
        // Arrange: creamos una visita sin ID (como vendría en el request)
        Visita visita = new Visita();


        // Simulamos la visita ya guardada (con ID asignado)
        Visita visitaGuardada = new Visita();
        visitaGuardada.setId(1L);
        // Configura otros atributos si es necesario

        when(visitaService.guardar(any(Visita.class))).thenReturn(visitaGuardada);

        // Act & Assert: convertimos la visita a JSON y enviamos la petición POST
        mockMvc.perform(post("/visitas")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(visita)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testActualizar() throws Exception {
        // Arrange: creamos una visita que se actualizará
        Visita visita = new Visita();
        // Configura atributos de la visita

        // Simulamos la visita actualizada (se espera que tenga el mismo ID)
        Visita visitaActualizada = new Visita();
        visitaActualizada.setId(1L);
        // Configura otros atributos actualizados

        when(visitaService.guardar(any(Visita.class))).thenReturn(visitaActualizada);

        // Act & Assert
        mockMvc.perform(put("/visitas/1")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(visita)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testEliminar() throws Exception {
        // Como el método eliminar no retorna nada, solo verificamos que se llame y retorne status OK
        mockMvc.perform(delete("/visitas/1"))
                .andExpect(status().isOk());
    }
}
