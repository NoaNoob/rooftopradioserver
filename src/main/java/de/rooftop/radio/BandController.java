package de.rooftop.radio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BandController {

	BandService bandService;

	@Autowired
	BandController(BandService bandService) {
		this.bandService = bandService;
	}

	@RequestMapping("/bands")
	public List<Band> getBands() {

		return bandService.getBandsFromCsvFile();
	}
}
