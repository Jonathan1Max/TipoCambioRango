package com.tucambio.tucambioapp;

/**
 *
 * @author J MAX
 */
import com.tucambio.tucambioapp.service.TipoCambioSoapService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TipoCambioServiceTest {
    
    @Autowired
    private TipoCambioSoapService tipoCambioSoapService;
    
    @Test
    public void testObtenerTipoCambioRango() {
        System.out.println("init");
        
        // Definir fechas de prueba para el rango
        String fechaInit = "2023-01-01";
        String fechaFin = "2023-01-31";
        
        // Llamar al método con los parámetros adecuados
        String resultado = tipoCambioSoapService.obtenerTipoCambioRango(fechaInit, fechaFin);
        
        // Mostrar el resultado
        System.out.println("Resultado del servicio SOAP: " + resultado);
        
        System.out.println("fin");
    }
}