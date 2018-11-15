package de.rooftop.radio.band;

import java.util.List;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BandRepositoryTest {

	@Autowired
	private BandRepository repo;

	@Test
	@Ignore
	public void shouldRetriveOrderedBandList() {

		repo.save(Band.builder().name("BBB").build());
		repo.save(Band.builder().name("ZZZ").build());
		repo.save(Band.builder().name("TTT").build());
		repo.save(Band.builder().name("AAA").build());

		List<Band> bands = repo.findAll(Sort.by(Direction.ASC, "name"));

		Assert.assertThat(bands.size(), Is.is(4));
		Assert.assertThat(bands.get(0).getName(), Is.is("AAA"));
		Assert.assertThat(bands.get(1).getName(), Is.is("BBB"));
		Assert.assertThat(bands.get(2).getName(), Is.is("TTT"));
		Assert.assertThat(bands.get(3).getName(), Is.is("ZZZ"));
		
	}
}
