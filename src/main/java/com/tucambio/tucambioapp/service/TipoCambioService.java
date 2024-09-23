/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tucambio.tucambioapp.service;

/**
 *
 * @author J MAX
 */
import com.tucambio.tucambioapp.Dto.TipoCambioDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TipoCambioService {

    public List<TipoCambioDTO> consultarTipoCambio(String fechaInicio, String fechaFin) {
        List<TipoCambioDTO> listaTipoCambio = new ArrayList<>();

        // Simular respuesta del servicio
        TipoCambioDTO tipoCambio1 = new TipoCambioDTO();
        tipoCambio1.setFecha(fechaInicio); // Usar la fecha de inicio
        tipoCambio1.setVenta(7.65f);
        tipoCambio1.setCompra(7.60f);
        listaTipoCambio.add(tipoCambio1);

        TipoCambioDTO tipoCambio2 = new TipoCambioDTO();
        tipoCambio2.setFecha(fechaFin); // Usar la fecha de fin
        tipoCambio2.setVenta(7.66f);
        tipoCambio2.setCompra(7.61f);
        listaTipoCambio.add(tipoCambio2);

        return listaTipoCambio;
    }
}