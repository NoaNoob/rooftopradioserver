package de.rooftop.radio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BandService {

	public List<Band> getBandsFromCsvFile() {

		List<Band> bands = new ArrayList<>();

		try {
			BufferedReader reader = new BufferedReader(new FileReader(new ClassPathResource("bands.csv").getFile()));
			while (reader.ready()) {
				String line = reader.readLine();
				bands.add(parserLineToBand(line));
			}

			reader.close();

		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return bands;
	}

	Band parserLineToBand(String line) {

		String[] fields = line.split(";");

		if (fields.length != 5) {

			return Band.builder().name(fields[0]).location("").type("").genre("").internet("Bitte Csv-Daten prüfen")
					.build();

		}

		return Band.builder().name(fields[0]).location(fields[1]).type(fields[2]).genre(fields[3]).internet(fields[4])
				.build();
	}
}
