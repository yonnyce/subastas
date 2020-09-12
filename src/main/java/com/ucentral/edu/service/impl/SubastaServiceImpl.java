package com.ucentral.edu.service.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import com.ucentral.edu.model.Subasta;
import com.ucentral.edu.repository.SubastaRepository;
import com.ucentral.edu.service.SubastaService;

@Service
public class SubastaServiceImpl implements SubastaService {

	@Autowired
	private SubastaRepository subastaRepository;

	@Override
	public Subasta crearSubasta(Subasta newSubasta) {
		return this.subastaRepository.save(newSubasta);
	}

	@Override
	public List<Subasta> consultarSubastas(Integer idUsuario) {
		return this.subastaRepository.findByUsuarioIdNot(idUsuario);
	}

	@Override
	public Subasta consultarSubasta(Integer idSubasta) {
		return this.subastaRepository.findById(idSubasta).orElseThrow(EntityNotFoundException::new);
	}

	@Override
	public void eliminarSubasta(Integer idSubasta) {
		this.subastaRepository.deleteById(idSubasta); ;
	}

	@Override
	public List<Subasta> consultarSubastasPorUsuario(Integer idUsuario) {
		return this.subastaRepository.findByUsuarioId(idUsuario);
	}

}
