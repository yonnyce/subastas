package com.ucentral.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ucentral.edu.model.Usuario;
import com.ucentral.edu.service.impl.UsuarioServiceImpl;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;
	
	@GetMapping(value = "/obtenerUsuario/{idUsuario}")
	public String buscarUsuario(@PathVariable("idUsuario") Integer idUsuario, Model model) {
		model.addAttribute("usuario", usuarioServiceImpl.buscarUsuario(idUsuario));
		return "usuario/obtenerUsuario";
	}
	
	@PostMapping(value = "/actualizarUsuario")
	public void actualizarUsuario(Usuario usuario) {
		usuarioServiceImpl.actualizarUsuario(usuario);
	}
	
	@PostMapping(value = "/eliminarUsuario/{idUsuario}")
	public void eliminarUsuario(@PathVariable("idUsuario") Integer idUsuario) {
		usuarioServiceImpl.eliminarUsuario(idUsuario);
	}
}
