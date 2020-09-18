package com.vics.backendapirest.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vics.backendapirest.entity.Bill;
import com.vics.backendapirest.entity.Client;
import com.vics.backendapirest.entity.Product;
import com.vics.backendapirest.entity.Region;

public interface IClientService {
	
	public List<Client> findAll();
	
	public Page<Client> findAll(Pageable pageable);
	
	public Client save(Client client);
	
	public void delete(Long id);
	
	public Client findById(Long id);
	
	public List<Region> findAllRegions();
	
	public Bill findBillById(Long id);
	
	public Bill saveBill(Bill bill);
	
	public void deleteBillById(Long id);
	
	public List<Product> findProductByNameContainingIgnoreCase(String name);

}
