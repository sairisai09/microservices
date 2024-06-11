package com.secure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.secure.entity.Customer;
import com.secure.repository.CustomerRepository;
import com.secure.service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService custService;
	
	@Autowired
	private PasswordEncoder pswdEncoder;
	@Autowired
	private CustomerRepository custRepo;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@PostMapping("/login")
	public ResponseEntity<String> logincheck(@RequestBody Customer cl){
		
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(cl.getEmail(),cl.getPassword());
		try {
			Authentication authenticate = authManager.authenticate(token);
			
			if(authenticate.isAuthenticated()) {
		return new ResponseEntity<String>("login succesful",HttpStatus.OK);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Invalid Credentials",HttpStatus.BAD_REQUEST);
		
	}
	
	
	
	
	
	@PostMapping("/register")
	public ResponseEntity<String> registration(@RequestBody Customer c){
//		UserDetails username = custService.loadUserByUsername(c.getEmail());
		String encode = pswdEncoder.encode(c.getPassword());
		c.setPassword(encode);
		custRepo.save(c);
		return new ResponseEntity<String>("User Registred Succesfully", HttpStatus.CREATED);
		
	}

}
