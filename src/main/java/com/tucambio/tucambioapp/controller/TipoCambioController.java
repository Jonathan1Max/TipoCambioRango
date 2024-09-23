/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tucambio.tucambioapp.controller;

/**
 *
 * @author J MAX
 */
import com.tucambio.tucambioapp.Dto.TipoCambioDTO;
import com.tucambio.tucambioapp.service.TipoCambioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
    public List<TipoCambioDTO> obtenerTipoCambioRango(
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
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Formato de fecha inválido. Use dd/MM/yyyy.");
        }

        // Consultar el tipo de cambio
        return tipoCambioService.consultarTipoCambio(fechaInicio, fechaFin);
    }
}