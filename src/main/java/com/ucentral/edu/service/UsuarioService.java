package com.ucentral.edu.service;

import com.ucentral.edu.model.Usuario;

public interface UsuarioService {

	Usuario crearUsuario(Usuario nuevoUsuario);

	void eliminarUsuario(Integer idUsuario);
	
	Usuario buscarUsuario(Integer idUsuario);

	void actualizarUsuario(Usuario usuario);
}
