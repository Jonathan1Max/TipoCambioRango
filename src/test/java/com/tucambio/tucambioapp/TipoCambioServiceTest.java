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
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TipoCambioServiceTest {

    private final TipoCambioService tipoCambioService = new TipoCambioService();

    @Test
    public void testConsultarTipoCambio() {
        String fechaInicio = "2024-09-20";
        String fechaFin = "2024-09-23";

        List<TipoCambioDTO> resultado = tipoCambioService.consultarTipoCambio(fechaInicio, fechaFin);

        // Mensajes de salida para verificar el estado
        System.out.println("Resultado de la consulta:");
        for (TipoCambioDTO tipoCambio : resultado) {
            System.out.println("Fecha: " + tipoCambio.getFecha() + ", Venta: " + tipoCambio.getVenta() + ", Compra: " + tipoCambio.getCompra());
        }

        assertNotNull(resultado, "La lista de tipo de cambio no debe ser nula");
        assertEquals(2, resultado.size(), "Se deben retornar dos registros de tipo de cambio");

        // Verificar los valores
        assertEquals("2024-09-20", resultado.get(0).getFecha(), "La fecha de inicio no coincide");
        assertEquals(7.65f, resultado.get(0).getVenta(), "El precio de venta no coincide");
        assertEquals(7.60f, resultado.get(0).getCompra(), "El precio de compra no coincide");

        assertEquals("2024-09-23", resultado.get(1).getFecha(), "La fecha de fin no coincide");
        assertEquals(7.66f, resultado.get(1).getVenta(), "El precio de venta no coincide");
        assertEquals(7.61f, resultado.get(1).getCompra(), "El precio de compra no coincide");
    }
}