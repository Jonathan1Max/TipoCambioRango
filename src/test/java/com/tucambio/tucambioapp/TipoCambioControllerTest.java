/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tucambio.tucambioapp;

/**
 *
 * @author J MAX
 */
import com.tucambio.tucambioapp.controller.TipoCambioController;
import com.tucambio.tucambioapp.Dto.TipoCambioDTO;
import com.tucambio.tucambioapp.service.TipoCambioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TipoCambioControllerTest {

    @InjectMocks
    private TipoCambioController tipoCambioController;

    @Mock
    private TipoCambioService tipoCambioService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testObtenerTipoCambioRango_ValidDates() {
        // Datos de prueba
        String fechaInicio = "01/01/2024";
        String fechaFin = "02/01/2024";

        TipoCambioDTO tipoCambio1 = new TipoCambioDTO();
        tipoCambio1.setFecha(fechaInicio);
        tipoCambio1.setVenta(7.65f);
        tipoCambio1.setCompra(7.60f);

        TipoCambioDTO tipoCambio2 = new TipoCambioDTO();
        tipoCambio2.setFecha(fechaFin);
        tipoCambio2.setVenta(7.66f);
        tipoCambio2.setCompra(7.61f);

        List<TipoCambioDTO> listaTipoCambio = Arrays.asList(tipoCambio1, tipoCambio2);

        // Mock del servicio
        when(tipoCambioService.consultarTipoCambio(fechaInicio, fechaFin)).thenReturn(listaTipoCambio);

        // Llamada al método
        List<TipoCambioDTO> result = tipoCambioController.obtenerTipoCambioRango(fechaInicio, fechaFin);

        // Verificación de resultados
        assertEquals(2, result.size());
        assertEquals(fechaInicio, result.get(0).getFecha());
        assertEquals(fechaFin, result.get(1).getFecha());
    }
}