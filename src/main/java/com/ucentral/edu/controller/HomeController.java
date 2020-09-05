package com.ucentral.edu.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping
	public String mostrarHome(Model model) {

		model.addAttribute("mensaje", "Esto es un mensaje");
		model.addAttribute("fecha", new Date());

		return "home";
	}

}
