package com.vics.backendapirest.service;

import com.vics.backendapirest.entity.User;

public interface IUsuarioService {
	
	public User  findByUsername(String username);
	
}
