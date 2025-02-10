package com.restapi.apirest.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "visitas")
public class Visita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String visitante;
    private String motivo;


    public Visita() {}

    public Visita(String visitante, String motivo) {
        this.visitante = visitante;
        this.motivo = motivo;

    }
//Getters and setters
   public Long getId() { return id; }
      public void setId(Long id) { this.id = id; }

    public String getVisitante() { return visitante; }
    public void setVisitante(String visitante) { this.visitante = visitante; }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }

}
