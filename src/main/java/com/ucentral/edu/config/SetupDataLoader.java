package com.ucentral.edu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ucentral.edu.model.AppRole;
import com.ucentral.edu.model.Usuario;
import com.ucentral.edu.repository.AppRoleRepository;
import com.ucentral.edu.service.UsuarioService;

/**
 * Inicializador de la base de datos del sistema, se encarga de registrar los
 * usuarios, permisos y demas informacion por defecto
 * 
 * @author Jonatan Quiroz C.
 *
 */
@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	private boolean alreadySetup = false;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private AppRoleRepository appRoleRepository;

	@Override
	@Transactional
	public void onApplicationEvent(final ContextRefreshedEvent event) {

		if (alreadySetup) {
			return;
		}

		this.inicializarRoles();

		String correo = "usuario@gmail.com";

		if (this.usuarioService.buscarUsuarioPorCorreo(correo) != null) {
			return;
		}

		Usuario usuario = new Usuario();

		usuario.setCorreo(correo);
		usuario.setNombre("Usuario de prueba");
		usuario.setTelefono("312123456");

		this.usuarioService.crearUsuario(usuario);

		System.out.println("\n\nUsuario usuario@gmail.com por defecto creado exitosamente, password: user\n\n");

		alreadySetup = true;
	}

	@Transactional
	public void inicializarRoles() {

		if (this.appRoleRepository.findByName("USUARIO").isPresent()) {
			return;
		}

		AppRole role = new AppRole();

		role.setName("USUARIO");

		this.appRoleRepository.saveAndFlush(role);
		System.out.println("rol USUARIO creado exitosamente");

	}

}