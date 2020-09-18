package com.vics.backendapirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vics.backendapirest.dao.IBillDao;
import com.vics.backendapirest.dao.IClientDao;
import com.vics.backendapirest.dao.IProductDao;
import com.vics.backendapirest.entity.Bill;
import com.vics.backendapirest.entity.Client;
import com.vics.backendapirest.entity.Product;
import com.vics.backendapirest.entity.Region;

@Service
public class ClientServiceImp implements IClientService{

	@Autowired
	private IClientDao clientDao;
	
	@Autowired
	private IBillDao billDao;
	
	@Autowired
	private IProductDao productDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Client> findAll() {
		return (List<Client>) this.clientDao.findAll();
	}
	
	
	@Override
	@Transactional(readOnly=true)
	public Page<Client> findAll(Pageable pageable) {
		
		return this.clientDao.findAll(pageable);
	}
	

	@Override
	@Transactional
	public Client save(Client client) {
		return this.clientDao.save(client);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		this.clientDao.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Client findById(Long id) {
		return (Client) this.clientDao.findById(id).orElse(null);
	}


	@Override
	@Transactional(readOnly=true)
	public List<Region> findAllRegions() {
		
		return this.clientDao.findAllRegions();
	}


	@Override
	@Transactional(readOnly=true)
	public Bill findBillById(Long id) {
		return this.billDao.findById(id).orElse(null);
	}


	@Override
	@Transactional
	public Bill saveBill(Bill bill) {
		return this.billDao.save(bill);
	}


	@Override
	@Transactional
	public void deleteBillById(Long id) {
		this.billDao.deleteById(id);
		
	}


	@Override
	@Transactional(readOnly=true)
	public List<Product> findProductByNameContainingIgnoreCase(String name) {
		
		return this.productDao.findByNameContainingIgnoreCase(name);
	}

	
	

}
