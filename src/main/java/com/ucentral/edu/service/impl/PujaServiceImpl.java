package com.ucentral.edu.service.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ucentral.edu.model.Puja;
import com.ucentral.edu.model.Subasta;
import com.ucentral.edu.repository.PujaRepository;
import com.ucentral.edu.service.PujaService;
import com.ucentral.edu.service.SubastaService;
import com.ucentral.edu.service.UsuarioService;

@Service
public class PujaServiceImpl implements PujaService {

	@Autowired
	private PujaRepository pujaRepository;

	@Autowired
	private SubastaService subastaService;
	
	@Autowired
	private UsuarioService usuarioService;

	@Override
	public void crearPuja(Puja puja) {

		// TODO: Logica para validar que esta puja sea mayor a la puja anterior, en caso
		// contrario lanzar excepcion de negocio. requiere consulta en jpql en la
		// interfaz para ordenar de mayor a menor y obtener la mayor.

		this.pujaRepository.save(puja);
	}

	@Override
	public List<Puja> consultarPujasSubasta(Integer idSubasta) {
		return this.pujaRepository.findBySubastaId(idSubasta);
	}

	@Override
	public List<Puja> consultarPujasUsuario(Integer idUsuario) {
		return this.pujaRepository.findByUsuarioId(idUsuario);
	}

	@Override
	public Puja consultarPuja(Integer idPuja) {
		return this.pujaRepository.findById(idPuja).orElseThrow(EntityNotFoundException::new);
	}

	@Override
	public void eliminarPuja(Integer idPuja) {

		Puja puja = this.consultarPuja(idPuja);

		this.pujaRepository.delete(puja);

	}

	@Override
	public Puja buscarByUsuarioId(Integer idUsuario) {
		return this.pujaRepository.buscarByUsuarioId(idUsuario);
	}

}
