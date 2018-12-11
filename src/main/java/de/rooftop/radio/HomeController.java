package de.rooftop.radio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

	@Autowired
	TokenStore tokenStore;
	
	@RequestMapping(value = "/")
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/home")
	public String home() {
		return "home";
	}

	@RequestMapping(value = "/impressum")
	public String impressum() {
		return "impressum";
	}

	@RequestMapping(value = "/private")
	public String admin() {
		return "private";
	}

	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/logout")
	public void logout(@RequestParam (value = "access_token") String accessToken) {

		tokenStore.removeAccessToken(tokenStore.readAccessToken(accessToken));
	}

}
