package com.secure.securityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.secure.service.CustomerService;

@Configuration
@EnableWebSecurity
public class AppSecurityConfigue {
	@Autowired
	private CustomerService custService;
	
	
	// cretae password encoder to encrypt password
	// customize authenticator provoider -> get userdetails from db 
	//authentication manager -> verify user details are valid or not
	// security filter chain -> customize security setting 
	
	@Bean
	public PasswordEncoder pswdEncode() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	 public AuthenticationProvider authPro() {
		 DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		 authProvider.setUserDetailsService(custService);
		 authProvider.setPasswordEncoder(pswdEncode());
		 
		 return authProvider;
		 
	 }
	
	 @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
	        return config.getAuthenticationManager();
	    }
	 
	 @Bean
	public SecurityFilterChain securityfilter(HttpSecurity http) throws Exception {
		
		
		return http.csrf()
				.disable()
				.authorizeHttpRequests()
				.requestMatchers("/register","/login")
				.permitAll()
				.and()
				.build();
	}

}
