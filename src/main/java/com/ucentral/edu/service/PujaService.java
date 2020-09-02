package com.ucentral.edu.service;

import java.util.List;

import com.ucentral.edu.model.Puja;

public interface PujaService {

	Puja crearPuja(Integer idSubasta, Integer idUsuario, Integer valorPuja);

	List<Puja> consultarPujasSubasta(Integer idSubasta);

	List<Puja> consultarPujasUsuario(Integer idUsuario);

	Puja consultarPuja(Integer idPuja);

	void eliminarPuja(Integer idPuja);

}
