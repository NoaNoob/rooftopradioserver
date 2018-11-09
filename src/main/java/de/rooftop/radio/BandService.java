package de.rooftop.radio;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BandService {

	@Autowired
	StatusService statusService;

	@Autowired
	ResourceLoader resourceLoader;

	public List<Band> getBandsFromCsvFile() {

		List<Band> bands = new ArrayList<>();

		try {

			InputStream stream = resourceLoader.getResource("classpath:bands.csv").getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(stream);
			BufferedReader reader = new BufferedReader(inputStreamReader);

			while (reader.ready()) {
				String line = reader.readLine();
				bands.add(parserLineToBand(line));
			}

			reader.close();

		} catch (Exception e) {
			log.error(e.getMessage());
			statusService.logException(e);
		}

		return bands;
	}

	Band parserLineToBand(String line) {

		String[] fields = line.split(";");

		if (fields.length != 5) {

			return Band.builder().name(fields[0]).location("").type("").genre("").internet("not available").build();

		}

		String internet = fields[4];
		if (!internet.contains("http://")) {
			internet = "http://" + internet;
		}

		return Band.builder().name(fields[0]).location(fields[1]).type(fields[2]).genre(fields[3]).internet(internet)
				.build();
	}
}
