package de.rooftop.radio;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

import de.rooftop.radio.band.BandService;
import de.rooftop.radio.user.Role;
import de.rooftop.radio.user.User;
import de.rooftop.radio.user.UserRepository;

@SpringBootApplication
public class RadioApplication {

	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Bean
	public TokenStore tokenStore() {
	    return new InMemoryTokenStore();
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
	
		ConfigurableApplicationContext context = SpringApplication.run(RadioApplication.class, args);

		context.getBean(BandService.class).initBandData();
	}

	@Autowired
	public void authentificationManager(AuthenticationManagerBuilder builder, UserRepository repo) throws Exception {

		if (repo.count() == 0) {
			repo.save(User.builder().username("band").password(passwordEncoder.encode("band"))
					.roles(Arrays.asList(new Role("USER"), new Role("ACTUATOR"))).build());
		}

		builder.userDetailsService(s -> new CustomUserDetails(repo.findByUsername(s)));
	}
}
