package com.ucentral.edu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ucentral.edu.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	Optional<Usuario> findByCorreo(String correo);

	@Query(value = "SELECT u FROM Usuario u WHERE u.user = ?1")
	Usuario findByIdUser(Integer iduser);
	

}
