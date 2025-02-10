package com.restapi.apirest;


import com.restapi.apirest.entity.Visita;
import com.restapi.apirest.repository.VisitaRepository;
import com.restapi.apirest.service.VisitaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VisitaServiceTest {

    @Mock
    private VisitaRepository visitaRepository;

    @InjectMocks
    private VisitaService visitaService;

    @Test
    public void testObtenerTodas() {
        // Arrange: Preparamos algunas visitas de ejemplo
        // 1L significa el número 1 de tipo long.
        Visita visita1 = new Visita();
        visita1.setId(1L);
        Visita visita2 = new Visita();
        visita2.setId(2L);
        List<Visita> visitas = Arrays.asList(visita1, visita2);

        when(visitaRepository.findAll()).thenReturn(visitas);

        // Act: Llamamos al método del servicio
        List<Visita> resultado = visitaService.obtenerTodas();

        // Assert: Verificamos que se devuelven las visitas y que se llamó al repositorio
        assertThat(resultado).hasSize(2);
        verify(visitaRepository, times(1)).findAll();
    }

    @Test
    public void testObtenerPorId() {
        // Arrange: Preparamos una visita de ejemplo
        Visita visita = new Visita();
        visita.setId(1L);

        when(visitaRepository.findById(1L)).thenReturn(Optional.of(visita));

        // Act
        Optional<Visita> resultado = visitaService.obtenerPorId(1L);

        // Assert
        assertThat(resultado).isPresent();
        assertThat(resultado.get().getId()).isEqualTo(1L);
        verify(visitaRepository, times(1)).findById(1L);
    }

    @Test
    public void testGuardar() {
        // Arrange: Creamos una visita sin ID (como vendría en el request)
        Visita visita = new Visita();
        // Configura los atributos necesarios de 'visita'

        // Simulamos la visita guardada, donde se asigna el ID
        Visita visitaGuardada = new Visita();
        visitaGuardada.setId(1L);
        // Configura otros atributos si es necesario

        when(visitaRepository.save(any(Visita.class))).thenReturn(visitaGuardada);

        // Act
        Visita resultado = visitaService.guardar(visita);

        // Assert
        assertThat(resultado.getId()).isEqualTo(1L);
        verify(visitaRepository, times(1)).save(visita);
    }

    @Test
    public void testEliminar() {
        // Act: Llamamos al método de eliminación
        visitaService.eliminar(1L);

        // Assert: Verificamos que se llamó al método deleteById del repositorio
        verify(visitaRepository, times(1)).deleteById(1L);
    }
}
