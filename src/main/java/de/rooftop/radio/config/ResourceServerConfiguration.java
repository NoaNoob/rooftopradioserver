package de.rooftop.radio.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/").permitAll().
		antMatchers("/home").permitAll().
		antMatchers("/impressum").permitAll().
		antMatchers("/private").authenticated().
		antMatchers("/initbands").authenticated().
		antMatchers("/addband").authenticated().
		antMatchers("/deleteband").authenticated().
		antMatchers("/login").permitAll().
		antMatchers("/logout").permitAll();

		http.headers().frameOptions().disable();
	}
}
