package com.tucambio.tucambioapp;

import com.tucambio.tucambioapp.Dto.TipoCambioDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TipoCambioDTOTest {

    @Test
    public void testTipoCambioDTO() {
        // Crear una instancia de TipoCambioDTO
        TipoCambioDTO tipoCambioDTO = new TipoCambioDTO("2024-09-01", 8.0f, 7.5f, "2024-09-30", 8.5f, 8.0f);
        
        // Verificar que los valores se establecieron correctamente
        assertEquals("2024-09-01", tipoCambioDTO.getFechaInit());
        assertEquals(8.0f, tipoCambioDTO.getVentaInit());
        assertEquals(7.5f, tipoCambioDTO.getCompraInit());
        assertEquals("2024-09-30", tipoCambioDTO.getFechaFin());
        assertEquals(8.5f, tipoCambioDTO.getVentaFin());
        assertEquals(8.0f, tipoCambioDTO.getCompraFin());
    }
}
