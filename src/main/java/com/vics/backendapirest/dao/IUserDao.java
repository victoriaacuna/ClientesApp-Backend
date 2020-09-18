package com.vics.backendapirest.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.vics.backendapirest.entity.User;

public interface IUserDao extends CrudRepository<User, Long>{

	public User findByUsername(String username);
	
	// Esto lit es lo mismo
//	@Query("select u from User u where u.username=?1")
//	public User findByUsername2(String username);
	
}
