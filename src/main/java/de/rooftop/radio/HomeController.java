package de.rooftop.radio;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping(value = "/")
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/private")
	public String admin() {
		return "private";
	}

	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}
}
