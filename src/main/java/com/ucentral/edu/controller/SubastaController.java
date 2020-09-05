package com.ucentral.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ucentral.edu.model.Subasta;
import com.ucentral.edu.service.impl.SubastaServiceImpl;

@Controller
@RequestMapping("/Subastas")
public class SubastaController {

	@Autowired
	public SubastaServiceImpl subastaServiceImpl;
	
	@PostMapping(value = "/crearSubasta")
	public void crearSubasta(Subasta newSubasta) {
		subastaServiceImpl.crearSubasta(newSubasta);
	}
	
	@GetMapping(value = "/consultar")
	public String consultarSubastas(String filtro, Model model) {
		model.addAttribute("subastas", subastaServiceImpl.consultarSubastas(filtro));
		return "subastas/listado";
	}
	
	@GetMapping(value = "/consultar/{idSubasta}")
	public String consultarSubastas(@PathVariable("idSubasta") Integer idSubasta, Model model) {
		model.addAttribute("subasta", subastaServiceImpl.consultarSubasta(idSubasta));
		return "subastas/consultarSubasta";
	}
	
	@PostMapping(value = "/eliminar/{idSubasta}")
	public void eliminarSubasta(@PathVariable("idSubasta") Integer idSubasta) {
		subastaServiceImpl.eliminarSubasta(idSubasta);
	}
	
	@GetMapping(value = "/consultarSubastasPropias/{idUsuario}")
	public String consultarSubastasPorUsuario(@PathVariable("idUsuario") Integer idUsuario, Model model) {
		model.addAttribute("subastas", subastaServiceImpl.consultarSubastasPorUsuario(idUsuario));
		return "subastas/consultarSubastasPorUsuario";
	}
}
