package com.ucentral.edu.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ucentral.edu.model.User;
import com.ucentral.edu.model.Usuario;
import com.ucentral.edu.repository.AppRoleRepository;
import com.ucentral.edu.repository.UserRepository;
import com.ucentral.edu.repository.UsuarioRepository;
import com.ucentral.edu.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AppRoleRepository appRoleRepository;

	/*@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
*/
	@Override
	@Transactional
	public Usuario crearUsuario(Usuario nuevoUsuario) {
		return this.usuarioRepository.save(nuevoUsuario);

	}

	@Override
	public void eliminarUsuario(Integer idUsuario) {
		usuarioRepository.deleteById(idUsuario);
	}

	@Override
	public Usuario buscarUsuario(Integer idUsuario) {
		return usuarioRepository.getOne(idUsuario);
	}

	@Override
	public void actualizarUsuario(Usuario usuario) {
		usuarioRepository.save(usuario);
	}

	@Override
	public Usuario buscarUsuarioPorCorreo(String correo) {
		return this.usuarioRepository.findByCorreo(correo).orElse(null);
	}

}
