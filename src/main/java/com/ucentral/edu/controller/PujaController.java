package com.ucentral.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ucentral.edu.service.impl.PujaServiceImpl;

@Controller
@RequestMapping("/puja")
public class PujaController {

	@Autowired
	private PujaServiceImpl pujaServiceImpl;
	
	
	@PostMapping(value = "/crearPuja/{idSubasta}/{idUsuario}")
	public void crearPuja(@PathVariable("idSubasta") Integer idSubasta, @PathVariable("idUsuario") Integer idUsuario, Integer valorPuja) {
		pujaServiceImpl.crearPuja(idSubasta, idUsuario, valorPuja);
	}

	@GetMapping(value = "/consultarPujasSubasta/{idSubasta}")
	public String consultarPujasSubasta(@PathVariable("idSubasta") Integer idSubasta, Model model) {
		model.addAttribute("pujasXSubasta", pujaServiceImpl.consultarPujasSubasta(idSubasta));
		return "pujas/consultarPujasSubasta";
	}

	@GetMapping(value = "/consultarPujasUsuario/{idUsuario}")
	public String consultarPujasUsuario(@PathVariable("idUsuario") Integer idUsuario, Model model) {
		model.addAttribute("pujasXUsuario", pujaServiceImpl.consultarPujasUsuario(idUsuario));
		return "pujas/consultarPujasUsuario";
	}

	@GetMapping(value = "/consultarPuja/{idPuja}")
	public String consultarPuja(@PathVariable("idPuja") Integer idPuja, Model model) {
		model.addAttribute("puja", pujaServiceImpl.consultarPuja(idPuja));
		return "pujas/consultarPuja";
	}
	
	@PostMapping(value = "/eliminarPuja/{idPuja}")
	public void eliminarPuja(@PathVariable("idPuja") Integer idPuja) {
		pujaServiceImpl.eliminarPuja(idPuja);
	}
	
	
}
