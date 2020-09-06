package com.ucentral.edu.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserImpl implements UserDetails {

	private static final long serialVersionUID = -3529545265956858394L;

	Integer id;

	private Boolean active;

	private String email;

	private String passwordHash;

	private String username;

	private Usuario usuario;

	private List<? extends GrantedAuthority> autoridades;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return autoridades;
	}

	@Override
	public String getPassword() {
		return this.passwordHash;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.active;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public List<? extends GrantedAuthority> getAutoridades() {
		return autoridades;
	}

	public void setAutoridades(List<? extends GrantedAuthority> autoridades) {
		this.autoridades = autoridades;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "UserImpl [id=" + id + ", active=" + active + ", email=" + email + ", passwordHash=" + passwordHash
				+ ", username=" + username + ", autoridades=" + autoridades.size() + "]";
	}

}
