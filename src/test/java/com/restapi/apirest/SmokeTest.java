package com.restapi.apirest;

import static org.assertj.core.api.Assertions.assertThat;

import com.restapi.apirest.controller.VisitaController;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//Test para afirmar q exite el controlador y no es nulo
@SpringBootTest
class SmokeTest {

    @Autowired
    private VisitaController controller;

    @Test
    void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }
}
