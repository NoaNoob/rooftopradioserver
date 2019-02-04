package de.rooftop.radio;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;


@Controller
public class HomeController {

	@Autowired
	TokenStore tokenStore;
	
	@RequestMapping(value = "/")
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/home")
	public String home(Device device, HttpServletRequest request) {

		if (device.isMobile())
			return "mobile";

		return "home";
	}

	@RequestMapping(value = "/home2")
	public String home2(Device device, HttpServletRequest request) {

		return "home2";
	}

	@RequestMapping(value = "/music")
	public String music() {
		return "music";
	}

	@RequestMapping(value = "/gallery")
	public String gallery() {
		return "gallery";
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
