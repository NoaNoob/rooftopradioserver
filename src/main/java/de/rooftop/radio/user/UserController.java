package de.rooftop.radio.user;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@GetMapping(value = "/getUsername")
	public String getUsername() {

		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
}
