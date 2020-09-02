package com.ucentral.edu.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

public class Subasta {

	@Id
	@SequenceGenerator(name = "subasta_id_seq", sequenceName = "subasta_id_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subasta_id_seq")
	@Column(unique = true, nullable = false)
	Integer id;

	@Column(name = "nombre", nullable = false)
	String nombre;

	@Column(name = "fecha_inicial", nullable = false)
	Date fechaInicial;

	@Column(name = "fecha_final", nullable = false)
	Date fechaFinal;

	@Column(name = "descripcion", nullable = false)
	String descripcion;

	@Column(name = "costo_inicial", nullable = false)
	Integer costoInicial;

	@Column(name = "imagen", nullable = true)
	Byte[] imagen;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario", nullable = false)
	Usuario usuario;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getCostoInicial() {
		return costoInicial;
	}

	public void setCostoInicial(Integer costoInicial) {
		this.costoInicial = costoInicial;
	}

	public Byte[] getImagen() {
		return imagen;
	}

	public void setImagen(Byte[] imagen) {
		this.imagen = imagen;
	}

}
