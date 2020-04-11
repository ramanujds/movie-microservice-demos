package com.capg.gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	
	auth.inMemoryAuthentication()
		.withUser("ramanuj")
		.password("password")
		.roles("USER")
		.and()
		.withUser("admin")
		.password("password")
		.roles("ADMIN"); 
}
 
 @Override
	protected void configure(HttpSecurity http) throws Exception {
		 http.authorizeRequests()
		 	 .antMatchers("/admin/**")
		 	 .hasRole("ADMIN")
		 	 .antMatchers("/user/**")
		 	 .hasAnyRole("ADMIN","USER")
		 	 .antMatchers("/**")
		 	 .permitAll()
		 	 .and()
		 	 .formLogin();
	}

@Bean
public PasswordEncoder getPasswordEncoder() {
	return NoOpPasswordEncoder.getInstance();
}
}
