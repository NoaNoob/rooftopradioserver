package de.rooftop.radio.user;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

	@Autowired
	UserRepository userRepo;

	@Test
	public void shouldCreateAndRetrieveUser() {

		User user = User.builder().username("tester").password("test").build();
		userRepo.save(user);

		user = userRepo.findByUsername("tester");
		
		Assert.assertThat(user.getPassword(), Is.is("test"));
	}
}
