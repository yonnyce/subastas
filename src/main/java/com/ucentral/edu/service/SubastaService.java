package com.ucentral.edu.service;

import java.util.List;

import com.ucentral.edu.model.Subasta;

public interface SubastaService {

	Subasta crearSubasta(Subasta newSubasta);

	List<Subasta> consultarSubastas(Integer idUsuario);

	Subasta consultarSubasta(Integer idSubasta);

	void eliminarSubasta(Integer idSubasta);

	List<Subasta> consultarSubastasPorUsuario(Integer idUsuario);

}
