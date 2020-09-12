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
import com.ucentral.edu.model.UserImpl;
import com.ucentral.edu.service.PujaService;
import com.ucentral.edu.service.UserService;

@Controller
@RequestMapping("/puja")
public class PujaController {

	@Autowired
	private PujaService pujaService;
	
	@Autowired
	private UserService userService;

	@PostMapping(value = "/crearPuja/{idSubasta}/{idUsuario}")
	public void crearPuja(@PathVariable("idSubasta") Integer idSubasta, @PathVariable("idUsuario") Integer idUsuario,
			Integer valorPuja) {
		pujaService.crearPuja(idSubasta, idUsuario, valorPuja);
	}

	@GetMapping(value = "/consultarPujasSubasta/{idSubasta}")
	public String consultarPujasSubasta(@PathVariable("idSubasta") Integer idSubasta, Model model) {
		model.addAttribute("pujasXSubasta", pujaService.consultarPujasSubasta(idSubasta));
		return "pujas/consultarPujasSubasta";
	}

	@GetMapping(value = "/consultarPujasUsuario")
	public String consultarPujasUsuario(Model model) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
		  userDetails = (UserDetails) principal;
		}
		
		User user = userService.findByUserName(userDetails.getUsername());

		model.addAttribute("pujasXUsuario", pujaService.consultarPujasUsuario(user.getUsuario().getId()));
		return "pujas/consultarPujasUsuario";
	}

	@GetMapping(value = "/consultarPuja/{idPuja}")
	public String consultarPuja(@PathVariable("idPuja") Integer idPuja, Model model) {
		model.addAttribute("puja", pujaService.consultarPuja(idPuja));
		return "pujas/consultarPuja";
	}

	@PostMapping(value = "/eliminarPuja/{idPuja}")
	public void eliminarPuja(@PathVariable("idPuja") Integer idPuja) {
		pujaService.eliminarPuja(idPuja);
	}

}
