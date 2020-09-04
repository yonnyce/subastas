package com.ucentral.edu.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ucentral.edu.model.User;
import com.ucentral.edu.model.UserImpl;
import com.ucentral.edu.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UsuarioRepository usuarioRepository;

	public UserDetailsServiceImpl() {

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User appUser = usuarioRepository.getByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("nombre de usuario no encontrado"));

		UserImpl user = new UserImpl();

		user.setActive(true);

		user.setId(appUser.getId());
		user.setPasswordHash(appUser.getPasswordHash());
		user.setUsername(appUser.getUsername());

		user.setAutoridades(this.getUserAuthorities(appUser.getUsername()));

		return user;
	}

	public List<? extends GrantedAuthority> getUserAuthorities(String username) {
		List<UserAuthoritiesEntity> authorities = this.userAuthoritiesEntityRepository.findByUsername(username);

		List<SimpleGrantedAuthority> result = new ArrayList<>();

		for (UserAuthoritiesEntity entity : authorities) {
			result.add(new SimpleGrantedAuthority(entity.getAuthority()));
		}

		return result;
	}

}
