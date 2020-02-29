package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	@Qualifier("customUserDetailService")
	CustomUserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)throws Exception{
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
	@Override
	protected void configure(HttpSecurity http)throws Exception  {
		
		http.authorizeRequests()
		.antMatchers("/portal/korisnik/prikazPredmeta","/portal/security/profil","/portal/reportsC/sviTestoviPredmeta")
		.hasAnyRole("student","profesor","asistent")
		.antMatchers("/portal/korisnik/sviStudentiPredmet")
		.hasAnyRole("profesor","asistent")
		.antMatchers("/portal/korisnik/radiTest","/portal/korisnik/sledecePitanje","/portal/reportsC/pitanjaodgovori")
		.hasRole("student")
		.antMatchers("/portal/korisnik/pregledajtestove","/portal/korisnik/sledecitest")
		.hasRole("profesor")
		.antMatchers("/portal/security/saveUser")
		.hasRole("administrator")
		.antMatchers("/portal/reportsC/sviStudentiPredmet")
		.hasAnyRole("asistent","profesor")
		.and()
		.formLogin()
		.loginPage("/security/login")
		.permitAll()
		.loginProcessingUrl("/login")
		.defaultSuccessUrl("/security/profil")
		//	.failureForwardUrl("/security/login?error=da")
		.and()
		.logout()
		.logoutSuccessUrl("/");
		http.csrf().disable();
	}
}