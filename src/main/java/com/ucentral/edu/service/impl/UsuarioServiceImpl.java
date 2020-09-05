package com.ucentral.edu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ucentral.edu.model.Usuario;
import com.ucentral.edu.repository.UsuarioRepository;
import com.ucentral.edu.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public Usuario crearUsuario(Usuario nuevoUsuario) {
		// TODO Auto-generated method stub
		return null;
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

}
