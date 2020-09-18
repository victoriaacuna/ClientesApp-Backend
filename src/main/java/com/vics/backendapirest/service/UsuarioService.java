package com.vics.backendapirest.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vics.backendapirest.dao.IUserDao;
import com.vics.backendapirest.entity.User;

@Service
public class UsuarioService implements UserDetailsService,IUsuarioService{

	@Autowired
	private IUserDao userDao;
	
	private Logger logger = LoggerFactory.getLogger(UsuarioService.class);
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = this.userDao.findByUsername(username);
		
		if(user==null) {
			logger.error("¡ERROR! El usuario " + username +" no existe en el sistema, mi pana.");
		}
		
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role-> new SimpleGrantedAuthority(role.getName()))
				.peek(authority -> logger.info("Rol: "+authority.getAuthority()))
				.collect(Collectors.toList());
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), 
				user.getPassword(), user.getEnabled(), true, true, true, authorities);
	}

	@Override
	@Transactional(readOnly = true)
	public User findByUsername(String username) {
		
		return this.userDao.findByUsername(username);
	}

}
