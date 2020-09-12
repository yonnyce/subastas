package com.ucentral.edu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ucentral.edu.model.Subasta;

public interface SubastaRepository extends JpaRepository<Subasta, Integer> {

	
	List<Subasta> findByUsuarioIdNot(Integer idUsuario);

	List<Subasta> findByUsuarioId(Integer idUsuario);

}
