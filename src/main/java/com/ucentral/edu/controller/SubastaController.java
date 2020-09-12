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

import com.ucentral.edu.model.Puja;
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

	@GetMapping(value = "/crear")
	public String nuevaSubasta(Subasta subasta) {
		return "subastas/nueva";
	}
	
	@PostMapping(value = "/crearSubasta")
	public String crearSubasta(Subasta newSubasta) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
		  userDetails = (UserDetails) principal;
		}
		
		User user = userService.findByUserName(userDetails.getUsername());
		newSubasta.setUsuario(usuarioService.buscarUsuario(user.getUsuario().getId()));
		
		subastaService.crearSubasta(newSubasta);
		
		return"redirect:/Subastas/consultarSubastasPropias";
	}

	@GetMapping(value = "/consultar")
	public String consultarSubastas(Model model) {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
		  userDetails = (UserDetails) principal;
		}
		
		User user = userService.findByUserName(userDetails.getUsername());
				
		model.addAttribute("subastas", subastaService.consultarSubastas(user.getUsuario().getId()));
		return "subastas/listado";
	}

	@GetMapping(value = "/consultar/{idSubasta}")
	public String consultarSubastas(@PathVariable("idSubasta") Integer idSubasta, Model model) {
		model.addAttribute("subasta", subastaService.consultarSubasta(idSubasta));
		return "subastas/consultarSubasta";
	}

	@GetMapping(value = "/eliminar/{id}")
	public String eliminarSubasta(@PathVariable("id") Integer idSubasta) {
		subastaService.eliminarSubasta(idSubasta);
		return"redirect:/Subastas/consultarSubastasPropias";
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
