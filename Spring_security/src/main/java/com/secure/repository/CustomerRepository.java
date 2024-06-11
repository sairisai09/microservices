package com.secure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.secure.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	
	public Customer findByEmail(String email);

}
