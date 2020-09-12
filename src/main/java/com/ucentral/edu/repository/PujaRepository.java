package com.ucentral.edu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ucentral.edu.model.Puja;

public interface PujaRepository extends JpaRepository<Puja, Integer> {

	List<Puja> findBySubastaId(Integer idSubasta);

	List<Puja> findByUsuarioId(Integer idUsuario);
	
	@Query(value = "SELECT p FROM Puja p WHERE p.usuario = ?1")
	Puja buscarByUsuarioId(Integer idUsuario);

}
