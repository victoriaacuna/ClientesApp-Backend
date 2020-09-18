package com.vics.backendapirest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vics.backendapirest.entity.Bill;
import com.vics.backendapirest.entity.Product;
import com.vics.backendapirest.service.IClientService;

@CrossOrigin(origins = {"http://localhost:4200", "*"})
@RestController
@RequestMapping("/api")
public class BillRestController {
	
	@Autowired
	private IClientService clientService;
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/bill/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Bill show(@PathVariable Long id) {
		return this.clientService.findBillById(id);
	}
	
	@Secured({"ROLE_ADMIN"})
	@DeleteMapping("/bill/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public void deleteBill(@PathVariable Long id) {
		this.clientService.deleteBillById(id);
	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/bill/filter-product/{name}")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Product> filterProduct(@PathVariable String name){
		return this.clientService.findProductByNameContainingIgnoreCase(name);
	}
	
	@Secured({"ROLE_ADMIN"})
	@PostMapping("/bill/create")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Bill postBill(@RequestBody Bill bill) {
		return this.clientService.saveBill(bill);
	}
	
	
}
