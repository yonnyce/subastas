package com.ucentral.edu.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ucentral.edu.model.AppRole;
import com.ucentral.edu.model.User;
import com.ucentral.edu.model.UserImpl;
import com.ucentral.edu.model.Usuario;
import com.ucentral.edu.repository.UserRepository;
import com.ucentral.edu.service.UserService;
import com.ucentral.edu.service.UsuarioService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	

	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	public User findByUserName(String userName) throws UsernameNotFoundException {
		User appUser = userRepository.getByUsername(userName)
				.orElseThrow(() -> new UsernameNotFoundException("nombre de usuario no encontrado"));
		return appUser;
	}

	@Override
	public void saveUser(User user) {
		userRepository.save(user);
	}
	
	public User crearUser(User user) {

		if (this.userRepository.getByUsername(user.getUsername()).isPresent()) {
			throw new IllegalStateException("Ya existe un usuario con el correo ingresado.");
		}

		Usuario usuario = new Usuario();
		usuario.setNombre(user.getUsername());
		user.setUsuario(usuarioService.crearUsuario(usuario));
		
		this.userRepository.save(user);

		return user;
	}

}
