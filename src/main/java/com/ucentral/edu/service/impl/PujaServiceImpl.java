package com.ucentral.edu.service.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;

import com.ucentral.edu.model.Puja;
import com.ucentral.edu.model.Subasta;
import com.ucentral.edu.repository.PujaRepository;
import com.ucentral.edu.service.PujaService;
import com.ucentral.edu.service.SubastaService;

public class PujaServiceImpl implements PujaService {

	@Autowired
	private PujaRepository pujaRepository;

	@Autowired
	private SubastaService subastaService;

	@Override
	public Puja crearPuja(Integer idSubasta, Integer idUsuario, Integer valorPuja) {

		Subasta subasta = this.subastaService.consultarSubasta(idSubasta);

		if (subasta.getUsuario().getId().equals(idUsuario)) {
			throw new IllegalStateException("Un usuario no puede realizar una puja a su propia subasta");
		}

		// TODO: Logica para validar que esta puja sea mayor a la puja anterior, en caso
		// contrario lanzar excepcion de negocio. requiere consulta en jpql en la
		// interfaz para ordenar de mayor a menor y obtener la mayor.

		Puja nuevaPuja = new Puja();

		// TODO: Pendiente interfaz de usuarios para consulta
		nuevaPuja.setUsuario(null);
		nuevaPuja.setValor(valorPuja);
		nuevaPuja.setSubasta(subasta);

		return this.pujaRepository.save(nuevaPuja);
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

}
