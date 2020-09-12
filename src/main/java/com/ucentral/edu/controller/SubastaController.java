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

import com.ucentral.edu.model.Subasta;
import com.ucentral.edu.model.User;
import com.ucentral.edu.model.UserImpl;
import com.ucentral.edu.model.Usuario;
import com.ucentral.edu.service.SubastaService;
import com.ucentral.edu.service.UserService;
import com.ucentral.edu.service.UsuarioService;

@Controller
@RequestMapping("/Subastas")
public class SubastaController {

	@Autowired
	public SubastaService subastaService;

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UserService userService;

	@PostMapping(value = "/crearSubasta")
	public void crearSubasta(Subasta newSubasta) {
		subastaService.crearSubasta(newSubasta);
	}

	@GetMapping(value = "/consultar")
	public String consultarSubastas(String filtro, Model model) {
		model.addAttribute("subastas", subastaService.consultarSubastas(filtro));
		return "subastas/listado";
	}

	@GetMapping(value = "/consultar/{idSubasta}")
	public String consultarSubastas(@PathVariable("idSubasta") Integer idSubasta, Model model) {
		model.addAttribute("subasta", subastaService.consultarSubasta(idSubasta));
		return "subastas/consultarSubasta";
	}

	@PostMapping(value = "/eliminar/{idSubasta}")
	public void eliminarSubasta(@PathVariable("idSubasta") Integer idSubasta) {
		subastaService.eliminarSubasta(idSubasta);
	}

	@GetMapping(value = "/consultarSubastasPropias")
	public String consultarSubastasPorUsuario(Model model) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
		  userDetails = (UserDetails) principal;
		}
		
		User user = userService.findByUserName(userDetails.getUsername());
		
		
		model.addAttribute("subastas", subastaService.consultarSubastasPorUsuario(user.getUsuario().getId()));
		return "subastas/consultarSubastasPorUsuario";
	}
}
