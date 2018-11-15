package de.rooftop.radio.band;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Band {

	@Id
	@GeneratedValue
	Long id;
	String name;
	String location;
	String type;
	String genre;
	String internet;
	
}
