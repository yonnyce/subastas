package com.ucentral.edu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "app_user")
public class User {

	@Id
	@SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
	@Column(unique = true, nullable = false)
	Integer id;

	@Column(name = "user_name", nullable = false, unique = true, length = 35)
	private String username;

	@Column(name = "password_hash", length = 512, nullable = false)
	private String passwordHash;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

}
