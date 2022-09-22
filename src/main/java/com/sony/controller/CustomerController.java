package com.sony.controller;

//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.sony.entity.Customer;
import com.sony.entity.CustomerList;
import com.sony.service.CustomerService;
@CrossOrigin
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	@Autowired
	private CustomerService cserv;

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public CustomerList getRequestToGetAllCustomers() {
		return new CustomerList(cserv.getAllCustomers());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getCustomerById(@PathVariable String id) {

		Customer customer = cserv.getCustomerById(id);
		if (customer == null) {
			return ResponseEntity.status(404).body("NO DATA FOUND FOR THE ID :");
		}
		return ResponseEntity.ok(customer);
	}

	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE })
	public ResponseEntity<Object> addNewCustomer(@RequestBody Customer customer) {
		customer = cserv.addNewCustomer(customer);
		return ResponseEntity.ok(customer);
	}
	
	@PutMapping(path="/{id}",produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE },
			consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE })
	public ResponseEntity<Object> updateCustomer(@RequestBody Customer customer,@PathVariable String id){
		customer = cserv.updateExistingCustomer(customer,id);
		return ResponseEntity.ok(customer);
	}

}
