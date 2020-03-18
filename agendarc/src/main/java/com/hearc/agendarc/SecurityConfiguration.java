package com.hearc.agendarc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication() //spécifie à Spring que l'authentification se fera "en mémoire"
                .withUser("user").password("{noop}password").roles("USER"); //{noop}, pas de cryptage
    }
    
    @Override
	protected void configure(HttpSecurity http) throws Exception {
	  http
	   .authorizeRequests() 
	     .antMatchers("/admin").authenticated()
	     .and()
	     .formLogin();

	}
}
