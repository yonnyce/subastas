package com.ucentral.edu.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ucentral.edu.model.Subasta;

public interface SubastaService {

	Subasta crearSubasta(Subasta newSubasta);

	Page<Subasta> consultarSubastas(Pageable page);

	Subasta consultarSubasta(Integer idSubasta);

	void eliminarSubasta(Integer idSubasta);

}
