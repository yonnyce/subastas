package com.ucentral.edu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@SequenceGenerator(name = "usuario_id_seq", sequenceName = "usuario_id_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_id_seq")
	@Column(unique = true, nullable = false)
	Integer id;

	@Column(length = 45, nullable = false)
	String nombre;

	@Column(length = 45)
	String telefono;

	@Column(nullable = false, unique = true, length = 45)
	String correo;

	@OneToOne
	@JoinColumn(name = "id_user", nullable = false)
	User user;

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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

}
