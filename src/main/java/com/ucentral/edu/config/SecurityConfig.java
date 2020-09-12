package com.ucentral.edu.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select user_name, password_hash, estatus from app_user where user_name=?")
				.authoritiesByUsernameQuery("select u.user_name, r.name from user_role ur "
						+ "inner join app_user u on u.id = ur.user_id "
						+ "inner join app_role r on r.id = ur.role_id " 
						+ "where u.user_name = ?");
	}
	
	
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests().antMatchers(
				"/bootstrap/**",
				"/tinymce/**").permitAll()
		.antMatchers("/pujas/**","/subastas/**","/usuario/**").hasAnyAuthority("USUARIO")
		.antMatchers("/signup").permitAll().anyRequest().authenticated()
		.and().formLogin().permitAll();
	}
	
	@Bean 
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
