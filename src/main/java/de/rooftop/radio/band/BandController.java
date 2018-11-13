package de.rooftop.radio.band;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.rooftop.radio.StatusService;

@RestController
public class BandController {

	BandService bandService;
	StatusService statusService;

	@Autowired
	BandController(BandService bandService, StatusService statusService) {
		this.bandService = bandService;
		this.statusService = statusService;
	}

	@RequestMapping("/bands")
	public List<Band> getBands() {

		return bandService.getBandsFromCsvFile();
	}

	@RequestMapping("/status")
	public String getStatus() {

		return "I'm fine.";
	}

	@RequestMapping("/exceptions")
	public List<Exception> getExceptions() {

		return statusService.getExceptions();
	}
}
