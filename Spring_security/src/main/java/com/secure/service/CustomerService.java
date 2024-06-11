package com.secure.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.secure.entity.Customer;
import com.secure.repository.CustomerRepository;

@Service
public class CustomerService implements UserDetailsService{
	
	@Autowired
	private CustomerRepository custrepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Customer customer = custrepo.findByEmail(email);
		return new User(customer.getEmail(), customer.getPassword(), Collections.EMPTY_LIST);
	}

}
