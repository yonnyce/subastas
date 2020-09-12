package com.ucentral.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ucentral.edu.model.User;
import com.ucentral.edu.model.Usuario;
import com.ucentral.edu.service.UserService;
import com.ucentral.edu.service.UsuarioService;

@Controller
@RequestMapping("/Usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UserService userService;

	@GetMapping(value = "/obtenerUsuarioActual")
	public String buscarUsuario(Model model) {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
		  userDetails = (UserDetails) principal;
		}
		
		User user = userService.findByUserName(userDetails.getUsername());
		model.addAttribute("usuario", usuarioService.buscarUsuario(user.getUsuario().getId()));
		
		return "usuario/obtenerUsuario";
	}

	@PostMapping(value = "/actualizarUsuario")
	public String actualizarUsuario(Usuario usuario) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
		  userDetails = (UserDetails) principal;
		}
		
		User user = userService.findByUserName(userDetails.getUsername());

		Usuario usuarioActualizado = usuarioService.buscarUsuario(user.getUsuario().getId());
		usuarioActualizado.setCorreo(usuario.getCorreo());
		usuarioActualizado.setTelefono(usuario.getTelefono());
		
		usuarioService.actualizarUsuario(usuarioActualizado);
		return "redirect:/Usuario/obtenerUsuarioActual";
	}

	@PostMapping(value = "/eliminarUsuario/{idUsuario}")
	public void eliminarUsuario(@PathVariable("idUsuario") Integer idUsuario) {
		usuarioService.eliminarUsuario(idUsuario);
	}
}
