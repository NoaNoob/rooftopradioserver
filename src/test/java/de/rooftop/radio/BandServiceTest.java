package de.rooftop.radio;

import java.util.List;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BandServiceTest {

	@Autowired
	BandService bandService;

	@Test
	public void shouldReadCsvFile() {

		List<Band> list = bandService.getBandsFromCsvFile();
		Assert.assertThat(list.size(), Is.is(74));
	}

	@Test
	@Ignore
	public void shouldReadCsvFileFromGoogleDrive() {

		List<Band> list = bandService.getBandsFromGoogleDrive();
		Assert.assertThat(list.size(), Is.is(74));
	}

	@Test
	public void shouldParseLineToBand() {
		Band band = bandService.parserLineToBand("Rooftop Radio;Görlitz;Band;Alternative Rock;www.rooftopradio.de");
		Assert.assertThat(band.getName(), Is.is("Rooftop Radio"));
		Assert.assertThat(band.getInternet(), Is.is("http://www.rooftopradio.de"));
	}

}
