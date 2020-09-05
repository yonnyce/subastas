package com.ucentral.edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ucentral.edu.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}
