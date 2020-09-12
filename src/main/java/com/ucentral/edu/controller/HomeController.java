package com.ucentral.edu.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ucentral.edu.model.AppRole;
import com.ucentral.edu.model.User;
import com.ucentral.edu.model.Usuario;
import com.ucentral.edu.repository.AppRoleRepository;
import com.ucentral.edu.service.UserService;
import com.ucentral.edu.service.UsuarioService;


@Controller
public class HomeController {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserService userService;
	
	@Autowired
	private AppRoleRepository appRoleRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/")
	public String mostrarHome(Model model) {
		return "home";
	}
	
	@GetMapping("/index")
	public String mostrarIndex(Authentication auth, HttpSession session) {
		String userName = auth.getName();
		System.out.println("usuario: " + userName);
		for(GrantedAuthority rol: auth.getAuthorities()){
			System.out.println("Rol: "+ rol.getAuthority());
		}
		
		if(session.getAttribute("user") == null) {
			User user = userService.findByUserName(userName);
			user.setPasswordHash(null);
			System.out.println("Usuario: " + user);
			session.setAttribute("user", user);
		}
		return "redirect:/";
	}
	
	@GetMapping("/signup")
	public String registrarse(User user) {
		return "formRegistro";
	}
	
	@PostMapping("/signup")
	public String guardarRegistro(User user) {
		
		String pwdPlano = user.getPasswordHash();
		String pwdEncrip = passwordEncoder.encode(pwdPlano);
		user.setPasswordHash(pwdEncrip);
		AppRole role = appRoleRepository.getOne(1);
		List<AppRole> roles = new ArrayList<>();
		roles.add(role);
		user.setRoles(roles);
		user.setEstatus(true);
		Usuario usuario = new Usuario();
		usuario.setNombre(user.getUsername());
		
		usuario.setUser(user);
		user.setUsuario(usuario);
		
		userService.saveUser(user);
		return "redirect:/index";
	}
	
	@GetMapping("/login")
	public String mostrarLogin() {
		return "formLogin";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		logoutHandler.logout(request, null, null);
		return "redirect:/login";
	}

}
