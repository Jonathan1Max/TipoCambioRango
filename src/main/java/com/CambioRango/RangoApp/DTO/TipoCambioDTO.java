/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.CambioRango.RangoApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TipoCambioDTO {
    private String fechaInit; // Nueva fecha inicial
    private float ventaInit;  // Venta para la fecha inicial
    private float compraInit; // Compra para la fecha inicial
    private String fechaFin;   // Nueva fecha final
    private float ventaFin;    // Venta para la fecha final
    private float compraFin;   // Compra para la fecha final
}