/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tucambio.tucambioapp.controller;

/**
 *
 * @author J MAX
 */
import com.tucambio.tucambioapp.service.TipoCambioService;
import com.tucambio.tucambioapp.Dto.ErrorResponse;
import com.tucambio.tucambioapp.Dto.TipoCambioDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@Tag(name = "Tipo de Cambio", description = "API para consultar el tipo de cambio")
public class TipoCambioController {

    private TipoCambioService tipoCambioService;

    @Autowired
    public void setTipoCambioService(TipoCambioService tipoCambioService) {
        this.tipoCambioService = tipoCambioService;
    }

    @Operation(summary = "Consultar tipo de cambio en un rango de fechas")
    @GetMapping("/tipoCambioRango")
    public ResponseEntity<?> obtenerTipoCambioRango(
        @Parameter(description = "Fecha inicio en formato dd/MM/yyyy") 
        @RequestParam(value = "fecha_ini", required = true) String fechaInicio,

        @Parameter(description = "Fecha fin en formato dd/MM/yyyy") 
        @RequestParam(value = "fecha_fin", required = true) String fechaFin) {

        // Validar el formato de las fechas
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate inicio;
        LocalDate fin;

        try {
            inicio = LocalDate.parse(fechaInicio, formatter);
            fin = LocalDate.parse(fechaFin, formatter);
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Formato de fecha inválido. Use dd/MM/yyyy."));
        }

        // Mensaje de salida para verificar las fechas recibidas
        System.out.println("Consultando tipo de cambio desde " + inicio + " hasta " + fin);

        List<TipoCambioDTO> resultados = tipoCambioService.consultarTipoCambio(fechaInicio, fechaFin);

        if (resultados.isEmpty()) {
            System.out.println("No se encontraron resultados para el rango de fechas proporcionado.");
            return ResponseEntity.ok(resultados);
        } else {
            System.out.println("Resultados obtenidos:");
            for (TipoCambioDTO tipoCambio : resultados) {
                System.out.println("Fecha: " + tipoCambio.getFecha() + ", Venta: " + tipoCambio.getVenta() + ", Compra: " + tipoCambio.getCompra());
            }
            return ResponseEntity.ok(resultados);
        }
    }
}