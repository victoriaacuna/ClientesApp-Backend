package com.vics.backendapirest.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.vics.backendapirest.entity.User;
import com.vics.backendapirest.service.IUsuarioService;


@Component
public class AditionalInfoToken implements TokenEnhancer{

	@Autowired
	private IUsuarioService userService;
	
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		User user = this.userService.findByUsername(authentication.getName());
		
		Map<String, Object> info = new HashMap<>();
		info.put("name", user.getName());
		info.put("last_name", user.getLast_name());
		info.put("email", user.getEmail());
		
		
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		return accessToken;
	}

}
