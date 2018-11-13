package de.rooftop.radio.band;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Band {

	String name;
	String location;
	String type;
	String genre;
	String internet;
	
}
