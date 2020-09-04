package com.ucentral.edu.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		//TODO: Cambiar endpoints a los necesarios
		http.authorizeRequests().antMatchers("/usuarios/**").hasRole("ADMIN")
				.antMatchers("/asignatura/**").hasRole("ADMIN")
				.antMatchers("/api/asignaturas/**").hasRole("ADMIN")
				.antMatchers("/reserva/**").hasAnyRole("ADMIN","COOR_RESERVAS")
				.antMatchers("/cliente/**").hasAnyRole("ADMIN","COOR_RESERVAS")
				.antMatchers("/periodo/**").hasRole("ADMIN")
				.antMatchers("/periodo/**").hasRole("ADMIN")
				.antMatchers("/importaciones/**").hasRole("ADMIN")
				.antMatchers("/reservar-recurso-estudiante").hasAnyRole("ADMIN","COOR_RESERVAS")
				.antMatchers("/reservar-recurso-externo").hasAnyRole("ADMIN","COOR_RESERVAS")
				
				
				.anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll().and().logout()
				.permitAll().and().rememberMe().rememberMeCookieName("uma-ucc-pesc")
				.tokenValiditySeconds(24 * 60 * 60).alwaysRemember(true) // expired time = 1 day
				.tokenRepository(persistentTokenRepository());
	}

	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(dataSource);
		return tokenRepository;
	}

	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/vendor/**").antMatchers("/css/**").antMatchers("/js/**");
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}

}
