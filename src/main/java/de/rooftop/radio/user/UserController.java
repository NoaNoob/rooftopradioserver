package de.rooftop.radio.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

	@Autowired
	TokenStore tokenStore;

	@GetMapping(value = "/getUsername")
	public String getUsername() {

		return SecurityContextHolder.getContext().getAuthentication().getName();
	}

	@GetMapping(value = "/logout")
	public void logout(@RequestParam(value = "access_token") String accessToken) {

		tokenStore.removeAccessToken(tokenStore.readAccessToken(accessToken));
	}
}
