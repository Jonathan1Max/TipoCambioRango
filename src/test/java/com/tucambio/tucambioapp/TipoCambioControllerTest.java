/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tucambio.tucambioapp;

/**
 *
 * @author J MAX
 */
import com.tucambio.tucambioapp.Dto.TipoCambioDTO;
import com.tucambio.tucambioapp.controller.TipoCambioController;
import com.tucambio.tucambioapp.service.TipoCambioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TipoCambioController.class)
public class TipoCambioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TipoCambioService tipoCambioService;

    @Test
    public void testObtenerTipoCambioRango() throws Exception {
        TipoCambioDTO tipoCambio1 = new TipoCambioDTO("Formato de fecha inv\u00e1lido. Use dd/MM/yyyy.");
        tipoCambio1.setFecha("20/09/2024");
        tipoCambio1.setVenta(7.65f);
        tipoCambio1.setCompra(7.60f);

        TipoCambioDTO tipoCambio2 = new TipoCambioDTO("Formato de fecha inv\u00e1lido. Use dd/MM/yyyy.");
        tipoCambio2.setFecha("23/09/2024");
        tipoCambio2.setVenta(7.66f);
        tipoCambio2.setCompra(7.61f);

        List<TipoCambioDTO> resultados = Arrays.asList(tipoCambio1, tipoCambio2);

        when(tipoCambioService.consultarTipoCambio(anyString(), anyString())).thenReturn(resultados);

        mockMvc.perform(get("/tipoCambioRango")
                .param("fecha_ini", "20/09/2024")
                .param("fecha_fin", "23/09/2024")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].fecha").value("20/09/2024"))
                .andExpect(jsonPath("$[0].venta").value(7.65))
                .andExpect(jsonPath("$[0].compra").value(7.60))
                .andExpect(jsonPath("$[1].fecha").value("23/09/2024"))
                .andExpect(jsonPath("$[1].venta").value(7.66))
                .andExpect(jsonPath("$[1].compra").value(7.61));
    }

    @Test
    public void testObtenerTipoCambioRangoFormatoInvalido() throws Exception {
        mockMvc.perform(get("/tipoCambioRango")
                .param("fecha_ini", "invalid-date")
                .param("fecha_fin", "invalid-date"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensaje").value("Formato de fecha inválido. Use dd/MM/yyyy."));
}
}