/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.CambioRango.RangoApp.controller;

/**
 *
 * @author J MAX
 */
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CambioRango.RangoApp.DTO.UsuarioDTO;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> obtenerUsuario(@PathVariable Long id) {
		UsuarioDTO usuario = new UsuarioDTO(id, "Jonathan", "Max");
		return ResponseEntity.ok(usuario);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UsuarioDTO> actualizarUsuario(@PathVariable Long id,
			@RequestBody UsuarioDTO usuario) {
		usuario.setId(id);
		return ResponseEntity.ok(usuario);
	}
	
}
