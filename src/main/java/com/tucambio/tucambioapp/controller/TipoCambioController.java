package com.tucambio.tucambioapp.controller;

import com.tucambio.tucambioapp.Dto.TipoCambioDTO;
import com.tucambio.tucambioapp.service.TipoCambioSoapService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TipoCambioController {

    @Autowired
    private TipoCambioSoapService tipoCambioSoapService;

@GetMapping("/tipoCambioRango")
public ResponseEntity<Object> obtenerTipoCambioRango(
        @RequestParam String fechaInit,
        @RequestParam String fechaFin) {
    
    String tipoCambio = tipoCambioSoapService.obtenerTipoCambioRango(fechaInit, fechaFin);

    if (tipoCambio.contains("Error")) {
        return ResponseEntity.status(500).body("Error: " + tipoCambio);
    }

    try {
    JSONObject xmlJSONObj = XML.toJSONObject(tipoCambio);

    // Acceder al cuerpo de la respuesta SOAP
    JSONObject var = xmlJSONObj.getJSONObject("soap:Envelope")
            .getJSONObject("soap:Body")
            .getJSONObject("TipoCambioRangoResponse")
            .getJSONObject("TipoCambioRangoResult");

    // Aquí asumimos que Vars es un objeto que puede tener múltiples Var
    JSONArray varsArray = var.getJSONObject("Vars").getJSONArray("Var");

    // Inicializar variables para la venta y compra inicial y final
    float ventaInit = 0, compraInit = 0, ventaFin = 0, compraFin = 0;

    // Asegúrate de manejar correctamente el array Var
    if (varsArray.length() > 0) {
        JSONObject firstVar = varsArray.getJSONObject(0);
        ventaInit = firstVar.getFloat("venta");
        compraInit = firstVar.getFloat("compra");
    }

    if (varsArray.length() > 1) {
        JSONObject lastVar = varsArray.getJSONObject(varsArray.length() - 1);
        ventaFin = lastVar.getFloat("venta");
        compraFin = lastVar.getFloat("compra");
    }

    // Crear la respuesta con las fechas de inicio y fin incluidas
    TipoCambioDTO tipoCambioDTO = new TipoCambioDTO(
            fechaInit,
            ventaInit,
            compraInit,
            fechaFin,
            ventaFin,
            compraFin
    );

    return ResponseEntity.ok(tipoCambioDTO);
} catch (Exception e) {
    e.printStackTrace();
    return ResponseEntity.status(500).body("Error al procesar el XML: " + e.getMessage());
}
}
}
