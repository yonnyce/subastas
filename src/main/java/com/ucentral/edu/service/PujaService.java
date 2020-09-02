package com.ucentral.edu.service;

import java.util.List;

import com.ucentral.edu.model.Puja;

public interface PujaService {

	Puja crearPuja(Puja nuevaPuja);

	List<Puja> consultarPujasSubasta(Integer idSubasta);

	List<Puja> consultarPujasUsuario(Integer idPuja);

	Puja consultarPuja(Integer idPuja);

	void eliminarPuja(Integer idPuja);

}
