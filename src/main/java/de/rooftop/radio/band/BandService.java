package de.rooftop.radio.band;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import de.rooftop.radio.StatusService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BandService {

	@Autowired
	StatusService statusService;

	@Autowired
	ResourceLoader resourceLoader;

	@Autowired
	BandRepository bandRepository;

	public List<Band> getBandsFromCsvFile() {
		List<Band> bands = new ArrayList<>();

		try {
			return getBandsFromStream(resourceLoader.getResource("classpath:bands.csv").getInputStream());
		} catch (Exception e) {
			log.error(e.getMessage());
			statusService.logException(e);
		}
		
		return bands;
	}
	
	public List<Band> getBandsFromStream(InputStream stream) {

		List<Band> bands = new ArrayList<>();

		try {

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

	public List<Band> getBandsFromGoogleDrive() {

		List<Band> bands = new ArrayList<>();

		try {

			URL url = new URL("https://drive.google.com/open?id=1_dzq34g9HRnzZh02mxUWWN1A--nj6jVX");

			InputStreamReader inputStreamReader = new InputStreamReader(url.openStream());
			BufferedReader reader = new BufferedReader(inputStreamReader);

			while (reader.ready()) {
				String line = reader.readLine();
				System.out.println(line);
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
		if (!internet.contains("http://") && !internet.contains("https://")) {
			internet = "http://" + internet;
		}

		return Band.builder().name(fields[0]).location(fields[1]).type(fields[2]).genre(fields[3]).internet(internet)
				.build();
	}

	public List<Band> getAllBands() {

		return bandRepository.findAll(Sort.by(Direction.ASC, "name"));
	}

	public void initBandData() {

		List<Band> bands = getBandsFromCsvFile();
		bandRepository.deleteAll();
		bandRepository.saveAll(bands);
	}
	
	public void addBand(Band band) {

		bandRepository.save(band);
	}
	
	public void deleteBand(Long id) {

		bandRepository.deleteById(id);
	}
}
