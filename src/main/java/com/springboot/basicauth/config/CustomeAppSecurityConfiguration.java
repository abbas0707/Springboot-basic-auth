package com.springboot.basicauth.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.springboot.basicauth.handler.CustomAuthenticationEntryPoint;

@Component
public class CustomeAppSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired	
	private CustomAuthenticationEntryPoint authenticationEntryPoint;
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		 String password = "123";
		 
	        String encrytedPassword = this.passwordEncoder().encode(password);
	        System.out.println("Encoded password of 123=" + encrytedPassword);
		auth.inMemoryAuthentication().withUser("user").password(encrytedPassword).roles("USER");
		
		
	}
	
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable().authorizeRequests()
		.anyRequest().authenticated()
		.and().httpBasic()
		.authenticationEntryPoint(authenticationEntryPoint);
	}
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	
	
}
