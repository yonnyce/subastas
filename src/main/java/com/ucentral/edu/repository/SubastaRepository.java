package com.ucentral.edu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ucentral.edu.model.Subasta;

public interface SubastaRepository extends JpaRepository<Subasta, Integer> {

	@Query("SELECT s FROM Subasta s WHERE  LOWER(s.nombre) LIKE LOWER(CONCAT('%',:filtro,'%'))")
	List<Subasta> filtrarSubastas(@Param("filtro") String filtro);

	List<Subasta> findByUsuarioId(Integer idUsuario);

}
