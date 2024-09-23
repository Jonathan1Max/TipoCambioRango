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
import com.tucambio.tucambioapp.service.TipoCambioService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TipoCambioServiceTest {

    @Test
    public void testConsultarTipoCambio() {
        TipoCambioService servicioMock = Mockito.mock(TipoCambioService.class);
        
        TipoCambioDTO mockDTO1 = new TipoCambioDTO();
        mockDTO1.setFecha("01/01/2024");
        mockDTO1.setVenta(7.65f);
        mockDTO1.setCompra(7.60f);

        TipoCambioDTO mockDTO2 = new TipoCambioDTO();
        mockDTO2.setFecha("02/01/2024");
        mockDTO2.setVenta(7.66f);
        mockDTO2.setCompra(7.61f);

        Mockito.when(servicioMock.consultarTipoCambio("01/01/2024", "02/01/2024"))
               .thenReturn(List.of(mockDTO1, mockDTO2));

        List<TipoCambioDTO> result = servicioMock.consultarTipoCambio("01/01/2024", "02/01/2024");
        assertEquals(2, result.size());
        assertEquals("01/01/2024", result.get(0).getFecha());
        assertEquals(7.65f, result.get(0).getVenta());
        assertEquals(7.60f, result.get(0).getCompra());
    }
}