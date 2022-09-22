package com.sony.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sony.entity.Customer;
import com.sony.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository crepo;
	
	public List<Customer> getAllCustomers(){
		return crepo.findAll();
	}
	
	
	public Customer getCustomerById(String id) {
		Optional<Customer> op = crepo.findById(id);
		if(op.isEmpty()) {
			return null;
		}
		return op.get();
	}
	
	
	public Customer addNewCustomer(Customer customer) {
		return crepo.save(customer);
	}


	public Customer updateExistingCustomer(Customer customer,String id) {
		customer.setCustomerId(id);
		return crepo.save(customer);
	}
	
}
