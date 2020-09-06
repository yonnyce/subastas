package com.ucentral.edu.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ucentral.edu.model.AppRole;
import com.ucentral.edu.model.User;
import com.ucentral.edu.model.UserImpl;
import com.ucentral.edu.repository.AppRoleRepository;
import com.ucentral.edu.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	AppRoleRepository appRoleRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User appUser = userRepository.getByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("nombre de usuario no encontrado"));

		UserImpl user = new UserImpl();

		user.setActive(true);
		user.setId(appUser.getId());
		user.setPasswordHash(appUser.getPasswordHash());
		user.setUsername(appUser.getUsername());

		user.setAutoridades(this.getUserAuthorities(appUser));
		user.setUsuario(appUser.getUsuario());

		return user;
	}

	public List<? extends GrantedAuthority> getUserAuthorities(User user) {
		AppRole authoritie = user.getRol();

		return Arrays.asList(new SimpleGrantedAuthority(authoritie.getName()));
	}

}
