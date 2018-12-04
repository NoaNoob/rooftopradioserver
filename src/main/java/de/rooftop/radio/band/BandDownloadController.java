package de.rooftop.radio.band;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BandDownloadController {

	private BandService bandService;

	@Autowired
	public BandDownloadController(BandService bandService) {
		this.bandService = bandService;
	}

	@GetMapping("/download")
	public ResponseEntity<?> downloadUnitoEinfuhr(HttpServletRequest request) {

		String filename = "RooftopBandliste.csv";

		String response = getBandsAsCsvList();

		return createResponse(filename, response);
	}

	private ResponseEntity<String> createResponse(String filename, String responseString) {

		ResponseEntity<String> response = ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + filename + "\"")
				.contentType(MediaType.APPLICATION_OCTET_STREAM).body(responseString);

		return response;
	}

	private String getBandsAsCsvList() {

		StringBuilder response = new StringBuilder();
		for (Band band : bandService.getAllBands()) {
	
			response.append(band.name).append(";").append(band.location).append(";").append(band.type).append(";")
			.append(band.genre).append(";").append(band.internet).append("\n");

		}

		return response.toString();
	}

}
