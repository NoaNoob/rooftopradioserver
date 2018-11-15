package de.rooftop.radio.band;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@RequestMapping("/initbands")
	public void initBands() {

		bandService.initBandData();
	}

	@RequestMapping("/deleteband")
	public void deleteBand(@RequestParam(value = "id") Long id) {

		bandService.deleteBand(id);
	}

	@RequestMapping("/bands")
	public List<Band> getBands() {

		return bandService.getAllBands();
	}

	@RequestMapping("/status")
	public String getStatus() {

		return "I'm fine. Current count of bands: " + bandService.getAllBands().size();
	}

	@RequestMapping("/exceptions")
	public List<Exception> getExceptions() {

		return statusService.getExceptions();
	}
}
