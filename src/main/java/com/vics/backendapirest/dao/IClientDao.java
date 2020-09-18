package com.vics.backendapirest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vics.backendapirest.entity.Client;
import com.vics.backendapirest.entity.Region;



public interface IClientDao extends JpaRepository<Client, Long>{

	@Query("from Region")
	public List<Region> findAllRegions();
	
}
