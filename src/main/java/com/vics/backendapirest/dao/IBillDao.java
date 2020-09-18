package com.vics.backendapirest.dao;

import org.springframework.data.repository.CrudRepository;

import com.vics.backendapirest.entity.Bill;

public interface IBillDao extends CrudRepository<Bill, Long>{

}
